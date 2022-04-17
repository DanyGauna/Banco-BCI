package com.bbci.createuser.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

	public static Boolean validaEmail(String email) {

		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mather = pattern.matcher(email);
		if (mather.find() == true) {
			return true;
		}
		return false;
	}
	
	
	public static String passwordValidator(String password) {

		Pattern pattern = Pattern
				.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,12}$");
		Matcher mather = pattern.matcher(password);
		if (mather.find() == true) {
			return "";
		}
		return ConstantesGenerales.ERROR_PASSWORD;
	}

}
