package telran.spring.jpa.util;

import java.time.LocalDate;

public class RandomData {

	public static int getRandomInt(int min, int max) {
		return (int) (min + Math.random() * (max - min + 1));
	}

	public static long getRandomLong(long min, long max) {
		return (long) (min + Math.random() * (max - min + 1));
	}

	public static <T> T getRandomArrayElement(T[] array) {
		int index = getRandomInt(0, array.length - 1);
		return array[index];
	}

	public static int chance() {
		// Probability in percents
		return getRandomInt(1, 100);
	}

	public static LocalDate getRandomDate(int minYear, int maxYear) {
		return LocalDate.of(getRandomInt(minYear, maxYear), getRandomInt(1, 12), getRandomInt(1, 28));
	}
}
