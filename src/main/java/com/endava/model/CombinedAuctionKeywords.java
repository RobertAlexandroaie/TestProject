package com.endava.model;

import javax.validation.Valid;

import com.endava.form.AuctionForm;

public class CombinedAuctionKeywords {
	 @Valid AuctionForm auctionForm;
	 KeywordsListContainer keywordsListContainer;
	 LocationsListContainer  locationsListContainer;
	 ImagesListContainer imagesListContainer;
	   
	   
	 public CombinedAuctionKeywords(){	 
	 }
	 public CombinedAuctionKeywords(AuctionForm auctionForm, KeywordsListContainer keywordsListContainer,  LocationsListContainer  locationsListContainer, ImagesListContainer imagesListContainer){	 
		 this.auctionForm=auctionForm;
		 this.keywordsListContainer=keywordsListContainer;
		 this.locationsListContainer=  locationsListContainer;
		 this.imagesListContainer= imagesListContainer;
	 }
	  
	   
	public AuctionForm getAuctionForm() {
		return auctionForm;
	}
	public void setAuctionForm(AuctionForm auctionForm) {
		this.auctionForm = auctionForm;
	}
	public KeywordsListContainer getKeywordsListContainer() {
		return keywordsListContainer;
	}
	public void setKeywordsListContainer(KeywordsListContainer keywordsListContainer) {
		this.keywordsListContainer = keywordsListContainer;
	}
	public LocationsListContainer getLocationsListContainer() {
		return locationsListContainer;
	}
	public void setLocationsListContainer(
			LocationsListContainer locationsListContainer) {
		this.locationsListContainer = locationsListContainer;
	}
	public ImagesListContainer getImagesListContainer() {
		return imagesListContainer;
	}
	public void setImagesListContainer(ImagesListContainer imagesListContainer) {
		this.imagesListContainer = imagesListContainer;
	}
	
	 
	   

}
