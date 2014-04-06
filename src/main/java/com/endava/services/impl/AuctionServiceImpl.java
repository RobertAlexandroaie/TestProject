package com.endava.services.impl;

import java.io.IOException;
import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.endava.dao.AuctionDao;
import com.endava.dao.CategoryDao;
import com.endava.dao.KeywordDao;
import com.endava.dao.ProductDao;
import com.endava.dao.RestrictionDao;
import com.endava.dao.UserDao;
import com.endava.dto.AuctionDto;
import com.endava.entities.Auction;
import com.endava.entities.Bid;
import com.endava.entities.Category;
import com.endava.entities.ImageFile;
import com.endava.entities.Product;
import com.endava.entities.Restriction;
import com.endava.entities.User;
import com.endava.form.AuctionForm;
import com.endava.form.FileForm;
import com.endava.model.CombinedAuctionKeywords;
import com.endava.model.Keyword;
import com.endava.model.KeywordsListContainer;
import com.endava.model.Location;
import com.endava.model.LocationsListContainer;
import com.endava.services.AuctionService;
import com.endava.util.EmailSenderUtil;

@Service("auctionService")
@EnableScheduling
public class AuctionServiceImpl implements AuctionService {

	@Autowired
	private AuctionDao auctionDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private KeywordDao keywordDao;
	@Autowired
	private RestrictionDao restrictionDao;

	private static AuctionDto auctionToAuctionDto(Auction auction) {

		AuctionDto auctionDto = new AuctionDto();

		auctionDto.setBuyout(auction.getBuyout());
		auctionDto.setCategory(auction.getCategory());
		auctionDto.setEndingTime(auction.getEndingTime());
		auctionDto.setId(auction.getId());
		auctionDto.setLastBid(auction.getLastBid());
		auctionDto.setMinimumBid(auction.getMinimumBid());
		auctionDto.setPaymentMethod(auction.getPaymentMethod());
		auctionDto.setProduct(auction.getProduct());
		auctionDto.setRestrictions(auction.getRestrictions());
		auctionDto.setStep(auction.getStep());
		auctionDto.setUser(auction.getUser());
		auctionDto.setViews(auction.getViews());

		return auctionDto;
	}

	@Override
	public List<AuctionDto> getAuctions() {
		List<Auction> auctions = auctionDao.getAuctions();
		List<AuctionDto> auctionDtoList = new ArrayList<AuctionDto>();

		for (int i = 0; i < auctions.size(); i++) {
			auctionDtoList.add(auctionToAuctionDto(auctions.get(i)));
		}

		return auctionDtoList;
	}
	
