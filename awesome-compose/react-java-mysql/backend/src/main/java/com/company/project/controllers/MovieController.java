package com.company.project.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.project.entity.Movie;
import com.company.project.entity.User;
import com.company.project.repository.MovieRepository;
import com.company.project.services.UserService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private MovieRepository movieRepository;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movie = movieRepository.findAll();
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }
}