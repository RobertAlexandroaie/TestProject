package com.endava.dto;

import java.util.Date;
import java.util.List;

import com.endava.entities.Bid;
import com.endava.entities.Category;
import com.endava.entities.Product;
import com.endava.entities.Restriction;
import com.endava.entities.User;

public class AuctionDto {

	
	private long id;
	private Product product;
	private Category category;
	private Integer minimumBid;
	private Integer buyout;
	private Date endingTime;
	private List<Restriction> restrictions;
	private String paymentMethod;	
	private int step;	
	private User user;
	private Bid lastBid;	
	private int views;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Integer getMinimumBid() {
		return minimumBid;
	}
	public void setMinimumBid(Integer minimumBid) {
		this.minimumBid = minimumBid;
	}
	public Integer getBuyout() {
		return buyout;
	}
	public void setBuyout(Integer buyout) {
		this.buyout = buyout;
	}
	public Date getEndingTime() {
		return endingTime;
	}
	public void setEndingTime(Date endingTime) {
		this.endingTime = endingTime;
	}
	public List<Restriction> getRestrictions() {
		return restrictions;
	}
	public void setRestrictions(List<Restriction> list) {
		this.restrictions = list;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Bid getLastBid() {
		return lastBid;
	}
	public void setLastBid(Bid lastBid) {
		this.lastBid = lastBid;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	
}
