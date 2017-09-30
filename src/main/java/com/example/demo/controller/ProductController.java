package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.filter.ProductFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    CartService cartService;

    //ONLY FOR ADMIN -->Begin
    @GetMapping(value = "/dashboard/edit")
    public ModelAndView products(Product product){
        ModelAndView mv = new ModelAndView("/dashboard");
        mv.addObject(product);
        mv.addObject("allProducts",productsRepository.findAll());
        mv.addObject("allProductsInCart", cartRepository.findAll());
        return mv;
    }

    @PostMapping("/dashboard/edit")
    public ModelAndView save (@Valid Product product, BindingResult bindingResult , RedirectAttributes attributes){
       if (bindingResult.hasErrors()){
           return products(product);
       }
        attributes.addFlashAttribute("message", "Product has been saved");
        productsRepository.save(product);
       return new ModelAndView("redirect:/dashboard/edit");

    }

    @RequestMapping(value = "/dashboard/update/{id}")
    public ModelAndView update(@PathVariable Long id){
        Product product = productsRepository.findOne(id);
        return products(product);

    }



//    @PostMapping(value = "/dashboard/addProductIntoCart")
//    public String addProductIntocart(@AuthenticationPrincipal User user, Cart cart, String productId){
//        Product product = productsRepository.findOne(Long.parseLong(productId));
//        cartService.addProductIntoCart(productId);
//        return null;
//    }

    @RequestMapping( value = "/dashboard/delete/{id}")
    public String delete (@PathVariable Long id, RedirectAttributes attributes){
        productsRepository.delete(id);
        attributes.addFlashAttribute("message","product removed!");
        return "redirect:/dashboard/edit";
    }
    //ONLY FOR ADMIN -->End


    //ALL USERS
    @GetMapping("/search")
    public ModelAndView search(ProductFilter productFilter, Pageable pageable){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allProducts",productsRepository.findByNameContainingIgnoreCase(
                Optional.ofNullable(productFilter.getName()).orElse("%")));//if there is null it will search all products
        return modelAndView;
    }


    @RequestMapping(value = "'/product/one/{id}",method = RequestMethod.GET)
    public String findOne(@PathVariable Long id){
        productsRepository.findOne(id);
        return "redirect:/index";
    }





}