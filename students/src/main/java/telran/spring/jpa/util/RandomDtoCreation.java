package telran.spring.jpa.util;

import telran.spring.jpa.dto.MarkDto;
import telran.spring.jpa.dto.StudentDto;
import telran.spring.jpa.dto.SubjectDto;

import static telran.spring.jpa.util.RandomData.*;
import static telran.spring.jpa.util.element_sources.Names.*;
import static telran.spring.jpa.util.element_sources.Subjects.*;

import java.util.Arrays;

public class RandomDtoCreation {

	public static StudentDto[] getRandomStudentsDtoArray(int students_quantity) {
		StudentDto[] studentDtoArray = new StudentDto[students_quantity];
		String[] names = Arrays.stream(NAMES).distinct().limit(students_quantity).toArray(String[]::new);
		for (int i = 1; i <= students_quantity; i++) {
			studentDtoArray[i - 1] = new StudentDto(i, names[i-1]);
		}
		return studentDtoArray;
	}

	public static SubjectDto[] getSubjectsDtoArray() {
		SubjectDto[] subjectDtoArray = new SubjectDto[SUBJECTS.length];
		for (int i = 1; i <= SUBJECTS.length; i++) {
			subjectDtoArray[i - 1] = new SubjectDto(i, SUBJECTS[i - 1]);
		}
		return subjectDtoArray;
	}
	
	public static MarkDto[] getRandomMarksDtoArray(StudentDto[] studentDtoArray, SubjectDto[] subjectDtoArray, int min_mark, int max_mark) {
		MarkDto[] markDtoArray = new MarkDto[studentDtoArray.length * subjectDtoArray.length];
		int count = 0;
		for(StudentDto studentDto : studentDtoArray) {
			for(SubjectDto subjectDto : subjectDtoArray) {
				markDtoArray[count] = new MarkDto(studentDto.stid, subjectDto.suid, getRandomInt(min_mark, max_mark));
				count++;
			}
		}
		return markDtoArray;
	}
}
