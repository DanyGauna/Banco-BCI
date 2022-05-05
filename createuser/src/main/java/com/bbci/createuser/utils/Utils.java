package com.bbci.createuser.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;

public class Utils {
	
	@Value("${pattern.email}")
	private static String patternEmail;
	
	@Value("${pattern.passwordvalidator}")
	private static String patternPasswordValidator;

	public static Boolean validaEmail(String email) {

		Pattern pattern = Pattern.compile(patternEmail);
		Matcher mather = pattern.matcher(email);
		if (mather.find() == true) {
			return true;
		}
		return false;
	}
	
	
	public static String passwordValidator(String password) {

		Pattern pattern = Pattern
				.compile(patternPasswordValidator);
		Matcher mather = pattern.matcher(password);
		if (mather.find() == true) {
			return "";
		}
		return ConstantesGenerales.ERROR_PASSWORD;
	}

}
