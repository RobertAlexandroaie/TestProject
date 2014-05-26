package com.endavafii.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@NamedQueries({
		@NamedQuery(name = Auction.FIND_AUCTIONS, query = "select a from Auction a where MOD(a.id, 37) = 0"),
		@NamedQuery(name = Auction.FIND_AUCTION_BY_ID, query = "select a from Auction a where a.id = :id"),
		@NamedQuery(name = Auction.FIND_AVAILABLE_AUCTIONS, query = "select a from Auction a where MOD(a.id, 37) = 0 ") })
@Entity
@Table(name = "auctions")
public class Auction implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_AUCTIONS = "Auction.FIND_AUCTIONS";
	public static final String FIND_AUCTION_BY_ID = "Auction.FIND_AUCTION_BY_ID";
	public static final String FIND_AVAILABLE_AUCTIONS = "Auction.FIND_AVAILABLE_AUCTIONS";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonBackReference
	private Product product;

	@ManyToOne
	@JsonBackReference
	private Category category;

	@Column(name = "minimum_bid")
	private Integer minimumBid;

	@Column
	private Integer buyout;

	@Column(name = "ending_time")
	private Date endingTime;

	@ElementCollection
	@Column(name = "restrictions")
	private List<Restriction> restrictions;

	@Column(name = "payment_method")
	private String paymentMethod;

	@Column(name = "step")
	private int step;

	private int promoted;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;

	@OneToOne
	@JsonManagedReference
	private Bid lastBid;

	private int views;

	private String status;

	@OneToOne
	private ImageFile imageFile;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public int getPromoted() {
		return promoted;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public void setPromoted(int promoted) {
		this.promoted = promoted;
	}

	public List<Restriction> getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(List<Restriction> restrictions) {
		this.restrictions = restrictions;
	}

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

	public Date getEndingTime() {
		return endingTime;
	}

	public void setEndingTime(Date endingTime) {
		this.endingTime = endingTime;
	}

	public Bid getLastBid() {
		return lastBid;
	}

	public void setLastBid(Bid lastBid) {
		// trebuie anuntat this.lastBid.getUser()
		this.lastBid = lastBid;
	}

}
