package com.endava.dao;

import java.util.List;

import com.endava.entities.Bid;
import com.endava.entities.Registry;
import com.endava.entities.User;


public interface UserDao extends GenericDao<User>{
	

	User getUserByEmail(String email);
	List<Bid> getBidsByUserEmail(String userEmail);
}
