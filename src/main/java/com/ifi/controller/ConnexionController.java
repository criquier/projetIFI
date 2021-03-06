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
    
    @Autowired
    SessionBean sessionBean;
    
    @RequestMapping(value="/connexion", method=RequestMethod.GET)
    public String connexion(Model model){
    	if(sessionBean.getUser() != null)
    		return "redirect:/";
    	
    	model.addAttribute("user", new User());
		return "connexion";
    }
    
    @RequestMapping(value="/deconnexion", method=RequestMethod.GET)
    public String deconnexion(Model model){
    	sessionBean.setUser(null);
    	return "redirect:/";
    }
    
    @RequestMapping(value="/connexion", method=RequestMethod.POST)
    public String connexionValider(User user,Model model){
    	String message_error = new String("Identifiant incorrect");
    	
		if(repository.existUser(user.getLogin()) == true){
			User userFind = repository.findByLogin(user.getLogin());
			if(userFind.getPassword().equals(user.getPassword())){
				sessionBean.setUser(userFind);
				return "redirect:/";
			}
		}
		model.addAttribute("message_error", message_error);
		return "connexion";
    }   
}
