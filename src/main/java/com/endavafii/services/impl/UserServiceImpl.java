package com.endavafii.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.endavafii.dao.AddressDao;
import com.endavafii.dao.ImageFileDao;
import com.endavafii.dao.RegistryDao;
import com.endavafii.dao.UserDao;
import com.endavafii.entities.Address;
import com.endavafii.entities.Bid;
import com.endavafii.entities.ImageFile;
import com.endavafii.entities.Registry;
import com.endavafii.entities.User;
import com.endavafii.form.SignUpForm;
import com.endavafii.services.UserService;
import com.endavafii.util.DigestUtil;

@Service("enUserService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private RegistryDao registryDao;
	
	@Autowired
	private ImageFileDao imageDao;

	private static User enUserFormToEnUser(SignUpForm signUpForm) {
		User user = new User();

		user.setFirstName(signUpForm.getFirstName());
		user.setLastName(signUpForm.getLastName());

		user.setGender(signUpForm.getGender());
		String city = signUpForm.getCity();
		String otherCity = signUpForm.getOtherCity();
		
		if(!otherCity.equals("")) {
			city=otherCity;
		}
		
		String countryIso = signUpForm.getCountry();
		String street = signUpForm.getStreet();

		Address adress = new Address();
		adress.setCity(city);
		adress.setCountryIso(countryIso);
		adress.setCountryName(new Locale("", countryIso).getDisplayCountry());
		adress.setStreet(street);

		user.setAddress(adress);

		try {
			user.setPassword(signUpForm.getPassword());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		user.setEmail(signUpForm.getEmail());

		Date userDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setLenient(false);
		try {
			userDate = sdf.parse(signUpForm.getDay() + "-"
					+ signUpForm.getMonth() + "-" + signUpForm.getYear());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user.setBirthDay(userDate);

		Registry registry = new Registry();
		try {
			registry.setCode(DigestUtil.digest(user.getEmail()));
		} catch (Exception e2) {

			e2.printStackTrace();
		}
		registry.setStatus("pending");
		// registry.setUser(user);
		user.setRegistry(registry);
		return user;
	}
	
	@Override
	@Transactional
	public void setImage(String email, ImageFile imageFile)
	{
		
		User user = dao.getUserByEmail(email);
		user.setImageFile(imageFile);
		imageDao.create(imageFile);
		dao.update(user);
	
	}

	@Override
	@Transactional
	public void createUser(SignUpForm signUpForm) {
		User user = enUserFormToEnUser(signUpForm);
		addressDao.create(user.getAddress());
		registryDao.create(user.getRegistry());
		dao.create(user);
	}

	@Override
	public List<String> getCodes() {

		return registryDao.getCodes();
	}

	@Override
	public String getUserStatusByEmail(String email) {
		User user = dao.getUserByEmail(email);
		if (user != null) {
			return user.getRegistry().getStatus();
		}
		return "";
	}

	

	@Override
	@Transactional
	public void activateCode(String code) {
		List<Registry> registries = registryDao.getRegistries();
		for (Registry registry : registries) {
			if (registry.getCode().equals(code)) {
				registry.setStatus("registered");
				registryDao.update(registry);
				break;
			}
		}
	}

	@Override
	public List<Bid> getBidsByUserEmail(String email) {
		return dao.getBidsByUserEmail(email);
	}
}
