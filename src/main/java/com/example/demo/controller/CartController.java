package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.filter.ProductFilter;
import com.example.demo.service.CartService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by Admin1 on 9/23/2017.
 */
@Controller
public class CartController {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/cart")
    public ModelAndView cart(ProductFilter productFilter){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserName());
        modelAndView.addObject("adminMessage","Content Available Only for Authenticated Users");
        modelAndView.addObject("allProducts",user.getProducts());
        modelAndView.setViewName("/cart");
        return modelAndView;
    }

    @GetMapping("/cart/{idProduct}")
    public String addToCart(Model model, @PathVariable long idProduct, Principal principal) {
       cartService.addToCart(idProduct,principal);
        return "redirect:/cart";
    }

    //payment section
    @GetMapping(value = "/payment")
    public String payment(){
        return "payment";
    }



}
