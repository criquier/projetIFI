package com.ifi.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ifi.model.User;


@Repository
@Transactional
	public class UserRepository  {
		@PersistenceContext
		private EntityManager entityManager;
		
		public User findByLoggin(String loggin)
		{
			User user = null;
			try {
				Query query = this.entityManager.createQuery("select u from User u where u.loggin=?");
				query.setParameter(1, loggin);
				user = (User) query.getSingleResult(); 	
			} catch (Exception e) {
				System.out.println("User findByLoggin------------- User non trouve -------------");
			}
			return user;
		}
		
		public boolean existUser(String loggin){
			try {
				if(findByLoggin(loggin) != null){
					System.out.println("public boolean existUser------------- User existe  -------------");
					return true;
				}
			} catch (Exception e) {
				System.out.println("public boolean existUser------------- User existe pas-------------");
				
			}
			return false;
		}
		
		public void save(User user)
		{
			this.entityManager.persist(user);
		}
		
		public List<User> findAll()
		{
			Query query= this.entityManager.createQuery("select u from User u");
			return query.getResultList();
		}
		
		public User findOne(long l)
		{
			User user =null;
			try {
				Query query = this.entityManager.createQuery("select u from User u where u.id=:id");
				query.setParameter("id",l);
				user =  (User) query.getSingleResult();
			} catch (Exception e) {
				System.out.println("User findOne------------- User non trouve -------------");
			}
			 return user;
			
		}
		
		
	    public boolean update(User user){
	    	
	    	try {
	    	   User u= findOne(user.getId());
	 	       u.setLoggin(user.getLoggin());
	 	       u.setPassword(user.getPassword());
	 	       this.entityManager.getTransaction().commit();
	 	       return true;
			} catch (Exception e) {
				return false;
			}
	    }
	   
	    public void delete(long id){
	        this.entityManager.remove(this.findOne(id));
	    }
		
		
	}
