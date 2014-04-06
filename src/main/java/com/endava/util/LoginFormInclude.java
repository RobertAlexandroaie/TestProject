package com.endava.util;

import org.springframework.ui.Model;

import com.endava.form.LoginForm;

public class LoginFormInclude {
	public static void addLoginForm(Model model) {
		model.addAttribute("loginForm", new LoginForm());
	}
}