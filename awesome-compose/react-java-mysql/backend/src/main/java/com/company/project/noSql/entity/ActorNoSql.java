package com.company.project.noSql.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "actors")
public class ActorNoSql {

    @MongoId
    private String actorId;

    private String actorFullName;

    private Date birthday;

    private String country;

    @DBRef
    private List<MovieNoSql> movieIds;
}