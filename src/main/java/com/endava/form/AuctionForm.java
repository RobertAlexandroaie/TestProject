package com.endava.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import com.endava.form.util.DateValidations;

public class AuctionForm {
	@NotEmpty(message="Product name is required.")
	 private String productName;
	 
	@NotEmpty(message="Product description is required.")
	 private String productDescription;
	
	private String category;
	
	private String subcategory;

	private FileForm image;
	
	@NotEmpty(message="Minimum bid is required.")
	@Pattern(regexp="[1-9][0-9]*", message="Must be a number")
	private String minimumBid;

    private String buyout;

	@NotNull(message="Day must not be empty.")
	@NotEmpty(message="Day must not be empty.")
	private String day;
	@NotNull(message="Month must not be empty.")
	@NotEmpty(message="Month must not be empty.")
	private String month;
	@NotNull(message="Year must not be empty.")
	@NotEmpty(message="Year must not be empty.")
	private String year;

	private String restrictions;//locations

	@NotEmpty(message="The payment method is required.")
	private String paymentMethod;

	//@NotEmpty(message="Bid step is required.")
	//@Pattern(regexp="[1-9][0-9]*", message="Must be a number")
	private String step;
		
    private String promoted;
    
	private String end="";
	

	public String getPromoted() {
		return promoted;
	}

	public void setPromoted(String promoted) {
		this.promoted = promoted;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public FileForm getImage() {
		return image;
	}

	public void setImage(FileForm image) {
		this.image = image;
	}

	public String getMinimumBid() {
		return minimumBid;
	}

	public void setMinimumBid(String minimumBid) {
		this.minimumBid = minimumBid;
	}

	public String getBuyout() {
		return buyout;
	}

	public void setBuyout(String buyout) {
		this.buyout = buyout;
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

	public String getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	 public void additionalValidations(BindingResult bindingResult) {
	        validateDate(bindingResult);
	    }

	    private void validateDate(BindingResult bindingResult) {
	    	Date endingTime = null;
    		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    		sdf.setLenient(false);
    		try {
    			endingTime = sdf.parse(day + "-" + month + "-" + year);
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
    		Date now= new Date();
    		System.out.println(now);
    		if(now.before(endingTime)==false){
    			bindingResult.addError(new FieldError("auctionForm", "end", "This is not a valid date"));
    			this.end= "The auction expiration date must be in the future!";
    		}
    		else{
    			if(buyout != null && buyout.isEmpty()==false && minimumBid !=null && minimumBid.isEmpty()==false){
	    			int buy = Integer.parseInt(buyout);
	    			int min = Integer.parseInt(minimumBid);
	    			  if (buy <= min ){
	    				bindingResult.addError(new FieldError("auctionForm", "end", "Buyout must be greater than minimum bid"));
	        			this.end= "Buyout must be greater than minimum bid";
	    			}
    			}
    		}
       
	    }

	

}
