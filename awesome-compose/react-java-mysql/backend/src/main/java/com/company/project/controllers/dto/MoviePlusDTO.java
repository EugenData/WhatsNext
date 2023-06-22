package com.company.project.controllers.dto;

import java.util.List;

import com.company.project.entity.Award;
import com.company.project.entity.Review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MoviePlusDTO {

    private Long movieId;

    private String title;

    private String genre;

    private int releaseDate;

    private List<Review> reviews;

    private List<Award> awards;

}