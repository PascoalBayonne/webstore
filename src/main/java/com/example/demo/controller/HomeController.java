package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value="/index", method= RequestMethod.GET)
	public String home(@AuthenticationPrincipal User user){
		return "index";
	}

	@GetMapping("/noob")
	public String accessDenied(){
		return "noob";
	}
}
