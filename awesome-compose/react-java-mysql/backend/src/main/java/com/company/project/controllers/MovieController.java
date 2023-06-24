package com.company.project.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.project.controllers.dto.MovieDTO;
import com.company.project.controllers.dto.MovieMapper;
import com.company.project.controllers.dto.MoviePlusDTO;
import com.company.project.entity.Movie;
import com.company.project.repository.MovieRepository;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private MovieRepository movieRepository;

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieDTO> movieDTOs = new ArrayList<>();

        for (Movie movie : movies) {
            MovieDTO movieDTO = MovieMapper.toDTO(movie);
            movieDTOs.add(movieDTO);
        }

        return new ResponseEntity<>(movieDTOs, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<MoviePlusDTO> getMoviesByid(@PathVariable String id) {
        Movie movie = movieRepository.findByMovieId(Long.parseLong(id));
        MoviePlusDTO moviePlusDTO = MovieMapper.toDTOplus(movie);
        return new ResponseEntity<>(moviePlusDTO, HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<Movie> getBestReviewedHorrorMovie() {
        List<Movie> horrorMovies = movieRepository.findByGenreAndReleaseDate("Horror", 2007);
        if (horrorMovies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Movie bestReviewedMovie = horrorMovies.get(0);
        int maxReviewCount = 0;
        int maxAwardCount = 0;

        for (Movie movie : horrorMovies) {
            int reviewCount = movie.getReviews().size();
            int awardCount = movie.getAwards().size();

            if (reviewCount > maxReviewCount || (reviewCount == maxReviewCount && awardCount > maxAwardCount)) {
                bestReviewedMovie = movie;
                maxReviewCount = reviewCount;
                maxAwardCount = awardCount;
            }
        }

        return new ResponseEntity<>(bestReviewedMovie, HttpStatus.OK);
    }
}