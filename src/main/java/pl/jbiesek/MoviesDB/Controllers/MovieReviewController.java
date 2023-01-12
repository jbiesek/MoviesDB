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
        return movieReviewRepository.getReferenceById(id);
    }

    @PostMapping("/movieReview")
    public ResponseEntity<Void> add(@RequestBody MovieReview movieReview) {
        movieReviewRepository.save(movieReview);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
