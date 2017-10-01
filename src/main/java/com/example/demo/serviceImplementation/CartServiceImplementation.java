package com.example.demo.serviceImplementation;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CartService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

/**
 * Created by Admin1 on 9/23/2017.
 */
@Service
public class CartServiceImplementation implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public void addToCart(long idProduct, Principal principal) {

        try {
            User user = userRepository.findByEmail(principal.getName());
            Product product = productsRepository.getOne(idProduct);
            user.getProducts().add(product);
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getCause());
        }


    }



//    @Override
//    public User showProductsInCart() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByEmail(auth.getName());
//        List<Product> productList = productsRepository.findAll();
//        user.setProducts(productList);
//        return user;
//    }


}
