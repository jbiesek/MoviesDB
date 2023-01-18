package pl.jbiesek.MoviesDB.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jbiesek.MoviesDB.Entities.Movie;
import pl.jbiesek.MoviesDB.Entities.MovieReview;
import pl.jbiesek.MoviesDB.Entities.User;
import pl.jbiesek.MoviesDB.Repositories.MovieRepository;
import pl.jbiesek.MoviesDB.Repositories.MovieReviewRepository;
import pl.jbiesek.MoviesDB.Repositories.UserRepository;

import java.util.List;

@RestController
public class MovieReviewController {

    @Autowired
    MovieReviewRepository movieReviewRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/movieReviews")
    public List<MovieReview> getAll() {
        return movieReviewRepository.findAll();
    }

    @GetMapping("/movieReview/{id}")
    public MovieReview getById(@PathVariable("id") int id) {
        if (movieReviewRepository.findById(id).isPresent()) {
            return movieReviewRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @PostMapping("/movieReview")
    public ResponseEntity<Void> add(@RequestBody MovieReview movieReview) {
        movieReviewRepository.save(movieReview);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/movieReview/{movieId}/{userId}")
    public ResponseEntity<Void> addWithMovie(@RequestBody MovieReview movieReview, @PathVariable("movieId") int movieId, @PathVariable("userId") int userId){
        Movie movie;
        if (movieRepository.findById(movieId).isPresent()) {
            movie = movieRepository.findById(movieId).get();
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        movieReview.setMovie(movie);
        User user;
        if (userRepository.findById(userId).isPresent()) {
            user = userRepository.findById(userId).get();
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        movieReview.setUser(user);
        movieReviewRepository.save(movieReview);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/movieReview/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody MovieReview updatedMovieReview){
        MovieReview movieReview = movieReviewRepository.getReferenceById(id);
        if (updatedMovieReview.getTitle() != null) {
            movieReview.setTitle(updatedMovieReview.getTitle());
        }
        if (updatedMovieReview.getDate_added() != null) {
            movieReview.setDate_added(updatedMovieReview.getDate_added());
        }
        if (updatedMovieReview.getDescription() != null) {
            movieReview.setDescription(updatedMovieReview.getDescription());
        }
        if (updatedMovieReview.getRating() >= 0 && updatedMovieReview.getRating() <= 10) {
            movieReview.setRating(updatedMovieReview.getRating());
        }
        if (updatedMovieReview.getMovie() != null) {
            movieReview.setMovie(updatedMovieReview.getMovie());
        }
        if (updatedMovieReview.getUser() != null) {
            movieReview.setUser(updatedMovieReview.getUser());
        }
        try {
            movieReviewRepository.save(movieReview);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/movieReview/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        try {
            movieReviewRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/movieReviews/byMovie/{id}")
    public List<MovieReview> getReviewsByMovie(@PathVariable("id") int id) {
        return movieReviewRepository.getReviewsByMovie(id);
    }

    @GetMapping("/movieReviews/byUser/{id}")
    public List<MovieReview> getReviewsByUser(@PathVariable("id") int id) {
        return movieReviewRepository.getReviewsByUser(id);
    }

    @GetMapping("/movieReviews/getRating/{id}")
    public float getMovieRating (@PathVariable("id") int id) {
        return movieReviewRepository.getMovieRating(id);
    }
}
