package com.endava.dao;

import java.util.List;

import com.endava.entities.Bid;

public interface BidDao extends GenericDao<Bid> {

	List<Bid> getBidsByAuctionIdAndUserId(Long userId, Long auctionId);
}
