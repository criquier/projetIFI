package com.ifi.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User {
	 @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    private long id;
	    private String loggin;
	    private String password;
		public long getId() {
			return id;
		}
		public User(){}
		public User(String loggin,String password){
			this.loggin=loggin;
			this.password=password;
		}
		public User(String loggin){
			this.loggin=loggin;
			//this.password=password;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getLoggin() {
			return loggin;
		}
		public void setLoggin(String loggin) {
			this.loggin = loggin;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		@Override
	    public String toString() {
	        return String.format(
	                "User[id=%d, loggin='%s', password='%s']",
	                id, loggin, password);
	    }
	    
	    
}