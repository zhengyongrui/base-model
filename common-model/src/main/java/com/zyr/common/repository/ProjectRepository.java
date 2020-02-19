package com.zyr.common.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 项目的仓库类
 * 方便以后更换数据库
 * @Author: zhengyongrui
 * @Date: 2019-10-27 23:32
 */
@NoRepositoryBean
public interface ProjectRepository<T, ID> extends MongoRepository<T, ID> {

}