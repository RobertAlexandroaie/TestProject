package com.endavafii.entities;

import java.io.Serializable;

import javax.persistence.Column;
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
@NamedQueries({
    @NamedQuery(name = Bid.FIND_BIDS, query = "select a from Bid a"),
    @NamedQuery(name = Bid.FIND_BIDS_BY_AUCTIONID_AND_USERID, query = "select a from Bid a where a.user.id = :userId and a.auction.id = :auctionId")
})
@Entity
@Table(name="bids")
public class Bid implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final String FIND_BIDS = "Bid.FIND_BIDS";
	public static final String FIND_BIDS_BY_AUCTIONID_AND_USERID = "Bid.FIND_BIDS_BY_AUCTIONID_AND_USERID";
	@OneToOne(mappedBy = "lastBid")
	@JsonBackReference
	private Auction auction;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;	
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonBackReference
	private User user;

	@Column(name="value")
	private int value;


	public Auction getAuction() {
		return auction;
	}
	
	
	public long getId() {
		return id;
	}


	public User getUser() {
		return user;
	}


	public int getValue() {
		return value;
	}


	public void setAuction(Auction auction) {
		this.auction = auction;
	}


	public void setId(long id) {
		this.id = id;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public void setValue(int value) {
		this.value = value;
	}
}
