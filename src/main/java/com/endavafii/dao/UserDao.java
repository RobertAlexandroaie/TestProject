package com.endavafii.dao;

import java.util.List;

import com.endavafii.entities.Bid;
import com.endavafii.entities.Registry;
import com.endavafii.entities.User;


public interface UserDao extends GenericDao<User>{
	

	User getUserByEmail(String email);
	List<Bid> getBidsByUserEmail(String userEmail);
}
