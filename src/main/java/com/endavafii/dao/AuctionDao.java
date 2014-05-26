package com.endavafii.dao;

import java.util.List;

import com.endavafii.entities.Auction;

public interface AuctionDao extends GenericDao<Auction> {
	List<Auction> getAuctions();
	Auction getAuctionById(String id);
	List<Auction> getAvailableAuctions();
}
