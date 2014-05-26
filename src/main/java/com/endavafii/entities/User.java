package com.endavafii.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.endavafii.util.DigestUtil;

@NamedQueries({
    @NamedQuery(name = User.FIND_USER_BY_EMAIL, query = "select e from User e WHERE e.email = :email"),
    @NamedQuery(name = User.FIND_BIDS_BY_EMAIL, query = "select e.bids from User e WHERE e.email = :email")	
})
@Entity
@Table(name = "en_users")
public class User implements Serializable {
    public static final String FIND_USER_BY_EMAIL = "User.FIND_USER_BY_EMAIL";
    public static final String FIND_BIDS_BY_EMAIL = "User.FIND_BIDS_BY_EMAIL";
	private static final long serialVersionUID = 1L;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Bid> bids;

	private Date birthDay;
	
	@OneToMany(mappedBy = "user")
	@JsonManagedReference
	private List<Auction> createdAuctions;

	private String email;

	@Column(name = "first_name")
	private String firstName;

	private String gender;

	@ElementCollection
	@Column(name = "keys_wish")
	private List<String> keyCategoryWish;

	@Column(name = "last_name")
	private String lastName;
	
	private String password;

	@OneToOne
	private Registry registry;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private long userId;

	@OneToMany
	@JsonManagedReference
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Category> wishesCategoryList;
	
	@OneToOne
	private ImageFile imageFile;

	public Address getAddress() {
		return address;
	}

	public List<Bid> getBids() {
		return bids;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public List<Auction> getCreatedAuctions() {
		return createdAuctions;
	}
	
	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getGender() {
		return gender;
	}


	public List<String> getKeyCategoryWish() {
		return keyCategoryWish;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public Registry getRegistry() {
		return registry;
	}



	public ImageFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(ImageFile imageFile) {
		this.imageFile = imageFile;
	}
	public long getUserId() {
		return userId;
	}

	public List<Category> getWishesCategoryList() {
		return wishesCategoryList;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public void setCreatedAuctions(List<Auction> createdAuctions) {
		this.createdAuctions = createdAuctions;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setKeyCategoryWish(List<String> keyCategoryWish) {
		this.keyCategoryWish = keyCategoryWish;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) throws Exception {
	
		this.password = DigestUtil.digest(password);
	}

	public void setRegistry(Registry registry) {
		this.registry = registry;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setWishesCategoryList(List<Category> wishesCategoryList) {
		this.wishesCategoryList = wishesCategoryList;
	}

}
