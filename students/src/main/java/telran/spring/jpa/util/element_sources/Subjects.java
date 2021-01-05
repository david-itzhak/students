package telran.spring.jpa.util.element_sources;

import java.util.Arrays;
import java.util.stream.Stream;

public class Subjects {
	public static final String FRONT_END[] = {
			"React", 
			"JS", 
			"CSS", 
			"HTML", 
			"Reducs"
			};
	
	public static final String BACK_END[] = {
			"Java Core", 
			"Java Advanced", 
			"Spring", 
			"DB", 
			"Maven" 
	};
	
	public static final String SUBJECTS[] = Stream.concat(Arrays.stream(FRONT_END), Arrays.stream(BACK_END))
			.toArray(String[]::new);
}
