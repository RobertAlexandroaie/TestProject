package com.endava.services;

import org.springframework.validation.BindingResult;

public interface AuthenticateService {
	
	
	void verifyUsernameAndPassword(String userName, String password, BindingResult bindingResult);
	
	void changePassword(String password, String email) throws Exception;
	

}
