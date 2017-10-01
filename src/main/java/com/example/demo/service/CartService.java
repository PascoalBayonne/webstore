package com.example.demo.service;

import java.security.Principal;

/**
 * Created by Admin1 on 9/23/2017.
 */
public interface CartService {

    void addToCart(long idProduct, Principal principal);
//    User showProductsInCart(long idProduct, Principal principal);
}
