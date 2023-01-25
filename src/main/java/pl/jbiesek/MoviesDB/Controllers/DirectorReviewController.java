package pl.jbiesek.MoviesDB.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jbiesek.MoviesDB.Entities.DirectorReview;
import pl.jbiesek.MoviesDB.Services.DirectorReviewService;

import java.util.List;

@RestController
public class DirectorReviewController {

    @Autowired
    DirectorReviewService directorReviewService;


    @GetMapping("/directorReviews")
    public List<DirectorReview> getAll() {
        return directorReviewService.getAll();
    }

    @GetMapping("/directorReview/{id}")
    public DirectorReview getById(@PathVariable("id") int id) {
        return directorReviewService.getById(id);
    }

    @PostMapping("/directorReview")
    public ResponseEntity<Void> add(@RequestBody DirectorReview directorReview) {
        directorReviewService.add(directorReview);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/directorReview/{directorId}/{userId}")
    public ResponseEntity<Void> addWithDirector(@RequestBody DirectorReview directorReview, @PathVariable("directorId") int directorId, @PathVariable("userId") int userId) {
        if (directorReviewService.addWithDirector(directorReview, directorId, userId)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/directorReview/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody DirectorReview updatedDirectorReview) {
        if (directorReviewService.update(id, updatedDirectorReview)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/directorReview/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        if (directorReviewService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/directorReviews/byDirector/{id}")
    public List<DirectorReview> getReviewsByDirector(@PathVariable("id") int id) {
        return directorReviewService.getReviewsByDirector(id);
    }

    @GetMapping("/directorReviews/byUser/{id}")
    public List<DirectorReview> getReviewsByUser(@PathVariable("id") int id) {
        return directorReviewService.getReviewsByUser(id);
    }

    @GetMapping("/directorReview/getRating/{id}")
    public float getDirectorRating(@PathVariable("id") int id) {
        return directorReviewService.getDirectorRating(id);
    }
}
