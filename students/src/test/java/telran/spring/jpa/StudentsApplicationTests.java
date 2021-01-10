package telran.spring.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import telran.spring.jpa.service.interfaces.Students;

@SpringBootTest

class StudentsApplicationTests {
@Autowired
Students students;

	@Test
	
	void contextLoads() {
		
	}
	
	

}
