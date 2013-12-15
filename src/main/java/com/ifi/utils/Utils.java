package com.ifi.utils;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.ifi.model.Tag;
import com.ifi.model.User;
import com.ifi.repositories.TagRepository;
import com.ifi.repositories.UserRepository;

/** Cette classe sert au developpement, vous pouvez mettre ici tout ce qui vous sert pour par exemple remplir la base de donnees, ou proceder a des tests **/
public class Utils {

	
	public static void fillDataBase(ApplicationContext context){
		
		// creation des utilisateur 

        	  UserRepository repository = context.getBean(UserRepository.class);
          	  repository.save(new User("thierno", "Barry"));
          	  repository.save(new User("maxime", "Gens"));
            repository.save(new User("benjamin", "Flahauw"));
            repository.save(new User("camille", "Riquier"));
            List<User> users = (List<User>) repository.findAll();
            System.out.println("Customers found with findAll():");
            System.out.println("-------------------------------");
            for (User user : users) {
                System.out.println(user.getId()+" "+user.getLogin()+" "+user.getPassword());
            }
            System.out.println();
    		TagRepository tagRepository = context.getBean(TagRepository.class);
    		tagRepository.save(new Tag("Noel"));	
    		tagRepository.save(new Tag("Neige"));
    		tagRepository.save(new Tag("Spring"));	
    		tagRepository.save(new Tag("Fete"));	
    		tagRepository.save(new Tag("Cadeau"));	

	}
	

	
	
}
