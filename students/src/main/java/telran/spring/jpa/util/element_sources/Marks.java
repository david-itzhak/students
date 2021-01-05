package telran.spring.jpa.util.element_sources;

import static telran.spring.jpa.util.RandomData.*;

public class Marks {
	public int getRandomMark() {
		return getRandomInt(60, 100);
	}
}
