package com.ifi.application;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.Topic;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
    
    @Bean
    public CommentaireRepository commentaireRepository(){
    	return new CommentaireRepository();
    }

    
    static String mailboxDestination = "b11p6";

	@Bean
	ConnectionFactory connectionFactory() {
		return new CachingConnectionFactory(new ActiveMQConnectionFactory(
				"tcp://localhost:61616"));
	}

	@Bean
	MessageListenerAdapter receiver() {
		return new MessageListenerAdapter(new Receiver()) {
			{
				setDefaultListenerMethod("receiveMessage");
			}
		};
	}
	
	@Bean
	SimpleMessageListenerContainer container(
			final MessageListenerAdapter messageListener,
			final ConnectionFactory connectionFactory) {
		final Destination destination = new Topic() {
			
			@Override
			public String getTopicName() throws JMSException {
				return mailboxDestination;
			}
		};
		return new SimpleMessageListenerContainer() {
			{
				setMessageListener(messageListener);
				setConnectionFactory(connectionFactory);
				setDestination(destination);
			}
		};
	}

	@Bean
	JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
		return new JmsTemplate(connectionFactory);
	}
    public static void main(String[] args) {
       // AbstractApplicationContext context = new AnnotationConfigApplicationContext(Application.class,args);
    	// SpringApplication.run(Application.class, args);
    	
    	 ApplicationContext context = SpringApplication.run(Application.class, args);
    	 Utils.fillDataBase(context);
       
    	 MessageCreator messageCreator = new MessageCreator() {
 			@Override
 			public Message createMessage(Session session) throws JMSException {
 				return session.createTextMessage("ping from Nordine!");
 			}
 		};
 		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
 		System.out.println("-------------------Message envoyé par Jms !!!");
// 		
// 		//// A ajouter pour la version topic
// 		final Destination destination = new Topic() {
// 			
// 			@Override
// 			public String getTopicName() throws JMSException {
// 				return mailboxDestination;
// 			}
// 		};
 		////
 		jmsTemplate.send("a11p6", messageCreator);

    	 

        //context.close();
    }

}
