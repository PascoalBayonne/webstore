package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller //@Controller is used to mark classes as Spring MVC Controller.
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@AuthenticationPrincipal User user) {
        return "login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup(User user) {
        ModelAndView modelAndView = new ModelAndView("/registration"); //name of my view registration.html with object gathered
        return modelAndView;
    }


    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView registrateUser(@Valid User user, BindingResult bindingResult, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail()); //fetch user by email if exists
        if (userExists != null) {
            bindingResult.rejectValue("email", "user.error", "there is a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            return signup(user);//in case of error in any field we redirected user to the registration page but
            //without clearing his correct input
        } else {
            userService.saveUser(user);//saving user with userService
            attributes.addFlashAttribute("messageSuccess", "Account created with success! please login now");
            //this interface RedirectAttributes helps us to propagate a message to our view.
            mv.addObject("user", new User());
        }
        return new ModelAndView("redirect:/signup");
    }


}
