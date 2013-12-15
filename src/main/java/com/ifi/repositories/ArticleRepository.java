package com.ifi.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ifi.model.Article;

@Repository
@Transactional
public class ArticleRepository {
   
       @PersistenceContext(type = PersistenceContextType.EXTENDED)
        private EntityManager entityManager;
        @Autowired
        TagRepository tagRepository;
       // Trouver un article à travers son id
        public Article findById(long id)
        {
                Query query = this.entityManager.createQuery("select a from Article a where a.id=?");
                query.setParameter(1, id);
                return (Article) query.getSingleResult();
                
        }
        // Sauvegarder un article
        @Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
        public void save(Article article)
        { 
            this.entityManager.persist(article);
        }
        //udapte
      //
        @Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
        @Modifying(clearAutomatically = true)
        public void update(Article article)
        {
             Query query = this.entityManager.createQuery(""
             	+ "update Article a set a.tags =?,"
             	+ "a.contenu=?, a.titre=?"   
             	+ " where a.id=?");
                query.setParameter(1, article.getTags());
                query.setParameter(2, article.getTitre());
                query.setParameter(3, article.getContenu());
                query.setParameter(4, article.getId());
                
        }
        public void merge(Article article){
            //entityManager.getTransaction().commit();
            entityManager.merge(article);
            entityManager.flush();
        }
        // Récupérer la liste de tous les articles
        @SuppressWarnings("unchecked")
	public List<Article> findAll()
        {
                Query query= this.entityManager.createQuery("select a from Article a order by a.date desc");
                return query.getResultList();
        }
        // Recupère les articles par Tag
        @SuppressWarnings("unchecked")
	public List<Article > findByTag(String tag)
        {
                Query query = this.entityManager.createQuery("SELECT a FROM Article a LEFT JOIN a.tags t"+ 
                "WHERE (t.contenu=?)");
                query.setParameter(1, tag);
                return query.getResultList();
                
        }
        //Recuperer les articles par Auteur
        @SuppressWarnings("unchecked")
	public List<Article > findByAuteur(String name)
        {
                Query query = this.entityManager.createQuery("SELECT a FROM Article a LEFT JOIN a.auteur u"+ 
                "WHERE (u.login=?)");
                query.setParameter(1, name);
                return query.getResultList();
                
        }
        
     // Récupérer la liste des 10 articles les plus recents
        @SuppressWarnings("unchecked")
	public List<Article> findMostRecent()
        {
                Query query= this.entityManager.createQuery("select a from Article a order by a.date desc").setMaxResults(10);
                return query.getResultList();
        }
        // Supprimer un article
        public void delete(long id){
         this.entityManager.remove(this.findById(id));
        }
        
        
    
}