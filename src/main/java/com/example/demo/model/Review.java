package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String text;
	private Integer rating;
	private Date date;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Product product;

	public Review(){

	}

	public Review(String text, Integer rating, Date date, User user, Product product) {
		super();
		this.text = text;
		this.rating = rating;
		this.date = date;
		this.user = user;
		this.product = product;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
		
}
