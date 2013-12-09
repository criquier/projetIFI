package com.ifi.model;

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
    	private long idCommentaire;
    	private String contenu;
    	private Date dateCommentaire;
    	@OneToOne
    	private User auteur;
    	
    	public Commentaire(){}
    	public Commentaire(long idCommentaire, String contenu, Date dateCommentaire, User auteur)
    	{
    	    this.idCommentaire=idCommentaire;
    	    this.contenu=contenu;
    	    this.dateCommentaire=dateCommentaire;
    	    this.auteur=auteur;
    	}
	public long getIdCommentaire() {
	    return idCommentaire;
	}
	public void setIdCommentaire(long idCommentaire) {
	    this.idCommentaire = idCommentaire;
	}
	public String getContenu() {
	    return contenu;
	}
	public void setContenu(String contenu) {
	    this.contenu = contenu;
	}
	public Date getDateCommentaire() {
	    return dateCommentaire;
	}
	public void setDateCommentaire(Date dateCommentaire) {
	    this.dateCommentaire = dateCommentaire;
	}
	public User getAuteur() {
	    return auteur;
	}
	public void setAuteur(User auteur) {
	    this.auteur = auteur;
	}
}
