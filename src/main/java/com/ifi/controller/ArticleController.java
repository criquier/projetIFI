package com.ifi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ifi.model.Article;
import com.ifi.model.Commentaire;
import com.ifi.repositories.ArticleRepository;
import com.ifi.repositories.CommentaireRepository;

@Controller
public class ArticleController {
       @Autowired
       private CommentaireRepository comRepository;
       @Autowired
       SessionBean sessionBean;
        @Autowired
        private ArticleRepository repository;
        private Article article;
    
    
    //intercepte les ajouts d'article
    @RequestMapping(value="/ajouterArticle", method=RequestMethod.GET)
    public String ajouterArticleFormulaire(Model model){
	// On l'ajoute dans la BD locale
    model.addAttribute("sessionBean", sessionBean);
	model.addAttribute("article", new Article());
	return "article";
    }
    
    @RequestMapping(value="/ajouterArticle", method=RequestMethod.POST)
    public String ajouterArticleAfficher(@ModelAttribute Article article, Model model) {
    	 article.setAuteur(sessionBean.getUser());
         model.addAttribute("article", article);
         model.addAttribute("sessionBean", sessionBean);
         repository.save(article);
         this.article=article;
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
	this.article=repository.findById(id);
	model.addAttribute("article",this.article);
        model.addAttribute("commentaires",this.article.getCommentaires());
	return "articleTemplate";
    }
    
    // Ajouter un commentaire à cet article
    @RequestMapping(value="/ajouterCommentaire", method=RequestMethod.POST)
    public String ajouterCommentaire(@RequestParam("contenuCommentaire") String commentaire,
	    Model model) {
	
         // on ajoute le commentaire de l'article
	Commentaire c=new Commentaire();
	c.setContenu(commentaire);
	comRepository.save(c);
	
	//System.out.println("SIZE:"+this.article.getCommentaires().size());
        this.article.getCommentaires().add(c);
        model.addAttribute("article",this.article);
        model.addAttribute("commentaires",this.article.getCommentaires());
         
        repository.update(this.article);
        Article ar=repository.findById(this.article.getId());
      //  System.out.println("SIZE22:"+ar.getCommentaires().size());
        return "articleTemplate";
        //return "redirect:/consulterArticle?id="+article.getId();
    }
}
