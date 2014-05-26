package com.endavafii.dao;

import java.util.List;

import com.endavafii.entities.Registry;

public interface RegistryDao extends GenericDao<Registry>{

	List<String> getCodes();
	List<Registry> getRegistries();
}
