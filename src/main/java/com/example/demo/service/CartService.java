package com.example.demo.service;

import com.example.demo.model.Product;

import java.security.Principal;

/**
 * Created by Admin1 on 9/23/2017.
 */
public interface CartService {

    void addToCart(long idProduct, Principal principal); //The principal is the currently logged in user. Spring Security
    void removeProductInCart(long idProduct,Principal principal);
    double totalPrice(Product product, Principal principal);
}
