package com.example.demo.service;

import com.example.demo.model.Cart;

/**
 * Created by Admin1 on 9/23/2017.
 */
public interface CartService {
    void addProductIntoCart(Cart cart, String [] productId );
    void deleteProductFromCart(String productId);
}
