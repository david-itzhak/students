package telran.spring.jpa.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.spring.jpa.dto.IntervalMarks;
import telran.spring.jpa.dto.StudentMarksCount;
import telran.spring.jpa.entities.Mark;

public interface MarkRepository extends JpaRepository<Mark, Integer> {
	/******************************************************************/
	
//	@Query(value = "select name from students join marks" + " on stid=student_stid group by name having avg(mark) >="
//			+ " (select avg(mark) from marks) order by avg(mark) desc", nativeQuery = true)
//	List<String> findBestStudents();
	@Query(value = "select s.name from Student s join s.marks m group by s.name having avg(m.mark.mark) >= (select avg(m.mark.mark) from Mark m) order by avg(m.mark.mark) desc")
	List<String> findBestStudents();
	
	/******************************************************************/
	
//	@Query(value = "select name from students join marks" + " on stid=student_stid group by name "
//			+ "  order by avg(mark) desc limit :n_students", nativeQuery = true)
//	List<String> findTopBestStudents(@Param("n_students") int nStudents);
	@Query(value = "select s.name from Student s join s.marks m group by s.name order by avg(m.mark.mark) desc") // Не получается убрать order by и заменить на сортировку через Pageable
	List<String> findTopBestStudents(Pageable pageable);

	
	/****************************************************************************/
	
//	@Query(value = "select name from students_marks_subjects where subject=:subject "
//			+ "group by name having avg(mark) >= (select avg(mark) from students_marks_subjects "
//			+ "where subject=:subject) order by avg(mark)", nativeQuery = true)
	@Query(value = "select s.name from Student s join s.marks m where m.subject.subject = :subject group by s.name having avg(m.mark.mark) >= (select avg(mk.mark.mark) from Student st "
			+ "join st.marks mk where mk.subject.subject = :subject) order by avg(m.mark.mark)")
	List<String> findBestStudentsSubject(@Param("subject") String subject);
	
	/*********************************************************************************/
	
//	@Query(value = "select name from students_marks_subjects where subject=:subject "
//			+ "group by name order by avg(mark) desc limit :n_students ", nativeQuery = true)
//	List<String> findTopBestStudentsSubject(@Param("n_students") int nStudents, @Param("subject") String subject);
	@Query(value = "select s.name from Student s join s.marks m where m.subject.subject = :subject group by s.name order by avg(m.mark.mark) desc") // Не получается убрать order by и заменить на сортировку через Pageable
	List<String> findTopBestStudentsSubject(@Param("subject") String subject, Pageable pageable);
	
	/******************************************************************/
	
//	@Query(value = "select name, count(mark) as marksCount from students left join marks on "
//			+ "stid=student_stid group by name", nativeQuery = true)
//	@Query(value = "select s.name, count(m.mark.mark) as marksCount from Student s left join s.marks m group by s.name")
//	List<Object[]> findStudentsMarksCount();
	@Query(value = "select new telran.spring.jpa.service.impl.StudentMarksCountImpl  (s.name, count(m.mark.mark)) from Student s left join s.marks m group by s.name")
	List<StudentMarksCount> findStudentsMarksCount();
	
	/******************************************************************/
	
//	@Query(value = "select subject from marks_subjects group by subject order by sum(mark) desc limit :n_subjects", nativeQuery = true)
//	List<String> findTopSubjectsHighestMarks(@Param("n_subjects") int nSubjects);
	@Query(value = "select s.subject from Subject s join s.marks m group by s.subject order by sum(m.mark.mark) desc") // Не получается убрать order by и заменить на сортировку через Pageable
	List<String> findTopSubjectsHighestMarks(Pageable pageable);
	
	/**********************************************************************************/
	
	@Query(value = "select subject from marks_subjects group by subject having sum(mark) >"
			+ " (select avg(total) from (select sum(mark) as total from marks_subjects group by subject )as totals) order by sum(mark) desc", nativeQuery = true) // Не получается переделать на JPQL, так как есть вложенный сабселект в клаузе фор
	List<String> findSubjectsHighestMarks();
	
	/***************************************************************************************************/
	
//	@Query(value = "select mark from marks_subjects where subject=:subject group by mark" // TODO переделать на JPQL
//			+ " order by count(*) desc limit :n_marks", nativeQuery = true)
//	List<Integer> findTopMarksEncountered(@Param("n_marks") int nMarks, @Param("subject") String subject);
	@Query(value = "select m.mark from Mark m where m.subject.subject = :subject group by m.mark order by count(*) desc")
	List<Integer> findTopMarksEncountered(@Param("subject") String subject, Pageable pageable);
	
	/********************************************************************************************************/
//	@Query(value = "select mark/:interval * :interval as min, :interval * (mark/:interval + 1) - 1 as max, count(*) as occurrences from " // TODO переделать на JPQL
//			+ " marks group by min, max  order by min", nativeQuery = true)
//	List<IntervalMarks> findIntervalsMarks(@Param("interval") int interval);
	@Query(value = "select m.mark/:interval * :interval as minMark, :interval * (m.mark/:interval + 1) - 1 as maxMark, count(*) as occurrences from " // TODO переделать на JPQL
			+ " Mark m group by minMark, maxMark order by minMark")
	List<IntervalMarks> findIntervalsMarks(@Param("interval") int interval);
	/********************************************************************************************************/
	@Modifying
	@Query(value="delete from marks where student_stid = "  // TODO переделать на JPQL
			+ "(select stid from students where name = :name) "
			+ "and subject_suid = (select suid from subjects where subject = "
			+ ":subject)", nativeQuery = true)
	void deletMarks(String name, String subject);
	
	
	// *********** HW #54 ***********
	
//	@Query(value="select COUNT(mark) from marks where student_stid = :stid and subject_suid = :suid", nativeQuery = true)
//	int getCountMarksForStudentBySubject(int stid, int suid);
	@Query(value="select COUNT(m) from Mark m where m.student.stid = :stid and m.subject.suid = :suid")
	int getCountMarksForStudentBySubject(int stid, int suid);
	
//	@Query(value="select ROUND(AVG(mark)) from marks where student_stid = :stid and subject_suid = :suid", nativeQuery = true)
//	int getAvgMarkForStudentBySubject(int stid, int suid);
	@Query(value="select AVG(m.mark) from Mark m where m.student.stid = :stid and m.subject.suid = :suid")
	int getAvgMarkForStudentBySubject(int stid, int suid);
	
	@Modifying
	@Query(value="delete from Mark m where m.student.stid = :stid and m.subject.suid = :suid")
	void deletAllMarksForStudentBySubject(int stid, int suid);
	
//	@Query(value="select m from Mark m where m.student.stid = :stid and m.subject.suid = :suid")
//	List <Mark> getMarksForStudentBySubject(int stid, int suid);
//	
//	@Query(value="select SUM(m.mark) from Mark m where m.student.stid = :stid and m.subject.suid = :suid")
//	int getSumMarksForStudentBySubject(int stid, int suid);
}
