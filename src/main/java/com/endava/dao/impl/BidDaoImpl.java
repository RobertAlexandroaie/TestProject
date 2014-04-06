package com.endava.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.endava.dao.BidDao;
import com.endava.entities.Bid;

@Component("bidDao")
public class BidDaoImpl extends GenericDaoImpl<Bid> implements BidDao {

	@Override
	@Transactional
	public List<Bid> getBidsByAuctionIdAndUserId(Long userId, Long auctionId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("auctionId", auctionId);
		return (List<Bid>)this.findByNamedQuery(Bid.FIND_BIDS_BY_AUCTIONID_AND_USERID, params);
	}


}
