package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping(value = "/cart")
    public ModelAndView cart(@AuthenticationPrincipal Product product){
        ModelAndView mv = new ModelAndView("/cart");

        return mv;
    }

    @GetMapping("/cart/{idProduct}")
    public String addToCart(Model model, @PathVariable long idProduct, Principal principal) {
       cartService.addToCart(idProduct,principal);
        return "redirect:/cart";
    }


}
