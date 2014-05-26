package com.endavafii.form;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm {
	
	
	@NotEmpty
    private String email;
    
    
    @NotEmpty
    private String password;
    
    private String incorrect;
    
    private String questionLogin;
    
    
    
	public String getQuestionLogin() {
		return questionLogin;
	}
	public void setQuestionLogin(String questionLogin) {
		this.questionLogin = questionLogin;
	}
	public String getIncorrect() {
		return incorrect;
	}
	public void setIncorrect(String incorrect) {
		this.incorrect = incorrect;
	}
	public String getPassword() {
		return password;
	}
	
	
		public void setPassword(String password) {
			String md5 = null;
			try {
				MessageDigest mdEnc = MessageDigest.getInstance("MD5"); // Encryption
																		// algorithm
				mdEnc.update(password.getBytes(), 0, password.length());
				md5 = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted
																		// string
			} catch (Exception ex) {

			}

			this.password = md5;
		}
	
		
	public String getEmail() {
		return email;
	}
	public void setEmail(String firstName) {
		this.email = firstName;
	}
	
    

}
