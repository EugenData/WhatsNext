package com.company.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.project.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByGenreAndReleaseDate(String genre, int releaseDate);

    Movie findByMovieId(Long id);
}