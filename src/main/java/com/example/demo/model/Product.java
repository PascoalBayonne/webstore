package com.example.demo.model;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@NotEmpty(message="*Please provide a name for this product")
	private String name;
	private String description;
	private double price;
	
	@Length(max=20,message="*Short description cannot have more than 20 characters")
	private String shortDescription;
	
	private byte[] image;
	private String imageUrl;
	
	@ManyToMany
	@JoinTable(name="user_product", joinColumns=@JoinColumn(name="product_id"),inverseJoinColumns=@JoinColumn(name="user_id") )
	private List<User>users;
	
	@OneToMany(mappedBy ="product")
	private List<Review> review;
	
	@ManyToMany
	@JoinTable(name="product_cart", joinColumns=@JoinColumn(name="product_id"), inverseJoinColumns=@JoinColumn(name="cart_id"))
	private List<Cart>carts;

	@ManyToOne
	Category category;

	public Product(){
		
	}

	public Product(String name, String description, double price, String shortDescription, byte[] image, String imageUrl, List<User> users, List<Review> review, List<Cart> carts, Category category) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.shortDescription = shortDescription;
		this.image = image;
		this.imageUrl = imageUrl;
		this.users = users;
		this.review = review;
		this.carts = carts;
		this.category = category;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Review> getReview() {
		return review;
	}

	public void setReview(List<Review> review) {
		this.review = review;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
