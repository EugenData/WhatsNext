package com.company.project.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "movie")
// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
// property = "movie_id")
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long movieId;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String genre;

  @Column(nullable = false)
  private int releaseDate;

  @ManyToMany(mappedBy = "movies")
  @JsonBackReference
  private List<Watchlist> watchlists;

  @OneToMany(mappedBy = "movie")
  @JsonManagedReference
  private List<Review> reviews;

  @ManyToOne
  @JoinColumn(name = "parent_movie_id")
  @JsonBackReference
  private Movie parentMovie;

  @OneToMany(mappedBy = "parentMovie")
  @JsonManagedReference
  private List<Movie> sequels;

  {
    sequels = new ArrayList<>();
  }

  @OneToMany(mappedBy = "movie")
  @JsonManagedReference
  private List<Award> awards;

  @ManyToMany
  @JoinTable(name = "movie_actors", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
  @JsonManagedReference
  private List<Actor> actors;
  {
    actors = new ArrayList<>();
  }

}
