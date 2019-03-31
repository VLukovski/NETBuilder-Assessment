package com.qa.assessment;

import java.util.List;
import java.util.stream.Collectors;

public class Alphabet {

	public String replaceLetterWithPosition(String string) {
		String positions = string.toLowerCase().chars()
				.filter(i -> (i <= 122 && i >= 97))
				.map(i -> i - 96)
				.boxed().collect(Collectors.toList())
				.toString()
				.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(",", "");
		return positions;
	}

}
