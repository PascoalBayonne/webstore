package com.example.demo.serviceImplementation;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Admin1 on 9/23/2017.
 */
@Service
public class CartServiceImplementation implements CartService{
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductsRepository productsRepository;


    @Override
    public void addProductIntoCart(Cart cart, String[] productId) {
        cartRepository.saveAndFlush(cart);
        for (int i =0; i<productId.length;i++){
            Product product = productsRepository.findOne(Long.parseLong(productId[i]));
            product.setCart(cart);
            productsRepository.save(product);
        }
    }

    @Override
    public void deleteProductFromCart(String productId) {

    }
}
