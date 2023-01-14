package pl.jbiesek.MoviesDB.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jbiesek.MoviesDB.Entities.MovieReview;
import pl.jbiesek.MoviesDB.Repositories.MovieReviewRepository;

import java.util.List;

@RestController
public class MovieReviewController {

    @Autowired
    MovieReviewRepository movieReviewRepository;

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
}
