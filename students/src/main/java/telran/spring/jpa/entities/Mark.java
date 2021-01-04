package telran.spring.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "marks")
public class Mark {

	@GeneratedValue
	@Id
	int id;
	@Column(nullable = false)
	int mark;
	@ManyToOne
	Student student;
	@ManyToOne
	Subject subject;

	public Mark() {
	}

	public Mark(int mark, Student student, Subject subject) {
		this.mark = mark;
		this.student = student;
		this.subject = subject;
	}
}
