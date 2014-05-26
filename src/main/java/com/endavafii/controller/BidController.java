package com.endavafii.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.endavafii.services.AuctionService;
import com.endavafii.services.BidService;

@Controller
@RequestMapping("/details")
public class BidController {

	@Autowired
	private AuctionService auctionService;

	@Autowired
	private BidService bidService;

	@RequestMapping(value = "/bid", method = RequestMethod.POST)
	public String bid(@RequestParam("auctionId") String auctionId,
			@RequestParam("value") String value, HttpSession session) {

		String userEmail = (String) session.getAttribute("user_name");
		bidService.createBid(value, auctionId, userEmail);
		return "details";
	}

	@RequestMapping(value = "/buyout", method = RequestMethod.POST)
	public String buyout(@RequestParam("auctionId") String auctionId,
			HttpSession session) {

		String userEmail = (String) session.getAttribute("user_name");
		bidService.buyout(auctionId, userEmail);
		return "details";
	}
}
