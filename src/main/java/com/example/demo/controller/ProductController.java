package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.filter.ProductFilter;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Controller
public class ProductController {
    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

//    @Autowired
//    CartService cartService;

    //ONLY FOR ADMIN -->Begin
    @GetMapping(value = "/dashboard/edit")
    public ModelAndView products(Product product) {
        ModelAndView mv = new ModelAndView("/dashboard");
        mv.addObject(product);
        mv.addObject("allProducts", productsRepository.findAll());
        mv.addObject("allProductsInCart", cartRepository.findAll());
        return mv;
    }

    @PostMapping("/dashboard/edit")
    public ModelAndView save(@Valid Product product, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return products(product);
        }
        attributes.addFlashAttribute("message", "Product has been saved");
        productsRepository.save(product);
        return new ModelAndView("redirect:/dashboard/edit");

    }

    @RequestMapping(value = "/dashboard/update/{id}")
    public ModelAndView update(@PathVariable Long id) {
        Product product = productsRepository.findOne(id);
        return products(product);

    }




    @RequestMapping(value = "/dashboard/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        productsRepository.delete(id);
        attributes.addFlashAttribute("message", "product removed!");
        return "redirect:/dashboard/edit";
    }
    //ONLY FOR ADMIN -->End


    //ALL USERS
    @GetMapping("/search")
    public ModelAndView search(ProductFilter productFilter) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allProducts", productsRepository.findByNameContainingIgnoreCase(
                Optional.ofNullable(productFilter.getName()).orElse("%")));//orElse If a value is present, returns the value, otherwise returns other
                /*Optional :A container object which may or may not contain a non-{@code null} value.
                * If a value is present, {@code isPresent()} returns {@code true} and
                * {@code get()} returns the value.*/
       // modelAndView.addObject("getProductsByPrice",productsRepository.findProductsByPrice(productFilter.getPrice()));

        return modelAndView;
    }

    @GetMapping("/searchp")
    public ModelAndView searchp(ProductFilter productFilter) {
        ModelAndView modelAndView = new ModelAndView("search");
        modelAndView.addObject("getProductsByPrice",productsRepository.findProductsByPrice(productFilter.getPrice()));
//orElse If a value is present, returns the value, otherwise returns other
                /*Optional :A container object which may or may not contain a non-{@code null} value.
                * If a value is present, {@code isPresent()} returns {@code true} and
                * {@code get()} returns the value.*/

        return modelAndView;
    }

//    @GetMapping("/search/price")
//    public ModelAndView searchPrice(ProductFilter productFilter) {
//        ModelAndView modelAndView = new ModelAndView("searchp");
//
//        modelAndView.addObject("getProductsByPrice",productsRepository.findProductsByPrice(productFilter.getPrice()));
//
//
//        return new ModelAndView("searchp");
//    }
     /*   An @ModelAttribute on a method argument indicates the argument should be retrieved from the model. If not present in the model,
       the argument should be instantiated first and then added to the model.
       Once present in the model, the argument's fields should be populated from all request parameters that have matching names.
      */

//    @RequestMapping(value = "/search/price", method = RequestMethod.GET)
//    public ModelAndView findProductByPrice(ProductFilter productFilter, @PathVariable Double price) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("getProductsByPrice",productsRepository.findProductsByPrice(productFilter.getPrice()));
//        return new ModelAndView("redirect:/searchp");
//    }


    @RequestMapping(value = "'/product/one/{id}", method = RequestMethod.GET)
    public String findOne(@PathVariable Long id) {
        productsRepository.findOne(id);
        return "redirect:/index";
    }


}