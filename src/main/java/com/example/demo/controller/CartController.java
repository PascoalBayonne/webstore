package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private UserService userService;

    @GetMapping(value = "/cart")
    public ModelAndView cart(ProductFilter productFilter){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();// security context is in the session and contains user/principal and roles/authorities.
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


    @RequestMapping( value = "/cart/delete/{idProduct}")
    public String removeFromCart(@PathVariable long idProduct, Principal principal, RedirectAttributes redirectAttributes){

        cartService.removeProductInCart(idProduct,principal);
        redirectAttributes.addFlashAttribute("message","You have removed the product from Your cart");
        //RedirectAttributes : You can use RedirectAttributes to store flash attributes and they will be automatically propagated to the "output" FlashMap of the current request.
        return "redirect:/cart";
    }

    //payment section
    @GetMapping(value = "/payment")
    public String payment(){
        return "payment";
    }



}
