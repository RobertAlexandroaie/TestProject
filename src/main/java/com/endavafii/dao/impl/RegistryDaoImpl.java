package com.endavafii.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.endavafii.dao.RegistryDao;
import com.endavafii.entities.Registry;
import com.endavafii.entities.User;

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
