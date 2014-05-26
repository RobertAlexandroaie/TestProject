package com.endavafii.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.endavafii.dto.CategoryDto;
import com.endavafii.entities.Image;
import com.endavafii.form.AuctionForm;
import com.endavafii.model.CombinedAuctionKeywords;
import com.endavafii.model.ImagesListContainer;
import com.endavafii.model.Keyword;
import com.endavafii.model.KeywordsListContainer;
import com.endavafii.model.Location;
import com.endavafii.model.LocationsListContainer;
import com.endavafii.services.AuctionService;
import com.endavafii.services.CategoryService;
import com.endavafii.util.LoginFormInclude;

@Controller
@RequestMapping("auction")
public class AuctionController {
	private static final Logger LOGGER = Logger
			.getLogger(AccountController.class);

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private AuctionService auctionService;

	private static List<CategoryDto> categories;

	@PostConstruct
	public void postInit() {
		categories = categoryService.getCategories();

	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addAccount(Model model, ModelMap map, HttpSession session,
			HttpServletRequest request,
			@RequestParam(value = "f", required = false) String flush,
			@RequestParam(value = "message", required = false) String message) {
		if (flush != null)
			session.setAttribute("allListContainer",
					getDummyCombinedAuctionKeywords());
		// if( session.getAttribute("keysListContainer") == null )
		session.setAttribute("allListContainer",
				getDummyCombinedAuctionKeywords());
		map.addAttribute("allListContainer", (CombinedAuctionKeywords) session
				.getAttribute("allListContainer"));
		if (message != null)
			map.addAttribute("message", message);

		model.addAttribute("categoriesCount", categories.size());
		model.addAttribute("categories", categories);

		model.addAttribute(new CombinedAuctionKeywords(new AuctionForm(),
				new KeywordsListContainer(),
				new  LocationsListContainer(),
				new  ImagesListContainer()));
		LoginFormInclude.addLoginForm(model);
		return "auction/add";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String addAuctionPost(
			@Valid CombinedAuctionKeywords combinedAuctionKeywords,
			BindingResult bindingResult, Model model,
			@ModelAttribute CombinedAuctionKeywords combinedAuctionKeywordsAtribute,
			HttpSession session,
			@RequestParam("file") MultipartFile file) {
		
		
		//for keywords list
		KeywordsListContainer keywordsListContainer= combinedAuctionKeywordsAtribute.getKeywordsListContainer();	
		for (int index = 0; index < keywordsListContainer.getKeywordsList()
				.size(); index++) {
			String elm = keywordsListContainer.getKeywordsList().get(index)
					.getName();
			if (elm.isEmpty() == true) {
				keywordsListContainer.getKeywordsList().remove(index);
				index--;
			}
		}
		System.out
				.println("listaa keywords : " + keywordsListContainer.getKeywordsList());
		combinedAuctionKeywordsAtribute.setKeywordsListContainer(keywordsListContainer);
		combinedAuctionKeywords.setKeywordsListContainer(keywordsListContainer);
		
		//for locations list
        LocationsListContainer locationsListContainer= combinedAuctionKeywordsAtribute.getLocationsListContainer();	
		for (int index = 0; index < locationsListContainer.getLocationsList()
				.size(); index++) {
			String elm = locationsListContainer.getLocationsList().get(index)
					.getName();
			if (elm.isEmpty() == true) {
				locationsListContainer.getLocationsList().remove(index);
				index--;
			}
		}
		System.out
		.println("listaa locations : " + locationsListContainer.getLocationsList());
		combinedAuctionKeywordsAtribute.setLocationsListContainer(locationsListContainer);
		combinedAuctionKeywords.setLocationsListContainer(locationsListContainer);
	
		
		if( keywordsListContainer.getKeywordsList().isEmpty() ==true){
			bindingResult.addError(new FieldError("auctionForm", "end", "You must have at least a keyword"));
			combinedAuctionKeywords.getAuctionForm().setEnd("You must have at least a keyword");
		}
		else{
			combinedAuctionKeywords.getAuctionForm().additionalValidations(bindingResult);
			
			LOGGER.debug(bindingResult);
			System.out.println("binding: "+bindingResult.toString());
		}
		if ((!bindingResult.hasErrors())) {
			auctionService.setAuction(combinedAuctionKeywords, (String)session.getAttribute("user_name"),file);
			return "auction/success";
		}

		session.setAttribute("allListContainer", combinedAuctionKeywordsAtribute);
		model.addAttribute(combinedAuctionKeywords);
		model.addAttribute("categoriesCount", categories.size());
		model.addAttribute("categories", categories);

		return "auction/add";
	}

	private CombinedAuctionKeywords  getDummyCombinedAuctionKeywords() {		
		List<Keyword> keywordsList = new ArrayList<Keyword>();
		keywordsList.add(new Keyword(" "));
		KeywordsListContainer keywordsListContainer=new KeywordsListContainer(keywordsList);
		
		List<Location> locationsList = new ArrayList<Location>();
		locationsList.add(new Location(" "));
		LocationsListContainer locationsListContainer=new LocationsListContainer(locationsList);
		
		List<Image> imagesList = new ArrayList<Image>();
		imagesList.add(new Image(" "));
		ImagesListContainer imagesListContainer=new ImagesListContainer(imagesList);
		
		return new CombinedAuctionKeywords (new AuctionForm(),keywordsListContainer, locationsListContainer, imagesListContainer);
	}
 
	@RequestMapping(value = "getSubcategory", method = RequestMethod.GET)
	public @ResponseBody
	List<CategoryDto> getSubcategories(
			@RequestParam("category") String categoryId) {
		List<CategoryDto> subcategories = categoryService.getSubCategories(Long
				.parseLong(categoryId));
		return subcategories;
	}

}
