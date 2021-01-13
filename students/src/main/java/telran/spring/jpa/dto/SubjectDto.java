package telran.spring.jpa.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class SubjectDto {
	private int suid;
	private String subject;
}
