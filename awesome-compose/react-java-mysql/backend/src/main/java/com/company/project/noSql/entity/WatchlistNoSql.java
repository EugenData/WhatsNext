package com.company.project.noSql.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "watchlist")
public class WatchlistNoSql {

    @MongoId
    private String id;

    private Long watchlistId;

    private Integer priority;

    private Date dateCreated;

    @DBRef
    private UserNoSql user;

    @DBRef
    private List<MovieNoSql> movies;

}