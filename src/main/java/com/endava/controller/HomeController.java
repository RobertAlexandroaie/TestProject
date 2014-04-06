package com.endava.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.endava.dto.AuctionDto;
import com.endava.dto.CategoryDto;
import com.endava.entities.Category;
import com.endava.form.LoginForm;
import com.endava.form.ResetPasswdForm;
import com.endava.services.AuctionService;
import com.endava.services.AuthenticateService;
import com.endava.services.CategoryService;

import com.endava.util.EmailSenderUtil;

import com.endava.util.LoginFormInclude;

/**
 * Created with IntelliJ IDEA. User: Ionut Andonescu Date: 7/21/13 Time: 6:34 AM
 */
@Controller
@SessionAttributes({ "user_name" })
public class HomeController {

	private static final Logger LOGGER = Logger
			.getLogger(AccountController.class);

	private static List<AuctionDto> auctions;

	@Autowired
	AuthenticateService authService;

	@Autowired
	AuctionService auctionService;

	@Autowired
	CategoryService categoryService;

	@PostConstruct
	public void getAuctions() {
		auctions = auctionService.getAuctions();
	}

	@RequestMapping(value = "/getAuctionList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Map<String, List<AuctionDto>>> getAuctionList() {
		List<CategoryDto> categories = categoryService.getCategories();
		Map<String, Map<String, List<AuctionDto>>> categoryMap = new HashMap<String, Map<String, List<AuctionDto>>>();
		Map<String, List<AuctionDto>> subcategoryMap;

		for (CategoryDto category : categories) {
			subcategoryMap = new HashMap<String, List<AuctionDto>>();
			List<CategoryDto> subcategories = categoryService
					.getSubCategories(category.getId());
			for (CategoryDto subcategory : subcategories) {
				subcategoryMap.put(subcategory.getName(),
						new ArrayList<AuctionDto>());
			}
			categoryMap.put(category.getName(), subcategoryMap);
		}

		for (AuctionDto auction : auctions) {
			Category subcategory = auction.getCategory();
			subcategoryMap = categoryMap.get(subcategory.getParentCategory()
					.getName());
			subcategoryMap.get(subcategory.getName()).add(auction);
		}
		return categoryMap;
	}

	@RequestMapping(value = "/getAuction", method = RequestMethod.GET)
	public @ResponseBody
	AuctionDto getAuction(@RequestParam(value = "id") String id) {
		return auctionService.getAuctionById(id);
	}

	private String loginMethod(LoginForm loginForm,
			BindingResult bindingResult, Model model, HttpSession session) {
		model.addAttribute(loginForm);
		String email = loginForm.getEmail();
		String password = loginForm.getPassword();
		authService.verifyUsernameAndPassword(email, password, bindingResult);

		if ((!bindingResult.hasErrors())) {
			session.setAttribute("user_name", email);
			return "redirect:/profile";
		}
		return "index";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showHomepage(Model model, HttpSession session) {
		// Login Form
		model.addAttribute("loginForm", new LoginForm());
		// Sign up form
		// model.addAttribute(new SignUpForm());
		// model.addAttribute("countries", AccountController.getCountries());
		//

		return "index";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String doLogInPost(@Valid LoginForm loginForm, Model model,
			BindingResult bindingResult, HttpSession session) {

		LOGGER.debug(bindingResult);
		LOGGER.debug(loginForm.toString());

		return loginMethod(loginForm, bindingResult, model, session);
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String doLogout(SessionStatus sessionStatus, RedirectAttributes ra) {
		sessionStatus.setComplete();
		return "redirect:/";
	}

	@RequestMapping(value = "reset", method = RequestMethod.GET)
	public String doLogout(Model model) {

		model.addAttribute("resetPasswdForm", new ResetPasswdForm());

		return "resetPswd";
	}

	@RequestMapping(value = "reset", method = RequestMethod.POST)
	public String doLogInPost(@Valid ResetPasswdForm resetPasswdForm,
			Model model, BindingResult bindingResult) throws Exception {

		LOGGER.debug(bindingResult);
		LOGGER.debug(resetPasswdForm.toString());

		String email = resetPasswdForm.getEmail();

		String text = RandomStringUtils.randomAlphanumeric(8);

		if ((!bindingResult.hasErrors())) {

			try {
				EmailSenderUtil.sendMail(text, text, "project@noreply.com",
						email);
			} catch (MessagingException e) {
				bindingResult.addError(new FieldError("signUpForm", "email",
						"Cannot send email to that address."));
				e.printStackTrace();
			}
		}

		if ((!bindingResult.hasErrors())) {

			authService.changePassword(text, email);

			return "account/success";
		}

		return "reset";

	}

	@RequestMapping(value = "/details", method = RequestMethod.POST)
	public String showItemDetails(@RequestParam(value = "item") String item,
			Model model) {
		model.addAttribute("item", item);
		LoginFormInclude.addLoginForm(model);
		return "detailsPage";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String showProfile(HttpSession session) {
		if (session.getAttribute("user_name") == null) {
			return "redirect:/";
		}
		return "account/profile";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String showProfilePost(Model model, HttpServletRequest request) {

		if (request.getAttribute("buttonName") != null) {
			return "account/success";
		}
		return "account/profile";
	}

}
