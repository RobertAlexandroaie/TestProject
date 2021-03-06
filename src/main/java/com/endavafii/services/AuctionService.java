package com.endavafii.services;

import java.util.Date;
import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.endavafii.dto.AuctionDto;
import com.endavafii.entities.Auction;
import com.endavafii.model.CombinedAuctionKeywords;

public interface AuctionService {

	List<AuctionDto> getAuctions();

	AuctionDto getAuctionById(String id);

	@Transactional
	void setAuction(CombinedAuctionKeywords aucForm, String userEmail,MultipartFile file);

	@Scheduled(cron = "0 0 0/6 * * *")
	@Async
	@Transactional
	public void checkAuctions();

}
