package telran.spring.jpa.controller;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RestController;

import telran.spring.jpa.dto.MarkDto;
import telran.spring.jpa.dto.StudentDto;
import telran.spring.jpa.dto.SubjectDto;
import telran.spring.jpa.service.interfaces.Students;

@Component
public class Controller {
	@Autowired
	Students students;
	
	@PostConstruct
	void fillDB() {
		students.addStudent(new StudentDto(1, "Vasia"));
		students.addSubject(new SubjectDto(1, "Java"));
		students.addMark(new MarkDto(1, 1, 80));
	}
}
