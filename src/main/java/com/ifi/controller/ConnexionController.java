package com.ifi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ifi.model.User;
import com.ifi.repositories.UserRepository;

@Controller
public class ConnexionController {
    @Autowired
	private UserRepository repository;
    
    @RequestMapping(value="/connexion", method=RequestMethod.GET)
    public String connexion(Model model){
    	model.addAttribute("user", new User());
		return "connexion";
    }
    
    @RequestMapping(value="/connexion", method=RequestMethod.POST)
    public String connexionValider(User user,Model model){
    	String msg_error = new String("utilisateur non identifié");
    	try {
    		if(repository.existUser(user.getLoggin()) == true){
    			User userFind = repository.findByLoggin(user.getLoggin());
    			model.addAttribute("userconnecter", userFind);
    		}
		} catch (Exception e) {
			model.addAttribute("message_error", msg_error);
			return "connexion";
		}
		return "index";
    }   
}
