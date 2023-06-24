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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "award")
public class AwardNoSql {

    @MongoId
    private String id;

    private String awardName;

    private Date dateAwarded;

    @DBRef
    private List<MovieNoSql> movie;

}