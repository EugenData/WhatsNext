package com.company.project.noSql.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reviews")

public class ReviewNoSql {
    @MongoId
    private String reviewId;

    private String reviewText;

    private Date dateCreated;

    @DBRef
    private UserNoSql users;

}
