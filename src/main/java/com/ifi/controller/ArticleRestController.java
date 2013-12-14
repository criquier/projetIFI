package com.ifi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
