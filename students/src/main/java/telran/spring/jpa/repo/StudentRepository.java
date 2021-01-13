package telran.spring.jpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.spring.jpa.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	/******************************************************************/
	@Query(value="select * from students where name = :name", nativeQuery = true)
	Student findByName(@Param("name")String name);
	/******************************************************************/
	@Modifying
	@Query(value="delete from students where name = :name", nativeQuery = true)
	void deleteStudent(@Param("name")String name);
	
	
	// *********** HW #54 ***********
	@Query(value="select stid from students", nativeQuery = true)
	List<Integer> getStudentsId();
}
