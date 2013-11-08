package com.ifi.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ifi.model.User;
import com.ifi.repositories.userRepository;

@Controller
public class UserController {
	
		
		@Autowired
		private userRepository repository;
		
		
		
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
			StringBuilder builder=new StringBuilder();
			List<User> users=repository.findAll();
			builder.append("-----------------Liste Users----------------------------------");
			builder.append("\n");
			for (User user : users) {
				builder.append(user.toString());
				builder.append("\n");
				
	          }
	  
	          return builder.toString()+"\n";
		}
	


}
