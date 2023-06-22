package com.company.project.controllers.dto;

import com.company.project.entity.Movie;

public class MovieMapper {
    public static MovieDTO toDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieId(movie.getMovieId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setGenre(movie.getGenre());
        movieDTO.setReleaseDate(movie.getReleaseDate());

        return movieDTO;
    }

    public static MoviePlusDTO toDTOplus(Movie movie) {
        MoviePlusDTO movieDTO = new MoviePlusDTO();
        movieDTO.setMovieId(movie.getMovieId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setGenre(movie.getGenre());
        movieDTO.setReleaseDate(movie.getReleaseDate());
        movieDTO.setReviews(movie.getReviews());
        movieDTO.setAwards(movie.getAwards());

        return movieDTO;
    }
}
