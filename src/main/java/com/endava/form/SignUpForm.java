package com.endava.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.endava.form.util.DateValidations;

public class SignUpForm {

	@NotEmpty(message="First name is required.")
	@Pattern(regexp="[A-Z][a-zA-Z]+", message="Must contain only letters, starting with capital letter.")
	private String firstName;
	
	@NotEmpty(message="Last name is required.")
	@Pattern(regexp="[A-Z][a-zA-Z]+", message="Must contain only letters, starting with capital letter.")
	private String lastName;
	
	@Email(message="Email format incorect.")
	@NotNull(message="Email is required.")
	@NotEmpty(message="Email is required.")
	private String email;
	
	@Email(message="Email format incorect.")
	@NotNull(message="Email is required.")
	@NotEmpty(message="Email is required.")
	private String emailVer;
	
	@Length(min = 6, max = 25, message="Password must be of minimum lenght 6 and maximum length 25.")
	private String password;

	@Length(min = 6, max = 25, message="Password must be of minimum lenght 6 and maximum length 25.")
	private String passwordCheck;

	@NotEmpty
	@NotNull
	private String gender;
	
	@NotNull(message="Day must not be empty.")
	@NotEmpty(message="Day must not be empty.")
	private String day;
	@NotNull(message="Month must not be empty.")
	@NotEmpty(message="Month must not be empty.")
	private String month;
	@NotNull(message="Year must not be empty.")
	@NotEmpty(message="Year must not be empty.")
	private String year;

	private String street;
	private String country;
	private String city;
	private String otherCity;
	public String getOtherCity() {
		return otherCity;
	}

	public void setOtherCity(String otherCity) {
		this.otherCity = otherCity;
	}

	private String captcha;

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getEmailVer() {
		return emailVer;
	}

	public void setEmailVer(String emailVer) {
		this.emailVer = emailVer;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	private String county;

	public void additionalValidations(BindingResult bindingResult) {
		validatePassword(bindingResult);
		validateAge(bindingResult);
		validateEmail(bindingResult);
	}

	private void validateAge(BindingResult bindingResult) {
		int returnCode = DateValidations.hasTheLegalAge(day, month, year);
		if (returnCode == -1) {
			bindingResult.addError(new FieldError("signUpForm", "year",
					"You are not over 18."));
		} else if (returnCode == 0) {
			bindingResult.addError(new FieldError("signUpForm", "year",
					"This is not a valid date."));
		}
	}

	private void validatePassword(BindingResult bindingResult) {
		if (StringUtils.isNotBlank(password)
				&& StringUtils.isNotBlank(passwordCheck)
				&& !password.equals(passwordCheck)) {
			bindingResult.addError(new FieldError("signUpForm",
					"passwordCheck", "The passwords don't match"));
		}
	}

	private void validateEmail(BindingResult bindingResult) {
		
		if (StringUtils.isNotBlank(email)
				&& StringUtils.isNotBlank(emailVer)
				&& !email.equals(emailVer)) {
			bindingResult.addError(new FieldError("signUpForm",
					"emailVer", "The emails don't match."));
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccountForm [firstName=").append(firstName)
				.append(", lastName=").append(lastName).append(", email=")
				.append(email).append(", password=").append(password)
				.append(", gender=").append(gender).append(", address=")
				.append(street).append(", county=").append(county)
				.append("]");
		return builder.toString();
	}
}
