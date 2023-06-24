package com.company.project.controllers;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.controllers.dto.ReviewDTO;
import com.company.project.entity.Movie;
import com.company.project.entity.Review;
import com.company.project.entity.User;
import com.company.project.repository.MovieRepository;
import com.company.project.repository.ReviewRepository;
import com.company.project.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @PostMapping(path = "/create", produces = "application/json")
    public ResponseEntity<String> createReview(@RequestBody ReviewDTO reviewDTO) {

        User user = userRepository.findById(reviewDTO.getUserId()).orElse(null);
        Movie movie = movieRepository.findById(reviewDTO.getMovieId()).orElse(null);

        if (user == null || movie == null || reviewDTO.getReviewText() == null) {
            return ResponseEntity.badRequest().body("Invalid user or movie ID");
        }

        Review review = new Review();
        review.setReviewText(reviewDTO.getReviewText());
        review.setTypeOfReview(reviewDTO.getTypeOfReview());
        review.setDateCreated(new Date(2000, 11, 10));
        review.setUser(user);
        review.setMovie(movie);

        reviewRepository.save(review);

        return ResponseEntity.ok("Review created successfully");
    }

    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<?> checkIfUserMadeReview(@RequestHeader("user-id") String userID,
            @RequestHeader("movie-id") String movieID) {
        Optional<Review> review = reviewRepository.findByUser_IdAndMovie_MovieId(Long.parseLong(userID),
                Long.parseLong(movieID));
        if (review.isPresent()) {
            return ResponseEntity.ok().body(review.get());
        } else {
            return ResponseEntity.ok().body(new Review());
        }
    }
}
