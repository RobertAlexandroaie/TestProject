package com.endava.dao;

import java.util.List;

import com.endava.entities.Registry;

public interface RegistryDao extends GenericDao<Registry>{

	List<String> getCodes();
	List<Registry> getRegistries();
}
