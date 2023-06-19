package com.company.project.entity;

import java.sql.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movie")
public class Movie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long movie_id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String genre;

  @Column(nullable = false)
  private Date releaseDate;

  @ManyToMany(mappedBy = "movies")
  @JsonBackReference
  private List<Watchlist> watchlists;

  @OneToMany(mappedBy = "movie")
  @JsonManagedReference
  private List<Review> reviews;

  // Marvel Cinematic Universe (MCU): The MCU is a vast movie franchise with
  // interconnected stories.
  // For instance, the movie "Iron Man" has sequels like "Iron Man 2" and "Iron
  // Man 3."
  // However, these sequels are not limited to just the "Iron Man" series.
  // Other movies like "The Avengers" and "Captain America: Civil War" also serve
  // as sequels to "Iron Man" and have their own sequels.
  // This creates a complex many-to-many relationship among the movies within the
  // MCU.

  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(name = "movie_sequels", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "sequel_id"))
  @JsonManagedReference
  @JsonBackReference
  private List<Movie> sequels;

  @OneToMany(mappedBy = "movie")
  @JsonManagedReference
  private List<Award> awards;

  @ManyToMany
  @JoinTable(name = "movie_actors", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
  @JsonBackReference
  private List<Actor> actors;

}
