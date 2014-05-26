
package com.endavafii.controller;


import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.endavafii.form.SignUpForm;
import com.endavafii.model.Country;
import com.endavafii.services.UserService;
import com.endavafii.util.DigestUtil;
import com.endavafii.util.EmailSenderUtil;
import com.endavafii.util.LoginFormInclude;

@Controller
@RequestMapping("account")
public class AccountController {
    
    private static final Logger LOGGER = Logger.getLogger(AccountController.class);
    private static Set<Country> countries = new TreeSet<Country>();
    
    static {
	Locale[] loc = Locale.getAvailableLocales();
	for (Locale iterator : loc) {
	    Country newCountry = new Country(iterator.getCountry(), iterator.getDisplayCountry());
	    if (!newCountry.getName().equals("")) {
		countries.add(newCountry);
	    }
	}
    }
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String signUpAccount(@Valid SignUpForm signUpForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
	
	signUpForm.additionalValidations(bindingResult);
	
	model.addAttribute(signUpForm);
	model.addAttribute("countries", countries);
	
	String remoteAddr = request.getRemoteAddr();
	ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
	reCaptcha.setPrivateKey("6LeHMeUSAAAAAK3GPHrmdrqt0LKMHEM1YXUwLP8X");
	
	String challenge = request.getParameter("recaptcha_challenge_field");
	String uresponse = request.getParameter("recaptcha_response_field");
	
	ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);
	
	if (!reCaptchaResponse.isValid()) {
	    bindingResult.addError(new FieldError("signUpForm", "captcha", "Answer is wrong."));
	}
	
	String email = signUpForm.getEmail();
	String digestEmail = "";
	try {
	    digestEmail = DigestUtil.digest(email);
	} catch (Exception e1) {
	    
	    e1.printStackTrace();
	}
	
	String status = userService.getUserStatusByEmail(email);
	
	if (status.equals("registered")) {
	    bindingResult.addError(new FieldError("signUpForm", "email", "Email already exists."));
	}
	if (status.equals("pending")) {
	    bindingResult.addError(new FieldError("signUpForm", "email", "Check your email for confirmation."));
	}
	
	String text = "To verify your email address, please follow this link: http://localhost:9080/EnCoders/account/validate?code=" + digestEmail;
	if ((!bindingResult.hasErrors())) {
	    try {
		EmailSenderUtil.sendMail(text, text, "project@noreply.com", email);
	    } catch (MessagingException e) {
		bindingResult.addError(new FieldError("signUpForm", "email", "Cannot send email to that address."));
		e.printStackTrace();
	    }
	}
	LoginFormInclude.addLoginForm(model);
	if ((!bindingResult.hasErrors())) {
	    userService.createUser(signUpForm);
	    
	    return "account/success";
	}
	
	return "account/signup";
    }
    
    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String signUp(Model model) {
	model.addAttribute(new SignUpForm());
	model.addAttribute("countries", countries);
	LoginFormInclude.addLoginForm(model);
	return "account/signup";
    }
    
    @RequestMapping(value = "validate", method = RequestMethod.GET)
    public String validate(@RequestParam("code") String code, Model model) {
	userService.activateCode(code);
	LoginFormInclude.addLoginForm(model);
	return "account/validate";
    }
}
