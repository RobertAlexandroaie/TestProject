package com.endava.dao.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.endava.dao.UserDao;
import com.endava.entities.Bid;
import com.endava.entities.Registry;
import com.endava.entities.User;


@Component("enUserDao")
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao{

	@Transactional
 	public User getUserByEmail(String email){
 		
 		Map<String, Object> params = new HashMap<String, Object>();
 		params.put("email", email);
 		
 		List list = this.findByNamedQuery(User.FIND_USER_BY_EMAIL, params);
 		if(list.size()==0) {
 			return null;
 		}
 		return (User) list.get(0);
 	}

	@Override
	@Transactional
	public List<Bid> getBidsByUserEmail(String userEmail) {
		Map<String, Object> params = new HashMap<String, Object>();
 		params.put("email", userEmail);
 		
 		List<Bid> list = this.findByNamedQuery(User.FIND_BIDS_BY_EMAIL, params);
 		
 		return list;
	}
}
