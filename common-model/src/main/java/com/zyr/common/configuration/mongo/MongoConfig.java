package com.zyr.common.configuration.mongo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

/**
 * @author zhengyongrui
 */
@Configuration
public class MongoConfig {

    /**
     * 添加事务配置
     * @param dbFactory mongoDb数据库工厂
     * @return
     */
    @Bean
    public MongoTransactionManager transactionManager(MongoDbFactory dbFactory)
    {
        return new MongoTransactionManager(dbFactory);
    }
}
