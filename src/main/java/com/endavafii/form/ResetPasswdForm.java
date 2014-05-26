package com.endavafii.form;

import org.hibernate.validator.constraints.NotEmpty;

public class ResetPasswdForm {

	
	@NotEmpty
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIncorrect() {
		return incorrect;
	}

	public void setIncorrect(String incorrect) {
		this.incorrect = incorrect;
	}

	private String incorrect;
	

}
