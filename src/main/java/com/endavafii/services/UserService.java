package com.endavafii.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.endavafii.entities.Bid;
import com.endavafii.entities.ImageFile;
import com.endavafii.entities.Registry;
import com.endavafii.form.SignUpForm;



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
