package com.endava.form.util;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidations {
	public static final int LEGAL_AGE = 18;

	private DateValidations() {
	}

	public static boolean hasTheLegalAge(Date birthDate) {
		return new Date().compareTo(new DateTime(birthDate)
				.plusYears(LEGAL_AGE).toDate()) == 1;
	}

	public static int hasTheLegalAge(String day, String month, String year) {
		Date userDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setLenient(false);
		/*
		if (day.equals("default") || month.equals("default")
				|| year.equals("default") || day.isEmpty() || month.isEmpty()
				|| year.isEmpty()) {
			return 0;
		}
		*/
		try {
			userDate = sdf.parse(day + "-" + month + "-" + year);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		if (new Date().compareTo(new DateTime(userDate).plusYears(LEGAL_AGE)
				.toDate()) == 1) {
			return 1;
		}
		return -1;
	}
}