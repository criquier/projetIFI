package com.ifi.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ifi.model.User;
import com.ifi.repositories.UserRepository;

@Controller
public class UserController {
	
		
		@Autowired
		private UserRepository repository;
		@Autowired
		SessionBean sessionBean;
	
	    @RequestMapping(value="/inscription", method=RequestMethod.GET)
	    public String inscriptionUser(Model model){
	    	if(sessionBean.getUser() != null)
	    		return "redirect:/";
			model.addAttribute("user", new User());
			return "inscription";
	    }
	    
	    @RequestMapping(value="/enregistrementUser", method=RequestMethod.POST)
	    public String validationInscriptionUser(@ModelAttribute User user, Model model) {
	    	String message_error = new String("Ce login existe deja");
	    	if(!user.getLoggin().equals("") && !user.getPassword().equals("")) 
		    	if(repository.existUser(user.getLoggin())==true){
		    		System.out.println("String validationInscriptionUser-------- Login existe deja-------");
		    		User u = new User();
		    		model.addAttribute("message_error", message_error);
		    		model.addAttribute("user", u);
		    		return "inscription";
		    	}else{
					repository.save(user);
					System.out.println("-------- Login existe pas deja en base,  utilisateur bien enregistrer-------");
					model.addAttribute("user", user);
					sessionBean.setUser(user);
					return "inscriptionValider";
		    	}
	    	else{
	    		message_error = new String("Attention il y a un champs vide");
	    		model.addAttribute("message_error", message_error);
				return "inscription";
	    	}
	    }
		
		@RequestMapping("/save")
		public @ResponseBody String save( @RequestParam(value="loggin", required=true) String loggin,
				 @RequestParam(value="password", required=false, defaultValue="ce1mdpp") String password)
		{
			repository.save(new User(loggin,password));
			return "saved";
		}
		
		@RequestMapping(value="/user/profil",method=RequestMethod.GET)
		public String afficherUser( @RequestParam(value="id", required=true) String id, Model model)
		{
			if(sessionBean.getUser() == null)
				return "redirect:/";
			
			User user = repository.findOne(Long.parseLong(id));
			if(user != null){
				model.addAttribute("sessionBean",sessionBean);
				model.addAttribute("user", user);
				return "userTemplate";
			}else{
				String message_error = new String("Erreur lors de la récupération de l'User, l'user n'existe pas");
				model.addAttribute("message_error", message_error);
				return "index";
			}
		}
		
		@RequestMapping(value="/user/profil/modifier", method=RequestMethod.POST)
		public String modifierUser( @ModelAttribute User user, Model model)
		{
			if(sessionBean.getUser() == null)
				return "redirect:/";
			
			long id = user.getId();
			System.out.println("----------------------------User recupére:"+user.toString());
				if(repository.update(user) == true){
					User userModifier = repository.findOne(id);
					System.out.println("modifierUser : ------------------------------- User modifier : "+userModifier.toString());
					sessionBean.setUser(userModifier);
					model.addAttribute("user", userModifier);
					model.addAttribute("sessionBean", sessionBean);
					return "userTemplate";
				}else{
					System.out.println("modifierUser : ------------------------------- update = false");
					String message_error = new String("Impossible de mettre à jour vos informations");
					model.addAttribute("message_error", message_error);
					model.addAttribute("user", user);
					model.addAttribute("sessionBean", sessionBean);
					return "userTemplate";
				}
			
		}
		
		
		
		@RequestMapping("/tous")
		public @ResponseBody String readAll(){
			String builder = new String();
			List<User> users=repository.findAll();
			builder = "-----------------Liste Users----------------------------------"+System.getProperty("line.separator");
			
			for (User user : users) {
				builder += user.toString().concat("\n");
	          }
	          return builder;
		}
	


}
