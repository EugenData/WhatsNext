package com.company.project.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

  private Long movieId;
  private String title;
  private String genre;
  private int releaseDate;

}