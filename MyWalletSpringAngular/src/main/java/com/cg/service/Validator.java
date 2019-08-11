package com.cg.service;

public interface Validator {
	String namePattern = "([A-Za-z]){3}([A-Za-z])*( ([A-Za-z]){3}([A-Za-z])*)* "+
			"(([A-Za-z]){3}([A-Za-z])*)|(([A-Za-z]){3}([A-Za-z])* "+
			"(([a-zA-Z])+'([a-zA-Z])+))|(([a-zA-Z])+'([a-zA-Z])+)";
	String mobilePattern = "[1-9]{1}[0-9]{9}";
	String aIdPattern = "[1-9]{1}\\d{2}";
	String balancePattern = "([0-9])+|([0-9])+.([0-9])+";
	
	/**
	 * For validating the given input on its particular pattern
	 * @param data: input to be compared
	 * @param pattern: pattern though which the input is compared
	 * */
	static boolean validateData(String data, String pattern) {
		return data.matches(pattern);
	}
}
