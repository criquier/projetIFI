package com.ifi.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Commentaire {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    	private long id;
    	private String contenu;
    	private String date;
    	@OneToOne
    	private User auteur;
    	
    	public Commentaire(){
            SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
            Date today=new Date();
            date=formater.format(today);
    	}
    	public Commentaire(long id, String contenu, String date, User auteur)
    	{
    	    this.id=id;
    	    this.contenu=contenu;
    	    this.date=date;
    	    this.auteur=auteur;
    	}
	public long getIdCommentaire() {
	    return id;
	}
	public void setIdCommentaire(long id) {
	    this.id = id;
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
}
