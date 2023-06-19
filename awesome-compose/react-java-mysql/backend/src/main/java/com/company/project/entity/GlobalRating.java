package com.company.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "global_rating")
public class GlobalRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gr_id;

    @Column(nullable = false)
    private String award_name;

    @Column(nullable = false)
    private Integer rating;
}