package com.company.project.noSql.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data

@AllArgsConstructor
@Getter
@Setter
@Document(collection = "movies")
public class MovieNoSql {

    @MongoId
    private String movieId;

    private String title;

    private String genre;

    private int releaseDate;

    @DBRef
    private List<WatchlistNoSql> watchlistIds;

    @DBRef
    private List<ReviewNoSql> reviews;

    @DBRef
    private MovieNoSql parentMovieId;

    @DBRef
    private List<MovieNoSql> sequelMovieIds;

    @DBRef
    private List<AwardNoSql> awardIds;

    @DBRef
    private List<ActorNoSql> actorIds;

    public MovieNoSql() {
        this.reviews = new ArrayList<>();
    }

}