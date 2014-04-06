package com.endava.dao;

import java.util.List;

import com.endava.entities.Auction;

public interface AuctionDao extends GenericDao<Auction> {
	List<Auction> getAuctions();
	Auction getAuctionById(String id);
	List<Auction> getAvailableAuctions();
}
