package com.endavafii.model;

import java.util.LinkedList;
import java.util.List;

import com.endavafii.entities.Image;

public class ImagesListContainer {
	private List<Image> imagesList = new LinkedList<Image>(); 
	
	public ImagesListContainer(){
		
	}
    public ImagesListContainer(List<Image> imagesList){
    	this.imagesList=imagesList;		
	}
	public List<Image> getImagesList() {
		return imagesList;
	}
	public void setImagesList(List<Image> imagesList) {
		this.imagesList = imagesList;
	}
    

}
