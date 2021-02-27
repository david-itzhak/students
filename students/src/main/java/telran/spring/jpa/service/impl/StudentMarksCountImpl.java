package telran.spring.jpa.service.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import telran.spring.jpa.dto.StudentMarksCount;

@AllArgsConstructor
@Getter
public class StudentMarksCountImpl implements StudentMarksCount {
	String name;
	long marksCount;
}
