package com.ifi.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ifi.model.User;


@Repository
@Transactional
public class UserRepository  {
    		@PersistenceContext(type = PersistenceContextType.EXTENDED)
		private EntityManager entityManager;
		
		public User findByLogin(String login)
		{
			User user = null;
			try {
				Query query = this.entityManager.createQuery("select u from User u where u.login=?");
				query.setParameter(1, login);
				user = (User) query.getSingleResult(); 	
			} catch (Exception e) {
				System.out.println("User findByLogin------------- User non trouve -------------");
			}
			return user;
		}
		
		public boolean existUser(String login){
			try {
				if(findByLogin(login) != null){
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
		
		@SuppressWarnings("unchecked")
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
	    	
//	    	try {
//	    		System.out.println("---------------------------- 1");
//	    	   User u= findOne(user.getId());
//	    	   System.out.println("---------------------------- 2");
//	 	       u.setLogin(user.getLogin());
//	 	      System.out.println("---------------------------- 3");
//	 	       u.setPassword(user.getPassword());
//	 	      System.out.println("---------------------------- 4");
	 	       entityManager.merge(user);
	 	       return true;
//	 	      System.out.println("---------------------------- 5");
	 	      // System.out.println("update Verification apres mise à jour-----------------  User : "+findOne(user.getId()).toString());
//	 	       return true;
//			} catch (Exception e) {
//				System.out.println("update-----------------  Erreur lors de la  modification");
//				return false;
//			}
	    }
	   
	    public void delete(long id){
	        this.entityManager.remove(this.findOne(id));
	    }
		
		
	}
