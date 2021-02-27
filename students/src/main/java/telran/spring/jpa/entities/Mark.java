package telran.spring.jpa.entities;

import javax.persistence.*; // This is JPA

import lombok.*;

@Entity // This annotations says that is the entity of the database. For SQL this annotation says that should be a corresponding table.
@Table(name = "marks") // This annotation defines only name of the table. This is additional for the annotation @Entity.
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
public class Mark {
	
	@Id // This is required field for entity.  This is means -> Primary key.
	@GeneratedValue // Is it auto increment?
	int id;
	
	@NonNull Integer mark;

	@ManyToOne  // Is it "Foreign key"?
	@NonNull Student student;
	
	@ManyToOne
	@NonNull Subject subject;

}
