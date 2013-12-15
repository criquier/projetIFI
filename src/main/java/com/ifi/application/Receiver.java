package com.ifi.application;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.FileSystemUtils;

import com.google.gson.Gson;
import com.ifi.model.Article;
import com.ifi.repositories.ArticleRepository;
import com.ifi.repositories.TagRepository;
import com.ifi.repositories.UserRepository;

public class Receiver {
    /**
     * Get a copy of the application context
     */
    @Autowired
    ConfigurableApplicationContext context;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TagRepository tagRepository;
    Gson gson = new Gson();
    /**
     * When you receive a message, print it out, then shut down the application.
     * Finally, clean up any ActiveMQ server stuff.
     * @param message
     */
    public void receiveMessage(String message) {
        System.out.println("------------------------------ Message recu dans le Receive <" + message + ">");
       // context.close();
        Article a = gson.fromJson(message, Article.class);
        articleRepository.save(a);
        FileSystemUtils.deleteRecursively(new File("activemq-data"));
    }
}
