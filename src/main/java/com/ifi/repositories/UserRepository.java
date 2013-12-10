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
			Query query = this.entityManager.createQuery("select u from User u where u.loggin=?");
			query.setParameter(1, loggin);
			User user = (User) query.getSingleResult(); 	
			return user;
			

		}
		
		public boolean existUser(String loggin){
			
			if(findByLoggin(loggin) == null){
				System.out.println("User n'existe pas ");
				return false;
				
			}
			System.out.println("User existe");
			return true;
			
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
			Query query = this.entityManager.createQuery("select u from User u where u.id=:id");
			query.setParameter("id",l);
			return (User) query.getSingleResult();
			
		}
		
	    
	}
	
	

