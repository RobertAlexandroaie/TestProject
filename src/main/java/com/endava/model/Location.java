package com.endava.model;

public class Location {

	private String name;
	
	public Location(){
		
	}
     public Location(String name){
    	 this.name= name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override 
	public String toString() {
	    return name.toString();
	  }
}
