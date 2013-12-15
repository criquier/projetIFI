package com.ifi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ifi.model.Article;
import com.ifi.model.Tag;
import com.ifi.model.User;
import com.ifi.repositories.ArticleRepository;
import com.ifi.repositories.TagRepository;
import com.ifi.repositories.UserRepository;
import com.ifi.utils.Selector;


@Controller
public class MainController {
    
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    SessionBean sessionBean;
    
    @RequestMapping(value="/", method=RequestMethod.GET)
	public String index( Model model)
	{  
	   // on recuppère la liste de tous les articles publié afin de les afficher
	     List<Article> articles=(List<Article>) articleRepository.findAll();
	     List<User> users=userRepository.findAll();
	     // on instancie le selector pour recuperer le filtre sur l'affichage
	     
		List<Tag> tags=tagRepository.findAll();
		System.out.println("TAG SIZE:"+tags.size());
	     Selector selector=new Selector();
	     // on ajoute la liste des aticles à la vue
	     model.addAttribute("articles",articles);
	     model.addAttribute("tags",tags);
	     model.addAttribute("selector",selector);
	     model.addAttribute("users",users);
	     
	     if(sessionBean.isConnected())
	    	 model.addAttribute("sessionBean",sessionBean);
	    
		return "index";
	}
}
