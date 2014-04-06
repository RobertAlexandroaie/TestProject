package com.endava.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.endava.dao.UserDao;
import com.endava.entities.User;
import com.endava.services.AuthenticateService;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

	@Autowired
	private UserDao userDao;

	public void verifyUsernameAndPassword(String userEmail, String password,
			BindingResult bindingResult) {

		User user = userDao.getUserByEmail(userEmail);

		if (user != null) {
			if (!(user.getPassword().equals(password) && user.getRegistry()
					.getStatus().equals("registered"))) {
				bindingResult.addError(new FieldError("loginForm", "incorrect",
						"Incorrect email and/or password"));
				bindingResult.addError(new FieldError("loginForm",
						"questionLogin", "Do you have an account?"));
				return;
			}
		}

	}

	@Transactional
	public void changePassword(String passwd, String email) throws Exception {

		User user = userDao.getUserByEmail(email);

		if (user != null) {
			user.getBids().size();
			user.getKeyCategoryWish().size();
			user.getCreatedAuctions().size();
			user.getWishesCategoryList().size();
			user.setPassword(passwd);

			userDao.update(user);
		}
	}

}
