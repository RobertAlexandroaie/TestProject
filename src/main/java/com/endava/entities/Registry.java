package com.endava.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NamedQueries({
		@NamedQuery(name = Registry.FIND_CODES, query = "select a.code from Registry a"),
		@NamedQuery(name = Registry.FIND_ALL, query = "select a from Registry a")

})
@Entity
@Table(name = "registry")
public class Registry implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String FIND_CODES = "Registry.FIND_CODES";
	public static final String FIND_ALL = "Registry.FIND_ALL";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "registry_id")
	private long registryId;

	private String status;

	private String code;

	public long getRegistryId() {
		return registryId;
	}

	public void setRegistryId(long registryId) {
		this.registryId = registryId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return registryId + " " + status;
	}
}
