package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.DomainException;

public class ValidateRegex {
	
	public static void validate(String text, String regex) {

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(text);

		if(!matcher.find()) {
			throw new DomainException("Dado inválido, digite o dado conforme o exemplo acima");
		}

	}

}
