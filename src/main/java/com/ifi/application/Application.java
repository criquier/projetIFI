package com.ifi.application;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

import java.util.UUID;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ifi.model.User;
import com.ifi.repositories.ArticleRepository;
import com.ifi.repositories.CommentaireRepository;
import com.ifi.repositories.UserRepository;
import com.ifi.utils.Utils;

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
@EnableAutoConfiguration(exclude={HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages="com.ifi.controller")
public class Application {
    
//	@Component
//	@Scope(value="session")
//	public class SessionBean {
//		
//			private final String id = UUID.randomUUID().toString();
//			public User user;
//			public String loggin; 
//			
//			
//			
//			public String getLoggin() {
//				return loggin;
//			}
//			public void setLoggin(String loggin) {
//				this.loggin = loggin;
//			}
//			public User getUser() {
//				return user;
//			}
//			public void setUser(User user) {
//				this.user = user;
//			}
//			
//			public boolean isConnected(){
//				return user != null;
//			}
//	}
	
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(H2).build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource);
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setPackagesToScan("com.ifi.*");
        return lef;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(Database.H2);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
    @Bean
    public UserRepository userrepository(){
    	return new UserRepository();
    }
    
    @Bean
    public ArticleRepository articleRepository(){
    	return new ArticleRepository();
    }
    
<<<<<<< HEAD
    
=======
    @Bean
    public CommentaireRepository commentaireRepository(){
    	return new CommentaireRepository();
    }
>>>>>>> b7ee66874d62da309007ee28da2966b7e8f1b388

    public static void main(String[] args) {
       // AbstractApplicationContext context = new AnnotationConfigApplicationContext(Application.class,args);
    	// SpringApplication.run(Application.class, args);
    	 System.out.println("--------------Thierno saidou-------------------");
    	 ApplicationContext context = SpringApplication.run(Application.class, args);
    	 Utils.fillDataBase(context);
       

        //context.close();
    }

}
