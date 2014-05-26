package com.endavafii.model;

import java.util.LinkedList;
import java.util.List;

public class LocationsListContainer {
	private List<Location> locationsList = new LinkedList<Location>(); 
	
	public LocationsListContainer(){
		
	}
     public LocationsListContainer(List<Location> locationsList){
    	 this.locationsList = locationsList;
		
	}
	public List<Location> getLocationsList() {
		return locationsList;
	}
	public void setLocationsList(List<Location> locationsList) {
		this.locationsList = locationsList;
	}
     
     

}
