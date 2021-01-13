package telran.spring.jpa.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class MarkDto {
	private int stid;
	private int suid;
	private int mark;
}
