package telran.spring.jpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.spring.jpa.dto.IntervalMarks;
import telran.spring.jpa.dto.MarkDto;
import telran.spring.jpa.dto.StudentDto;
import telran.spring.jpa.dto.StudentMarksCount;
import telran.spring.jpa.dto.SubjectDto;
import telran.spring.jpa.entities.Mark;
import telran.spring.jpa.entities.Student;
import telran.spring.jpa.entities.Subject;
import telran.spring.jpa.repo.*;
import telran.spring.jpa.service.interfaces.Students;

@Service
public class StudentsJpaImpl implements Students {
	@Autowired
	StudentRepository students;
	@Autowired
	SubjectRepository subjects;
	@Autowired
	MarkRepository marks;

	@Override
	@Transactional
	public void addStudent(StudentDto studentDto) {
		Student student = new Student(studentDto.getStid(), studentDto.getName());
		students.save(student);

	}

	@Override
	@Transactional
	public void addSubject(SubjectDto subjectDto) {
		Subject subject = new Subject(subjectDto.getSuid(), subjectDto.getSubject());
		subjects.save(subject);

	}

	@Override
	@Transactional
	public void addMark(MarkDto markDto) {
		Student student = students.findById(markDto.getStid()).orElse(null);
		Subject subject = subjects.findById(markDto.getSuid()).orElse(null);
		Mark mark = new Mark(markDto.getMark(), student, subject);
		marks.save(mark);
	}

	@Override
	@Transactional
	public void deleteMarks(String name, String subject) {
		marks.deletMarks(name, subject);
	}

	@Override
	public List<String> bestStudents() {
		return marks.findBestStudents();
	}

	@Override
	public List<String> bestStudents(int nStudents) {
//		return marks.findTopBestStudents(nStudents);
		return marks.findTopBestStudents(PageRequest.of(0, nStudents));
	}

	@Override
	public List<String> bestStudentsSubject(String subject) {
		return marks.findBestStudentsSubject(subject);
	}

	@Override
	public List<String> bestStudentsSubject(int nStudents, String subject) {
		return marks.findTopBestStudentsSubject(subject, PageRequest.of(0,nStudents));
	}

	@Override
	public List<StudentMarksCount> getStudentsMarksCount() {
		return marks.findStudentsMarksCount();
	}

	@Override
	public List<String> getSubjectsHighestMarks() {
		return marks.findSubjectsHighestMarks();
	}

	@Override
	public List<String> getTopSubjectsHighestMarks(int nSubjects) {
		return marks.findTopSubjectsHighestMarks(PageRequest.of(0,nSubjects));
	}

	@Override
	public List<Integer> getTopMarksEncountered(int nMarks, String subject) {
		return marks.findTopMarksEncountered(subject, PageRequest.of(0, nMarks));
	}

	@Override
	public List<IntervalMarks> getIntervalsMarks(int interval) {
		return marks.findIntervalsMarks(interval);
	}

	@Override
	@Transactional
	public void deleteStudent(String name) {
		students.deleteStudent(name);
	}

// *********** HW 54 ***********
	@Override
	@Transactional
	public void averagingSubjectMarks() {
		List<Integer> studentsId = students.getStudentsId();
		List<Integer> subjectsId = subjects.getSubjectsId();
		for (int stid : studentsId) {
			for (int suid : subjectsId) {
				int countMarks = marks.getCountMarksForStudentBySubject(stid, suid);
				if (countMarks > 1) {
					int avg = Math.round(marks.getAvgMarkForStudentBySubject(stid, suid));
					marks.deletAllMarksForStudentBySubject(stid, suid);
					marks.save(new Mark(avg, students.findById(stid).orElse(null), subjects.findById(suid).orElse(null)));
				}
			}
		}
	}
}
