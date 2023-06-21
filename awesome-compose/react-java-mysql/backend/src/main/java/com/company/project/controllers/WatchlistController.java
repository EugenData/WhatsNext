package com.company.project.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.project.entity.Movie;
import com.company.project.entity.User;
import com.company.project.entity.Watchlist;
import com.company.project.repository.MovieRepository;
import com.company.project.repository.WatchlistRepository;
import com.company.project.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/watchlist")
public class WatchlistController {

    private WatchlistRepository watchlistRepository;

    @GetMapping
    public ResponseEntity<List<Watchlist>> getAllWatchlists() {
        List<Watchlist> watchlist = watchlistRepository.findAll();
        return new ResponseEntity<>(watchlist, HttpStatus.OK);
    }
}