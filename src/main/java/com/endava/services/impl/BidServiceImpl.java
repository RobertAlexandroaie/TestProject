package com.endava.services.impl;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.endava.dao.AuctionDao;
import com.endava.dao.BidDao;
import com.endava.dao.UserDao;
import com.endava.dto.BidDto;
import com.endava.entities.Auction;
import com.endava.entities.Bid;
import com.endava.entities.User;
import com.endava.services.BidService;
import com.endava.util.EmailSenderUtil;

@Service("bidService")
public class BidServiceImpl implements BidService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private AuctionDao auctionDao;

	@Autowired
	private BidDao bidDao;

	private static BidDto bidFromBidDto(Bid bid) {
		BidDto bidDto = new BidDto();

		bidDto.setAuction(bid.getAuction());
		bidDto.setId(bid.getId());
		bidDto.setUser(bid.getUser());
		bidDto.setValue(bid.getValue());

		return bidDto;
	}

	@Override
	@Transactional
	public void createBid(String value, String auctionId, String userEmail) {
		User user = userDao.getUserByEmail(userEmail);
		Auction auction = auctionDao.getAuctionById(auctionId);
		
		List<Bid> bidsOfUserOnAuction = bidDao.getBidsByAuctionIdAndUserId(user.getUserId(), auction.getId());
		Bid bid = null;
		if(bidsOfUserOnAuction!=null && bidsOfUserOnAuction.size()>0) {
			bid = bidsOfUserOnAuction.get(0);
			bid.setValue(Integer.parseInt(value));
			bidDao.update(bid);
		} else {
			bid = new Bid();
			bid.setValue(Integer.parseInt(value));
			bid.setUser(user);
			bid.setAuction(auction);
			bidDao.create(bid);	
		}	
		auction.setLastBid(bid);
		auctionDao.update(auction);
		userDao.update(user);
	}

	@Override
	@Transactional
	public void buyout(String auctionId, String userEmail) {
		User user = userDao.getUserByEmail(userEmail);
		Auction auction = auctionDao.getAuctionById(auctionId);
		
		List<Bid> bidsOfUserOnAuction = bidDao.getBidsByAuctionIdAndUserId(user.getUserId(), auction.getId());

		Bid bid = null;
		if(bidsOfUserOnAuction!=null && bidsOfUserOnAuction.size()>0) {
			bid = bidsOfUserOnAuction.get(0);
			bid.setValue(auction.getBuyout());
			bidDao.update(bid);
		} else {
			bid = new Bid();
			bid.setValue(auction.getBuyout());
			bid.setUser(user);
			bid.setAuction(auction);	
			bidDao.create(bid);	
		}
		auction.setStatus("sold");
		auctionDao.update(auction);
		userDao.update(user);
	}
}
