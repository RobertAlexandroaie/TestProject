package com.endava.services;

import org.springframework.transaction.annotation.Transactional;

public interface BidService {

	@Transactional
    void createBid(String value, String auctionId, String userEmail);

	@Transactional
    void buyout(String auctionId, String userEmail);
	
}
