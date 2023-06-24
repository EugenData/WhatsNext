package com.company.project.noSql.entity;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class UserNoSql {

    @MongoId
    private String userId;

    private String userName;

    private String password;

    private String email;
    
    @DBRef
    private List<WatchlistNoSql> watchlistIds;

    @DBRef
    private List<ReviewNoSql> reviews;

}