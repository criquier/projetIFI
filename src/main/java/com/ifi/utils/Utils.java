package com.ifi.utils;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.ifi.model.Article;
import com.ifi.model.Tag;
import com.ifi.model.User;
import com.ifi.repositories.ArticleRepository;
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
    		// Des articles par defaut
    		ArticleRepository articleRepository = context.getBean(ArticleRepository.class);
    		Article a1=new Article("Spring",
    			
    		"Spring est un nouveau framework qui facilite le developpement"
    		+ "Java/J2ee. Nous l avons apris dans le cadre de nos cours de "
    		+ "Master informatique. Nous comptons mettre en application ce"
    		+ "framework une fois en entreprise.",repository.findOne(1));
    		a1.getTags().add(tagRepository.findByContenu("Spring"));
    		articleRepository.save(a1);
    		
    		Article a2=new Article("Noel approche",
    			
    	        "Cadeau pour une femme, ou cadeau pour un homme, il peut "
    	        + "etre offert en toute occasion aux amis, familles et "
    	        + "collegues de travail: cadeaux anniversaire, "
    	        + "cadeaux de noel, cadeau pour la fete des peres, "
    	        + "cadeau pour la fête des meres, cadeau de mariage, "
    	        + "cadeau de depart en retraite, cadeau saint "
    	        + "valentin...",repository.findOne(1));
    	        a2.getTags().add(tagRepository.findByContenu("Cadeau"));
    	        a2.getTags().add(tagRepository.findByContenu("Noel"));
    	    	articleRepository.save(a2);
    	    	
    	    Article a3=new Article("Fete de fin d'annee",
			
              "Les fetes de fin d’annee sont des fetes annuelles celebrees"
              + " a lapproche et aux alentours de la fin de l’année,"
              + " durant le mois de decembre et en particulier a "
              + "l'occasion de la deuxieme moitie du mois de decembre "
              + "et des tout premiers jours "
              + "de janvier.",repository.findOne(2));
               a3.getTags().add(tagRepository.findByContenu("Fete"));
               a3.getTags().add(tagRepository.findByContenu("Noel"));
               articleRepository.save(a3);
    		

	}
	
      
	
	
}
