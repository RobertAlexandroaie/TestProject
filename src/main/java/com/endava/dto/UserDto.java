package com.endava.dto;

import java.util.Date;
import java.util.List;

import com.endava.entities.Address;
import com.endava.entities.Auction;
import com.endava.entities.Bid;
import com.endava.entities.Registry;


public class UserDto {

	private long userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Date birthDay;
	private String gender;
	
	private Registry registry;
	private Address address;

	
	private List<Bid> bids;
	private List<Auction> createdAuctions;
	private List<String> keyCategoryWish;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Registry getRegistry() {
		return registry;
	}
	public void setRegistry(Registry registry) {
		this.registry = registry;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Bid> getBids() {
		return bids;
	}
	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}
	public List<Auction> getCreatedAuctions() {
		return createdAuctions;
	}
	public void setCreatedAuctions(List<Auction> createdAuctions) {
		this.createdAuctions = createdAuctions;
	}
	public List<String> getKeyCategoryWish() {
		return keyCategoryWish;
	}
	public void setKeyCategoryWish(List<String> keyCategoryWish) {
		this.keyCategoryWish = keyCategoryWish;
	}	



}