	 private  Auction aucFormToAuction(CombinedAuctionKeywords aucForm,MultipartFile file) {
		 System.out.println("file size is : "+ file.getSize()+"filename    is : "+ file.getOriginalFilename());
	        Auction auction = new Auction();
            AuctionForm af=aucForm.getAuctionForm();
            KeywordsListContainer klc=aucForm.getKeywordsListContainer();
            
            List<com.endava.entities.Keyword> keywordsList = new ArrayList<com.endava.entities.Keyword>();           
            for(Keyword k : klc.getKeywordsList()){
            	com.endava.entities.Keyword key= new com.endava.entities.Keyword();
            	key.setName(k.getName());
            	keywordDao.create(key);
            	keywordsList.add(key);
            }

//            //find the category parent object
            Category parentCategory =  new Category();
            List<Category> categories =categoryDao.getCategories();
            for(Category c : categories){
            	if(c.getId() == Integer.parseInt(af.getCategory())){
            		parentCategory = c;
            		break;
            	}
            }              
//            //find the id of the subcategory ( because af.getSubcategory() is the subcategory name )
            Category subcategory = null;
            List<Category> subcategories =categoryDao.getSubCategories(parentCategory.getId());
            for(Category c : subcategories){
            	if(c.getName().equals(af.getSubcategory())){
            		subcategory = c;
            		System.out.println("am setat subcategory" +subcategory.getId());
            		break;
            	}
            }
            
            //get the image
            try {
    			@SuppressWarnings("deprecation")
				Blob blob = Hibernate.createBlob(file.getInputStream());
            
    			FileForm ff= new FileForm ();
    			ff.setFilename(file.getOriginalFilename());
    			ff.setContentType(file.getContentType());
    			ff.setContent(blob);		
    			aucForm.getAuctionForm().setImage(ff);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}  

            ImageFile image = null;
    		try {
    			//bd
    			image = new ImageFile();
    			image.setFilename(aucForm.getAuctionForm().getImage().getFilename());
    			image.setContentType(aucForm.getAuctionForm().getImage().getContentType());
    			image.setContent(aucForm.getAuctionForm().getImage().getContent());
    		
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    		
           
            
          //create the product object for DB
            Product prod=new Product();
            prod.setName(af.getProductName());
            prod.setDescription(af.getProductDescription());
            prod.setImage(image);
            prod.setKeywords(keywordsList);
            productDao.create(prod);
            
            //create the endingTime object
            Date endingTime = null;
    		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    		sdf.setLenient(false);
    		try {
    			endingTime = sdf.parse(af.getDay() + "-" + af.getMonth() + "-" + af.getYear());
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
    	
    		//restriction
    		 LocationsListContainer loc=aucForm.getLocationsListContainer();
             
             List<Restriction> locationsList = new ArrayList<Restriction>();           
             for(Location k : loc.getLocationsList()){
            	 Restriction res=  new Restriction();
            	 res.setName(k.getName());
            	 restrictionDao.create(res);
            	 locationsList.add(res);
             }
             
    		//finally create the action
            auction.setProduct(prod);
            if(af.getBuyout().isEmpty()==false && af.getBuyout()!= null)
                auction.setBuyout(Integer.parseInt(af.getBuyout()));
            auction.setCategory(subcategory);
            auction.setMinimumBid(Integer.parseInt(af.getMinimumBid()));
            auction.setPaymentMethod(af.getPaymentMethod());
            auction.setRestrictions(locationsList);
            if( af.getStep().isEmpty()==false && af.getStep() != null)
                   auction.setStep(Integer.parseInt(af.getStep()));
            auction.setViews(Integer.parseInt("0"));
            auction.setEndingTime(endingTime);
            auction.setStatus("available");
            if( af.getPromoted().isEmpty()==false && af.getPromoted() != null)
                 auction.setPromoted(Integer.parseInt(af.getPromoted()));

	        return auction;
	    }

	  public void setAuction(CombinedAuctionKeywords aucForm, String userEmail,MultipartFile file) {
		Auction ac = aucFormToAuction(aucForm,file);

		User owner = userDao.getUserByEmail(userEmail);
		ac.setUser(owner);
		
		
		auctionDao.create(ac);
	 }

	  private void sold(Auction auction, Date date) {
			Bid lastBid = auction.getLastBid();
			User owner = auction.getUser();
			String message = null;
			
			if(auction.getEndingTime().compareTo(date) < 0) {
				if (lastBid != null) {
					User bidder = lastBid.getUser();
					auction.setStatus("sold");
					message = "Your auction: " + auction.getProduct().getName()
							+ " has been sold to: " + bidder.getFirstName() + " "
							+ bidder.getLastName() + " for " + lastBid.getValue();
				} else {
					auction.setStatus("not sold");
					message = "Your auction has expired and has no bidders.";
				}
				try {
					EmailSenderUtil.sendMail(message, message, "emay@noreply",
							owner.getEmail());
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				auctionDao.update(auction);
			}
		}

		@Scheduled(cron="0 0 0/6 * * *")
		@Async
		@Transactional
		public void checkAuctions() {
			List<Auction> availableAuctions = auctionDao.getAvailableAuctions();
			Date currentDate = new Date();
			for(Auction auction: availableAuctions) {
				sold(auction, currentDate);
			}
		}
		
		@Override
		public AuctionDto getAuctionById(String id) {
			Auction auction = auctionDao.getAuctionById(id);
			return auctionToAuctionDto(auction);
		}
}
