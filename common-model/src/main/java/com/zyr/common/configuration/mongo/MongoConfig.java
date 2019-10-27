package com.zyr.common.configuration.mongo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

@Configuration
public class MongoConfig {

    @Bean
    public MongoTransactionManager transactionManager(MongoDbFactory dbFactory)
    {
        return new MongoTransactionManager(dbFactory);
    }
}
