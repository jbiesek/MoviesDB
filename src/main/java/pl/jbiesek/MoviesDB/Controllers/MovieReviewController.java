package pl.jbiesek.MoviesDB.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jbiesek.MoviesDB.Entities.MovieReview;
import pl.jbiesek.MoviesDB.Services.MovieReviewService;

import java.util.List;

@RestController
public class MovieReviewController {

    @Autowired
    MovieReviewService movieReviewService;

    @GetMapping("/movieReviews")
    public List<MovieReview> getAll() {
        return movieReviewService.getAll();
    }

    @GetMapping("/movieReview/{id}")
    public MovieReview getById(@PathVariable("id") int id) {
        return movieReviewService.getById(id);
    }

    @PostMapping("/movieReview")
    public ResponseEntity<Void> add(@RequestBody MovieReview movieReview) {
        movieReviewService.add(movieReview);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/movieReview/{movieId}/{userId}")
    public ResponseEntity<Void> addWithMovie(@RequestBody MovieReview movieReview, @PathVariable("movieId") int movieId, @PathVariable("userId") int userId) {
        if (movieReviewService.addWithMovie(movieReview, movieId, userId)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/movieReview/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody MovieReview updatedMovieReview) {
        if (movieReviewService.update(id, updatedMovieReview)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/movieReview/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        if (movieReviewService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/movieReviews/byMovie/{id}")
    public List<MovieReview> getReviewsByMovie(@PathVariable("id") int id) {
        return movieReviewService.getReviewsByMovie(id);
    }

    @GetMapping("/movieReviews/byUser/{id}")
    public List<MovieReview> getReviewsByUser(@PathVariable("id") int id) {
        return movieReviewService.getReviewsByUser(id);
    }

    @GetMapping("/movieReviews/getRating/{id}")
    public float getMovieRating(@PathVariable("id") int id) {
        return movieReviewService.getMovieRating(id);
    }
}
