package com.company.project;

import java.sql.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.company.project.noSql.entity.MovieNoSql;
import com.company.project.noSql.entity.ReviewNoSql;
import com.company.project.noSql.entity.UserNoSql;
import com.company.project.noSql.repository.MovieRepositoryNoSql;
import com.company.project.noSql.repository.ReviewRepositoryNoSql;
import com.company.project.noSql.repository.UserRepositoryNoSql;
import com.github.javafaker.Faker;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepositoryNoSql userRepositoryNoSql;
    private final MovieRepositoryNoSql movieRepositoryNoSql;
    private final ReviewRepositoryNoSql reviewRepositoryNoSql;

    public DataInitializer(UserRepositoryNoSql userRepositoryNoSql, MovieRepositoryNoSql movieRepositoryNoSql,
            ReviewRepositoryNoSql reviewRepositoryNoSql) {
        this.userRepositoryNoSql = userRepositoryNoSql;
        this.movieRepositoryNoSql = movieRepositoryNoSql;
        this.reviewRepositoryNoSql = reviewRepositoryNoSql;
    }

    @Override
    public void run(String... args) throws Exception {
        UserNoSql user1 = new UserNoSql();
        user1.setUserName("john_doe");
        user1.setPassword("password1");
        user1.setEmail("john.doe@example.com");
        userRepositoryNoSql.save(user1);

        UserNoSql user2 = new UserNoSql();
        user2.setUserName("jane_smith");
        user2.setPassword("password2");
        user2.setEmail("jane.smith@example.com");
        userRepositoryNoSql.save(user2);

        MovieNoSql movie1 = new MovieNoSql();
        movie1.setTitle("Movie 1");
        movie1.setGenre("Action");
        movie1.setReleaseDate(2022);
        movieRepositoryNoSql.save(movie1);

        MovieNoSql movie2 = new MovieNoSql();
        movie2.setTitle("Movie 2");
        movie2.setGenre("Comedy");
        movie2.setReleaseDate(2023);
        movieRepositoryNoSql.save(movie2);

        ReviewNoSql review1 = new ReviewNoSql();
        review1.setReviewText("Great movie!");
        review1.setDateCreated(new Date(3000, 10, 10));
        review1.setUsers(user1);
        reviewRepositoryNoSql.save(review1);

        ReviewNoSql review2 = new ReviewNoSql();
        review2.setReviewText("Enjoyed it!");
        review2.setDateCreated(new Date(3000, 10, 10));
        review2.setUsers(user2);
        reviewRepositoryNoSql.save(review2);

        movie1.getReviews().add(review1);
        movie2.getReviews().add(review2);

        movieRepositoryNoSql.save(movie1);
        movieRepositoryNoSql.save(movie2);

    }
}