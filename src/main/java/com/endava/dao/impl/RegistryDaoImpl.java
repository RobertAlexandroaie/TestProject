package com.endava.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.endava.dao.RegistryDao;
import com.endava.entities.Registry;
import com.endava.entities.User;

@Component("registryDao")
public class RegistryDaoImpl extends GenericDaoImpl<Registry> implements RegistryDao{

	@Override
	@Transactional
	public List<String> getCodes() {
        return this.findByNamedQuery(Registry.FIND_CODES);
	}

	@Override
	@Transactional
	public List<Registry> getRegistries() {
		
		return this.findByNamedQuery(Registry.FIND_ALL);
	}

}
