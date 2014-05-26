package com.endavafii.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.endavafii.dao.AuctionDao;
import com.endavafii.entities.Auction;
import com.endavafii.entities.User;

@Component("auctionDao")
public class AuctionDaoImpl extends GenericDaoImpl<Auction> implements AuctionDao {

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Auction> getAuctions() {
		return this.findByNamedQuery(Auction.FIND_AUCTIONS);
	}

	@Override
	@Transactional
	public Auction getAuctionById(String id) {

 		Map<String, Object> params = new HashMap<String, Object>();
 		params.put("id", Long.parseLong(id));
    	
		List<Auction> auctions = this.findByNamedQuery(Auction.FIND_AUCTION_BY_ID, params);
		
 		if(auctions.size()==0) {
 			return null;
 		}
		return auctions.get(0);
	}

	@Override
	@Transactional
	public List<Auction> getAvailableAuctions() {
		return this.findByNamedQuery(Auction.FIND_AVAILABLE_AUCTIONS);
	}
}
