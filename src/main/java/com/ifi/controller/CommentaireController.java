package com.ifi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ifi.repositories.CommentaireRepository;

@Controller
public class CommentaireController {
    @Autowired
   	private CommentaireRepository repository;
       
   /* 
    @RequestMapping(value="/ajouterCommentaire", method=RequestMethod.POST)
    public String ajouterCommentaire(@ModelAttribute Commentaire commentaire, Model model) {
         model.addAttribute("commentaire", commentaire);
         repository.save(commentaire);
        return "articleTemplate";
    }*/
}
