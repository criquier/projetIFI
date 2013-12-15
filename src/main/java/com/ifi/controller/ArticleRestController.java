package com.ifi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.ifi.model.Article;
import com.ifi.repositories.ArticleRepository;

@Controller
@RequestMapping("/articles")
public class ArticleRestController {
    
    @Autowired
   ArticleRepository repository;
    @RequestMapping(method = RequestMethod.GET,
	    produces = {"application/json", "application/xml"})
    public @ResponseBody
    List<Article> getArticlesRecent() {
            List<Article> articles = repository.findAll(); 
            return articles;

      
    }
  
    
    
    @RequestMapping(value = "auteur/{name}",method = RequestMethod.GET,
	    produces = {"application/json", "application/xml"})
    public @ResponseBody
    List<Article> getArticlesAuteur(@PathVariable String name) {
            List<Article> articles = repository.findByAuteur(name);   
            return articles;

      
    }
    
    @RequestMapping(value = "tag/{tag}",method = RequestMethod.GET,
	    produces = {"application/json", "application/xml"})
    public @ResponseBody
    List<Article> getArticlesTag(@PathVariable String tag) {
            List<Article> articles = repository.findByTag(tag);   
            return articles;

      
    }
    //Construit un article à travers un JSON
    @RequestMapping(value = "fromRest/{jsonURL}",method = RequestMethod.GET
	   )
    public String builArticlesFromJSON(@PathVariable String jsonURL,Model model) {
	RestTemplate restTemplate = new RestTemplate();
        Article article = restTemplate.getForObject(jsonURL, Article.class);
        model.addAttribute("article", article);
            return "templates/articelTemplate";

      
    }
}
