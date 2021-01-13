package telran.spring.jpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.spring.jpa.entities.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

	// *********** HW #54 ***********
	@Query(value = "select suid from subjects", nativeQuery = true)
	List<Integer> getSubjectsId();
}