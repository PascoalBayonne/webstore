package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Integer quantity;
	private Date dateAdded;

	@OneToMany(mappedBy = "cart")
	private Set<Product> products = new HashSet<>();

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Cart() {

	}

	public Cart(Integer quantity, Date dateAdded, Set<Product> products, User user) {
		this.quantity = quantity;
		this.dateAdded = dateAdded;
		this.products = products;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}


	public Set<Product> getProducts() {
		return products;
	}


	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
