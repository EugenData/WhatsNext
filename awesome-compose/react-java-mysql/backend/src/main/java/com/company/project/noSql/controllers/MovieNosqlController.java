package com.company.project.noSql.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.noSql.entity.MovieNoSql;
import com.company.project.noSql.repository.MovieRepositoryNoSql;

@RestController
@RequestMapping("/create/movie")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieNosqlController {

    private MovieRepositoryNoSql movieRepository;

    @PostMapping
    public MovieNoSql saveMovie(@RequestBody MovieNoSql movie) {
        return movieRepository.save(movie);
    }

}
