package com.company.project.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.company.project.entity.Actor;
import com.company.project.entity.Award;
import com.company.project.entity.Movie;
import com.company.project.entity.Review;
import com.company.project.entity.User;
import com.company.project.entity.Watchlist;
import com.company.project.repository.ActorRepository;
import com.company.project.repository.AwardRepository;
import com.company.project.repository.MovieRepository;
import com.company.project.repository.ReviewRepository;
import com.company.project.repository.UserRepository;
import com.company.project.repository.WatchlistRepository;
import com.github.javafaker.Faker;

import lombok.NoArgsConstructor;

@Service
public class PopulateDatabase {

    private Faker faker;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final WatchlistRepository watchlistRepository;
    private final ReviewRepository reviewRepository;
    private final AwardRepository awardRepository;
    private final ActorRepository actorRepository;

    public PopulateDatabase(UserRepository userRepository, MovieRepository movieRepository,
            WatchlistRepository watchlistRepository, ReviewRepository reviewRepository,
            AwardRepository awardRepository, ActorRepository actorRepository) {

        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.watchlistRepository = watchlistRepository;
        this.reviewRepository = reviewRepository;
        this.awardRepository = awardRepository;
        this.actorRepository = actorRepository;
        this.faker = new Faker();
    }

    public void populateAll() {
        generateActors();
        generateMovies();
        generateSequels();
        generateUsers();
        generateWatchlists();
        addWatchlistsToUsers();
        generateReviews();
        generateAwards();
    }

    private void generateUsers() {
        var user_amount = 3;

        var domains = new String[] {
                "gmail.com",
                "protonmail.com",
                "icloud.com"
        };

        var users = new User[user_amount];

        for (int i = 0; i < user_amount; i++) {
            var n = Faker.instance().name();
            var userName = n.firstName();
            var password = Faker.instance().random().toString();
            var email = userName + "@" + domains[i % domains.length];
            var id = Faker.instance().random().nextLong();

            users[i] = new User(id, userName, password, email, null, null);

        }
        userRepository.saveAll(List.of(users));
    }

    private void generateMovies() {
        var movies_amount = 10;
        List<Movie> movies = new ArrayList<>();

        for (int i = 0; i < movies_amount; i++) {
            Movie movie = new Movie();
            movie.setTitle(faker.book().title());
            movie.setGenre(faker.book().genre());

            movie.setReleaseDate(faker.random().nextInt(2000, 2011));

            movie.setActors(getRandomActors());
            movies.add(movie);
        }
        movieRepository.saveAll(movies);
    }

    private void generateWatchlists() {
        List<Watchlist> watchlists = new ArrayList<>();
        List<User> users = userRepository.findAll();

        for (User user : users) {
            for (int i = 0; i < 3; i++) {

                Watchlist watchlist = new Watchlist();
                watchlist.setPriority(faker.random().nextInt(1, 11));
                watchlist.setDateCreated(new Date(faker.random().nextInt(1, 11)));
                watchlist.setUser(user);
                watchlists.add(watchlist);

            }

            watchlistRepository.saveAll(watchlists);
        }
    }

    private void addWatchlistsToUsers() {

        List<Watchlist> watchlists = watchlistRepository.findAll();

        for (Watchlist watchlist : watchlists) {
            watchlist.getMovies().addAll(getRandomMovies());
            watchlistRepository.save(watchlist);
        }
    }

    private List<Movie> getRandomMovies() {
        List<Movie> movies = new ArrayList<>(movieRepository.findAll());
        List<Movie> randomMovies = new ArrayList<>();

        int totalMovies = movies.size();

        for (int i = 0; i < faker.random().nextInt(1, 5); i++) {
            int randomIndex = faker.random().nextInt(totalMovies);
            Movie movie = movies.get(randomIndex);
            if (movie != null && movie.getMovieId() != null) {
                randomMovies.add(movie);
            }
        }

        return randomMovies;
    }

    private void generateReviews() {
        List<Review> reviews = new ArrayList<>();

        List<User> users = userRepository.findAll();
        List<Movie> movies = new ArrayList<>(movieRepository.findAll());

        for (User user : users) {
            for (int i = 0; i < faker.random().nextInt(1, 5); i++) {
                int randomIndex = faker.random().nextInt(movies.size());
                Movie movie = movies.get(randomIndex);
                Review review = new Review();
                review.setUser(user);
                review.setMovie(movie);
                review.setDateCreated(new Date(faker.random().nextInt(1, 11)));
                review.setReviewText(faker.lorem().sentence());
                reviews.add(review);
                movies.remove(randomIndex);
            }
        }

        reviewRepository.saveAll(reviews);
    }

    private List<Actor> getRandomActors() {
        var actors = actorRepository.findAll();

        List<Actor> randomActors = new ArrayList<>();
        int totalMovies = actors.size();

        for (int i = 0; i < faker.random().nextInt(1, 5); i++) {
            int randomIndex = faker.random().nextInt(totalMovies);
            randomActors.add(actors.get(randomIndex));
        }

        return randomActors;
    }

    private void generateSequels() {
        List<Movie> movies = movieRepository.findAll();

        for (Movie movie : movies) {
            // Generate a random number of sequels for each movie
            int numSequels = faker.random().nextInt(1, 3);

            for (int i = 0; i < numSequels; i++) {
                // Create a new Movie object for the sequel
                Movie sequel = new Movie();
                sequel.setTitle(faker.book().title()); // Generate a random title
                sequel.setGenre(faker.book().genre()); // Generate a random genre
                sequel.setReleaseDate(faker.random().nextInt(2000, 2011)); // Generate a random release date

                sequel.setParentMovie(movie);
                // Add the sequel to the current movie's sequels list
                movie.getSequels().add(sequel);

                // Establish the bidirectional relationship between the original movie and its
                // sequel
                sequel.setParentMovie(movie);

                // Save the sequel movie to the repository
                movieRepository.save(sequel);
            }

            // Save the original movie to the repository
            movieRepository.save(movie);
        }
    }

    private void generateAwards() {
        List<Award> awards = new ArrayList<>();
        List<Movie> movies = movieRepository.findAll();

        for (Movie movie : movies) {
            Award award = new Award();
            award.setAward_name(faker.elderScrolls().city());
            award.setDateAwarded(new Date(faker.random().nextInt(1, 11)));
            award.setMovie(movie);
            awards.add(award);
        }

        awardRepository.saveAll(awards);
    }

    private void generateActors() {
        var actor_amount = 10;
        List<Actor> actors = new ArrayList<>();

        for (int i = 0; i < actor_amount; i++) {
            Actor actor = new Actor();
            actor.setActor_fullName(faker.name().fullName());
            actor.setBithday(new Date(faker.random().nextInt(1, 11)));
            actor.setCountry(faker.country().name());

            actors.add(actor);
        }

        actorRepository.saveAll(actors);

    }

}
