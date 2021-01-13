package telran.spring.jpa.entities;

import java.util.List;

import javax.persistence.*;  // This is JPA
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "subjects")
@RequiredArgsConstructor
@NoArgsConstructor
public class Subject {
	
	@Id
	@NonNull Integer suid;
	
	@Column(unique = true, nullable = false)
	@NonNull String subject;
	
	@OneToMany(mappedBy = "subject", cascade = CascadeType.REMOVE)
	@OnDelete(action = OnDeleteAction.CASCADE)
	List<Mark> marks;
}
