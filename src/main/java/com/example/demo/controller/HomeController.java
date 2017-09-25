package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.repository.filter.ProductFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@Autowired
	private ProductsRepository productsRepository;


	@RequestMapping(value="/index", method= RequestMethod.GET)
	public ModelAndView home(@AuthenticationPrincipal User user, ProductFilter productFilter){
		ModelAndView modelAndView = new ModelAndView("/index");
		modelAndView.addObject("allProducts",productsRepository.findAll());
		return modelAndView;
	}

	@GetMapping("/noob")
	public String accessDenied(){
		return "noob";
	}
}
