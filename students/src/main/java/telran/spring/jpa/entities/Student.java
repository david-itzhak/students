package telran.spring.jpa.entities;

import java.util.List;
import javax.persistence.*; // This is JPA

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity // This annotations says that is the entity of the database. For SQL this annotation says that should be a corresponding table.
@Table(name = "students") // This annotation defines only name of the table. This is additional for the annotation @Entity.
@RequiredArgsConstructor
@NoArgsConstructor 
public class Student {
	
	@Id // This is required field for entity. This is means -> Primary key.
	@NonNull Integer stid;
	
	@Column(unique = true, nullable = false)
	@NonNull String name;

	@OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
	@OnDelete(action = OnDeleteAction.CASCADE)
	List<Mark> marks;
}
