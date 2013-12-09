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
//    	StringBuilder resultat = new StringBuilder();
//    	User userBdd = new User();
//    	userBdd= repository.findByLoggin(user.getLoggin());
//    	if(userBdd != null){
//    		resultat.append("L'identifiant est incorrect ");
//    		System.out.println("id non");
//    		if(!userBdd.getPassword().equals(user.getPassword())){
//    			resultat.append("Le mot de passe est incorrect");
//    			System.out.println("mdp non");
//    			model.addAttribute("resultat", resultat);
//    		}else{
//    			model.addAttribute("user", user);
//    			System.out.println("ok");
//    		}
//    	} 	
		return "index";
    }   
}
