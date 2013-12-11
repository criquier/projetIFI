package com.ifi.controller;

import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.ifi.model.User;

@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionBean {
	
		private final String id = UUID.randomUUID().toString();
		public User user;
		public String loggin; 
		
		
		
		public String getLoggin() {
			return loggin;
		}
		public void setLoggin(String loggin) {
			this.loggin = loggin;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		
		public boolean isConnected(){
			return user != null;
		}
}
