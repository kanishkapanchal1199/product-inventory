package com.coachbar.productinventory.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.coachbar.productinventory.repository")
public class MongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private static String connectionStringURI;

    @Value("${spring.data.mongodb.database}")
    private static String databaseName;
    @Bean
    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString(MongoConfig.connectionStringURI);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), MongoConfig.databaseName);
    }
}


