package com.endavafii.dao;

import java.util.List;

import com.endavafii.entities.Bid;

public interface BidDao extends GenericDao<Bid> {

	List<Bid> getBidsByAuctionIdAndUserId(Long userId, Long auctionId);
}
