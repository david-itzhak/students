package telran.spring.jpa.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class StudentDto {
	private int stid;
	private String name;
}
