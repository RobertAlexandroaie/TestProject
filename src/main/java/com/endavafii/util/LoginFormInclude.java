package com.endavafii.util;

import org.springframework.ui.Model;

import com.endavafii.form.LoginForm;

public class LoginFormInclude {
	public static void addLoginForm(Model model) {
		model.addAttribute("loginForm", new LoginForm());
	}
}