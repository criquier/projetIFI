package com.ifi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ifi.model.Article;
import com.ifi.model.User;
import com.ifi.repositories.ArticleRepository;
import com.ifi.repositories.UserRepository;

@Controller
public class MainController {
    
    @Autowired
    ArticleRepository articleRepository;
    UserRepository userRepository;
    @RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Model model)
	{  
	   // on recuppère la liste de tous les articles publié afin de les afficher
	     List<Article> articles=articleRepository.findAll();
	     //List<User> users=userRepository.findAll();
	     // on ajoute la liste des aticles à la vue
	     model.addAttribute("articles",articles);
	     //model.addAttribute("users",users);
		return "index";
	}
}
