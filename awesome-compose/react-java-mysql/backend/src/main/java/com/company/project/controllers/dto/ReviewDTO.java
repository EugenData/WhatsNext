package com.company.project.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private String reviewText;
    private Long userId;
    private Long movieId;

    // Constructors, getters, and setters
}