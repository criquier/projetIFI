package com.ifi.controller;


import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.ifi.model.Article;
import com.ifi.model.Commentaire;
import com.ifi.model.Tag;
import com.ifi.repositories.ArticleRepository;
import com.ifi.repositories.CommentaireRepository;
import com.ifi.repositories.TagRepository;

@Controller
@RequestMapping("/articles")
public class ArticleController {

   @Autowired
    private TagRepository tagRepository;
   @Autowired
    private CommentaireRepository comRepository;
   @Autowired
    private ArticleRepository repository;
    private Article article;
    @Autowired
    SessionBean sessionBean;
    @Autowired
    JmsTemplate jmsTemplate;
    
    
    
    //intercepte les ajouts d'article
    @RequestMapping(value="/ajouterArticle", method=RequestMethod.GET)
    public String ajouterArticleFormulaire(Model model){
    	if(sessionBean.getUser() == null)
			return "redirect:/";
		
	// On l'ajoute dans la BD locale
        model.addAttribute("sessionBean", sessionBean);
	model.addAttribute("article", new Article());
	model.addAttribute("TAGS", this.tagRepository.findAll());
	return "articles/article";
    }
    
    @RequestMapping(value="/ajouterArticle", method=RequestMethod.POST)

    public String ajouterArticleAfficher(@ModelAttribute Article article,
	    @RequestParam("checked") List<String> checked,Model model) {
    	if(sessionBean.getUser() == null)
	  return "redirect:/";
		
    	 article.setAuteur(sessionBean.getUser());
         model.addAttribute("article", article);
         model.addAttribute("sessionBean", sessionBean);
	
	model.addAttribute("article", article);
         //System.out.println("ID: " +checked.size());
         Tag tag=new Tag();
         for(int i=0;i<checked.size();i++){
             if(tagRepository.findByContenu(checked.get(i))==null){
        	 tag=new Tag(checked.get(i));
        	 tagRepository.save(tag);
             }else{
        	 tag=tagRepository.findByContenu(checked.get(i)); 
             }
             article.getTags().add(tag);
             
         }
         
         repository.save(article);
         /*** envoi aux autre ordi **/
         Gson gson = new Gson();
         final String json = gson.toJson(article);
         jmsTemplate.send(new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				return session.createTextMessage(json);
			}
		});
        // System.out.println("TAGS: "+article.getTags().size());
        
        
         this.article=article;
        return "articles/articleTemplate";
    }
  
    //intercepte la modification sur Article
    @RequestMapping("/modifierArticle")
    public void modifierArticle(@RequestParam(value="id", required=true) long id){
	
    }
    
    //intercepte la suppression de l'article
    @RequestMapping("/supprimerArticle")
    public void supprimerArticle(@RequestParam(value="id", required=true) long id){
    	if(sessionBean.getUser() != null)
    		repository.delete(id);
	
    }
    //intercepte la consultation de l'article
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String consulterArticle(@PathVariable String id,
	    Model model){
	model.addAttribute("sessionBean", sessionBean);
	this.article=repository.findById(Long.parseLong(id));
	 System.out.println("ARTICLE:"+this.article.getTitre());
    	if(sessionBean.getUser() == null)
			return "redirect:/";

	model.addAttribute("article",this.article);
	//model.addAttribute("tags",this.article.getTags());
       // model.addAttribute("commentaires",this.article.getCommentaires());
     System.out.println("COMMENTAIRES:"+this.article.getTags().size());
	return "articles/articleTemplate";
    }
    
    // Ajouter un commentaire à cet article
    @RequestMapping(value="/ajouterCommentaire", method=RequestMethod.POST)
    public String ajouterCommentaire(@RequestParam("contenuCommentaire") String commentaire,
	    Model model) {
    	if(sessionBean.getUser() == null)
			return "redirect:/";
		
         // on ajoute le commentaire de l'article
	Commentaire c=new Commentaire();
	c.setAuteur(sessionBean.getUser());
	c.setContenu(commentaire);
	comRepository.save(c);

        this.article.getCommentaires().add(c);
        model.addAttribute("article",this.article);
        model.addAttribute("sessionBean", sessionBean);

        repository.update(this.article);

        return "articles/articleTemplate";
        //return "redirect:/consulterArticle?id="+article.getId();
    }
}
