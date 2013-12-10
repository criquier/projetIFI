package com.ifi.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ifi.model.Commentaire;
@Repository
@Transactional
public class CommentaireRepository {
    
    @PersistenceContext
	private EntityManager entityManager;
   // Trouver un Commentaire à travers son id
	public Commentaire findById(long id)
	{
		Query query = this.entityManager.createQuery("select c from Commentaire c where c.id=?");
		query.setParameter(1, id);
		return (Commentaire) query.getSingleResult(); 
		
	}
	// Sauvegarder un Commentaire
	public void save(Commentaire Commentaire)
	{
	    this.entityManager.persist(Commentaire);
	}
	// Récupérer la liste de tous les Commentaires
	public List<Commentaire> findAll()
	{
		Query query= this.entityManager.createQuery("select c from Commentaire c");
		return query.getResultList();
	}
	// Supprimer un Commentaire
	public void delete(long id){
	    this.entityManager.remove(this.findById(id));
	}

}
