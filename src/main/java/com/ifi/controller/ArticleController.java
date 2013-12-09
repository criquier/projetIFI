package com.ifi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ifi.model.Article;
import com.ifi.repositories.ArticleRepository;

@Controller
public class ArticleController {
    @Autowired
	private ArticleRepository repository;
    
    //intercepte les ajouts d'article
    @RequestMapping(value="/ajouterArticle", method=RequestMethod.GET)
    public String ajouterArticleFormulaire(Model model){
	// On l'ajoute dans la BD locale
	model.addAttribute("article", new Article());
	return "article";
    }
    
    @RequestMapping(value="/ajouterArticle", method=RequestMethod.POST)
    public String ajouterArticleAfficher(@ModelAttribute Article article, Model model) {
         model.addAttribute("article", article);
         repository.save(article);
        return "articleTemplate";
    }
  
    //intercepte la modification sur Article
    @RequestMapping("/modifierArticle")
    public void modifierArticle(@RequestParam(value="id", required=true) long id){
	
    }
    
    //intercepte la suppression de l'article
    @RequestMapping("/supprimerArticle")
    public void supprimerArticle(@RequestParam(value="id", required=true) long id){
	repository.delete(id);
	
    }
    //intercepte la consultation de l'article
    @RequestMapping("/consulterArticle")
    public String consulterArticle(@RequestParam(value="id", required=true) long id,
	    Model model){
	model.addAttribute(repository.findById(id));
	return "articleTemplate";
    }
    
}
