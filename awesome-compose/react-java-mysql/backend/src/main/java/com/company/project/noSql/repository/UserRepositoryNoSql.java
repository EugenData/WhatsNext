package com.company.project.noSql.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.company.project.noSql.entity.UserNoSql;

@Repository
public interface UserRepositoryNoSql extends MongoRepository<UserNoSql, String> {

}
