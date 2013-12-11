package com.ifi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    @Autowired
    UserRepository userRepository;
    @Autowired
    SessionBean sessionBean;
    
    @RequestMapping(value="/", method=RequestMethod.GET)
	public String index( Model model)
	{  
    	User user = new User("null", "null");
	   // on recuppère la liste de tous les articles publié afin de les afficher
	     List<Article> articles=(List<Article>) articleRepository.findAll();
	     List<User> users=userRepository.findAll();
	     // on ajoute la liste des aticles à la vue
	     model.addAttribute("articles",articles);
	     model.addAttribute("users",users);
//	     System.out.println("-------------------------main "+sessionBean.getUser().getLoggin());
	     if(sessionBean.isConnected() == false)
	    	System.out.println("User non connecte");
	     else{
	    	 System.out.println("-------------------------main "+sessionBean.getUser().getLoggin());
	     
	     model.addAttribute("sessionBean",sessionBean);
	     
	     }
	    
		return "index";
	}
}
