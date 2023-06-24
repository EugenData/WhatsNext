package com.company.project.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "watchlist")

public class Watchlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long watchlist_id;

    @Column(nullable = false)
    private Integer priority;

    @Column(nullable = false)
    private Date dateCreated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToMany
    @JoinTable(name = "watchlist_movie", joinColumns = @JoinColumn(name = "watchlist_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
    @JsonManagedReference
    private List<Movie> movies;
    {
        movies = new ArrayList<>();
    }

}
