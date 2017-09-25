package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.model.User;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Admin1 on 9/23/2017.
 */
@Controller
public class CartController {
    @Autowired
    private CartRepository cartRepository;

//    @Autowired
//    private CartService cartService;

    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping("/cart")
    public ModelAndView OverView(Cart cart) {
        ModelAndView mv = new ModelAndView("/cart");
        mv.addObject("allProductsInCart", cartRepository.findAll());
        return mv;
    }

    @PostMapping("/cart/saveProduct")
    public ModelAndView cart(@AuthenticationPrincipal User user, Cart cart){
        ModelAndView modelAndView = new ModelAndView();
       cartRepository.save(cart);
       return new ModelAndView("redirect:/cart");


    }
}
