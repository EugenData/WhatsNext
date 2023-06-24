package com.company.project.noSql.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.company.project.noSql.entity.MovieNoSql;


@Repository
public interface  MovieRepositoryNoSql extends MongoRepository<MovieNoSql, String>{
    
}
