package com.ifi.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String contenu;
    @ManyToMany(mappedBy="tags")
    private List<Article> articles;
    public Tag(){}
   public Tag(long id,String contenu)
   {
       this.id=id;
       this.contenu=contenu;
   }
   
   public Tag(String contenu)
   {
       this.contenu=contenu;
   }
   
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getContenu() {
        return contenu;
    }
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
//    public List<Article> getArticles() {
//        return articles;
//    }
//    public void setArticles(List<Article> articles) {
//        this.articles = articles;
//    }
    
}
