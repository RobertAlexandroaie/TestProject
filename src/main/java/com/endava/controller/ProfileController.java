package com.endava.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.endava.dao.UserDao;
import com.endava.entities.ImageFile;
import com.endava.entities.User;
import com.endava.form.FileForm;
import com.endava.form.SignUpForm;
import com.endava.services.AuthenticateService;
import com.endava.services.UserService;



@Controller
public class ProfileController {
	

	@Autowired
    private ServletContext servletContext;
	
	@Autowired
	private AuthenticateService authService;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserService userService;
	
		
	@RequestMapping(value = "/save", method = RequestMethod.POST)
		public String save(
            @ModelAttribute("uploadForm") FileForm fileForm,
                    Model map, HttpServletRequest request,
                    HttpServletResponse response, HttpSession session, @RequestParam("file") MultipartFile file) throws IOException{
	
        
		try {
			Blob blob = Hibernate.createBlob(file.getInputStream());

			fileForm.setFilename(file.getOriginalFilename());
			fileForm.setContent(blob);
			fileForm.setContentType(file.getContentType());
		} catch (IOException e) {
			e.printStackTrace();
		}  

		try {
			
			ImageFile image = new ImageFile();
			image.setFilename(fileForm.getFilename());
			image.setContentType(fileForm.getContentType());
			image.setContent(fileForm.getContent());
			userService.setImage(session.getAttribute("user_name").toString(), image);
		
		} catch(Exception e) {
			e.printStackTrace();
		}
      
        return "account/profile";
    }
	
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public String displayImage(HttpServletResponse response, HttpSession session) {
		
		User user = userDao.getUserByEmail(session.getAttribute("user_name").toString());
		
		ImageFile image = user.getImageFile();
		
		try{
		response.setHeader("Content-Disposition", "inline;filename=\"" +image.getFilename()+ "\"");
			OutputStream out = response.getOutputStream();
			response.setContentType(image.getContentType());
			IOUtils.copy(image.getContent().getBinaryStream(), out);
			out.flush();
			out.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			
		}

		return "account/profile";
		
		
		
	}
	
	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public String accountSettings(@ModelAttribute("signUpForm") SignUpForm signUpForm){
		
		
		
		
		return "account/accountSettings";
		
		
	}

}
