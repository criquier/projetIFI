package com.ifi.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Article {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private long id;
  private String titre;
  private String contenu;
  private String date;
  @ManyToOne
  private User auteur;
  @OneToMany
  private List<Commentaire> commentaires=new ArrayList<Commentaire>();
  
  @ManyToMany
  private List<Tag> tags=new ArrayList<Tag>();
  
   //Constructeurs
   public Article(){
      SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
      Date today=new Date();
      date=formater.format(today);
     
   }
   public Article(long id,String titre, String contenu){
       SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
       this.id=id;
       this.titre=titre;
       this.contenu=contenu;
      Date today=new Date();
      this.date=formater.format(today);

     
   }
  
  // getters et setters
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getContenu() {
        return contenu;
    }
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public User getAuteur() {
        return auteur;
    }
    public void setAuteur(User auteur) {
        this.auteur = auteur;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }
    public List<Tag> getTags() {
        return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    
    
    
  
} 