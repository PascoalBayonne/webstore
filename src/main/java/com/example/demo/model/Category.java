package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Admin1 on 9/1/2017.
 */
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String category;


    @OneToMany(mappedBy = "category")
    List<Product> products;

    public Category() {
    }

    public Category(String category, List<Product> products) {
        this.category = category;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
