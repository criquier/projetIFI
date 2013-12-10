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

import com.ifi.model.Article;
import com.ifi.model.User;
import com.ifi.repositories.UserRepository;

@Controller
public class UserController {
	
		
		@Autowired
		private UserRepository repository;
		
	
	    @RequestMapping(value="/inscription", method=RequestMethod.GET)
	    public String inscriptionUser(Model model){
			model.addAttribute("user", new User());
			return "inscription";
	    }
	    
	    @RequestMapping(value="/enregistrementUser", method=RequestMethod.POST)
	    public String validationInscriptionUser(@ModelAttribute User user, Model model) {
	    	String message_error = new String("Ce login existe deja");
	    	if(!user.getLoggin().equals("") && !user.getPassword().equals("")) 
		    	if(repository.existUser(user.getLoggin())==true){
		    		System.out.println("tring validationInscriptionUser-------- Login existe deja-------");
		    		User u = new User();
		    		model.addAttribute("message_error", message_error);
		    		model.addAttribute("user", u);
		    		return "inscription";
		    	}else{
					repository.save(user);
					System.out.println("-------- Login existe pas-------");
					model.addAttribute("user", user);
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
		
		@RequestMapping("/user")
		public @ResponseBody String index( @RequestParam(value="loggin", required=true) String loggin)
		{
			User user =repository.findByLoggin(loggin);
			StringBuilder builder=new StringBuilder();
			if(user==null) return "User does not exist";
			else
			return builder.append(user.toString()).toString();
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
