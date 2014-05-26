package com.endavafii.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String city;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		if (city.equals("default") || city.equals("City")) {
			this.city = "";
		} else {
			this.city = city;
		}
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCountryIso() {
		return countryIso;
		
	}

	public void setCountryIso(String countryIso) {
		if (countryIso.equals("default") || countryIso.equals("Country")) {
			this.countryIso = "";
		} else {
			this.countryIso = countryIso;
		}
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String street;

	private String countryIso;

	private String zipCode;
	
	private String countryName;

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}
