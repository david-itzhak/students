package telran.spring.jpa;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import telran.spring.jpa.service.interfaces.Students;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class StudentsApplicationTests {

	@Autowired
	Students students;

	@Autowired
	MockMvc mockMvc;

	@Test
	@Sql("fillTables.sql")
	void bestStudents() {
		List<String> bestStudents = students.bestStudents();
		assertEquals(1, bestStudents.size());
		assertEquals("Moshe", bestStudents.get(0));
		bestStudents = students.bestStudents(2);
		assertEquals("Moshe", bestStudents.get(0));
		assertEquals("Sara", bestStudents.get(1));
	}

	@Test
	@Sql("fillTables.sql")
	void highestSubjectMarks() {
	}

	@Test
	@Sql("fillTables.sql")
	void deleteMarks() throws Exception {
		int status = mockMvc.perform(MockMvcRequestBuilders.delete("/marks/Java?name=Moshe")).andReturn().getResponse()
				.getStatus();
		assertEquals(200, status);
		status = mockMvc.perform(MockMvcRequestBuilders.delete("/marks/React?name=Moshe")).andReturn().getResponse()
				.getStatus();
		withoutMoshe();
	}

	@Test
	@Sql("fillTables.sql")
	void deleteStudent() {
		students.deleteStudent("Moshe");
		withoutMoshe();
	}
	
	private void withoutMoshe() {
		List<String> bestStudents = students.bestStudents();
		assertEquals(1, bestStudents.size());
		assertEquals("Sara", bestStudents.get(0));
	}
	
}
