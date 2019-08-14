package com.udacity.reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.MongoRepository;

@SpringBootApplication
@EnableMongoAuditing
public class ReviewsApplication {

    @Autowired
    MongoRepository mongoRepository;

    public static void main(String[] args) {
        SpringApplication.run(ReviewsApplication.class, args);
    }

    @Bean
    CommandLineRunner preLoadMongo() throws Exception {
        return args -> {
            mongoRepository.deleteAll();
        };
    }


}
