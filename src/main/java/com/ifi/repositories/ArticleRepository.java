package com.ifi.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ifi.model.Article;

@Repository
@Transactional
public class ArticleRepository {
    @PersistenceContext
	private EntityManager entityManager;
       // Trouver un article à travers son id
	public Article findById(long id)
	{
		Query query = this.entityManager.createQuery("select a from Article a where a.id=?");
		query.setParameter(1, id);
		return (Article) query.getSingleResult(); 
		
	}
	// Sauvegarder un article
	public void save(Article article)
	{
	    this.entityManager.persist(article);
	}
	// Récupérer la liste de tous les articles
	public List<Article> findAll()
	{
		Query query= this.entityManager.createQuery("select a from Article a");
		return query.getResultList();
	}
	// Supprimer un article
	public void delete(long id){
	    this.entityManager.remove(this.findById(id));
	}
	
	
    
}
