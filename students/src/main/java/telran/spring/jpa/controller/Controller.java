package telran.spring.jpa.controller;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RestController;

import telran.spring.jpa.dto.MarkDto;
import telran.spring.jpa.dto.StudentDto;
import telran.spring.jpa.dto.SubjectDto;
import telran.spring.jpa.service.interfaces.Students;
import static telran.spring.jpa.util.RandomDtoCreation.*;

import java.util.Arrays;

@Component
public class Controller {
	private static final int STUDENTS_QUANTITY = 20;
	private static final int MIN_MARK = 60;
	private static final int MAX_MARK = 100;

	@Autowired
	Students students;

	@PostConstruct
	void fillDB() {
		StudentDto[] studentsDtoArray = getRandomStudentsDtoArray(STUDENTS_QUANTITY);
		Arrays.stream(studentsDtoArray).forEach((dto) -> students.addStudent(dto));

		SubjectDto[] subjectDtoArray = getSubjectsDtoArray();
		Arrays.stream(subjectDtoArray).forEach((dto) -> students.addSubject(dto));

		MarkDto[] marksDtoArray = getRandomMarksDtoArray(studentsDtoArray, subjectDtoArray, MIN_MARK, MAX_MARK);
		Arrays.stream(marksDtoArray).forEach((dto) -> students.addMark(dto));
	}
}

