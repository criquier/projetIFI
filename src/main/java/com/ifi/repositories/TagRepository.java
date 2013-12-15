package com.ifi.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ifi.model.Tag;

@Repository
@Transactional
public class TagRepository {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;
   
   // Trouver un article à travers son id
    public Tag findById(long id)
    {
            Query query = this.entityManager.createQuery("select t from Tag t where t.id=?");
            query.setParameter(1, id);
            return (Tag) query.getSingleResult();
            
    }
 // Trouver un article à travers son contenu
    public Tag findByContenu(String contenu)
    {
            Query query = this.entityManager.createQuery("select t from Tag t where t.contenu=?");
            query.setParameter(1, contenu);
            return (Tag) query.getSingleResult();
            
    }
    // Sauvegarder un article
    @Transactional
    public void save(Tag tag)
    {
      this.entityManager.persist(tag);
    }
    //udapte
    public void update(Tag tag){
        entityManager.merge(tag);
    }
    // Récupérer la liste de tous les articles
    @SuppressWarnings("unchecked")
    public List<Tag> findAll()
    {
            Query query= this.entityManager.createQuery("select t from Tag t");
            return query.getResultList();
    }
    // Supprimer un article
    public void delete(long id){
     this.entityManager.remove(this.findById(id));
    }
    
}
