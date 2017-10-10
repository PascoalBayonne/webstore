package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.repository.filter.ProductFilter;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Pageable;


@Controller
public class HomeController {
	@Autowired
	private ProductsRepository productsRepository;

	@Autowired
	private UserService userService;

	@RequestMapping(value="/", method= RequestMethod.GET)
	public ModelAndView home(ProductFilter productFilter, Pageable pageable){
		ModelAndView modelAndView = new ModelAndView("index");
		try{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getUserName());
		}catch (Exception e){
			e.getCause();
		}

		modelAndView.addObject("allProducts",productsRepository.findAll());
		return modelAndView;
	}

	@GetMapping("/noob")
	public String accessDenied(){
		return "noob";
	}

	@GetMapping("/template-pattern")
	public String some(){
		return "template-pattern";
	}
}
