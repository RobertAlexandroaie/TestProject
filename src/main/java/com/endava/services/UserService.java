package com.endava.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.endava.entities.Bid;
import com.endava.entities.ImageFile;
import com.endava.entities.Registry;
import com.endava.form.SignUpForm;



public interface UserService {
	
	@Transactional
    void createUser(SignUpForm signUpForm);
	
	@Transactional
	void setImage(String userEmail, ImageFile imageFile);

    List<String> getCodes();
    String getUserStatusByEmail(String email);
    List<Bid> getBidsByUserEmail(String email);
    void activateCode(String code);
}
