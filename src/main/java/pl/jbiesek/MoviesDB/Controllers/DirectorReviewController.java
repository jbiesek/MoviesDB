package pl.jbiesek.MoviesDB.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jbiesek.MoviesDB.Entities.DirectorReview;
import pl.jbiesek.MoviesDB.Repositories.DirectorReviewRepository;

import java.util.List;

@RestController
public class DirectorReviewController {

    @Autowired
    DirectorReviewRepository directorReviewRepository;

    @GetMapping("/directorReviews")
    public List<DirectorReview> getAll(){
        return directorReviewRepository.findAll();
    }

    @GetMapping("/directorReview/{id}")
    public DirectorReview getById(@PathVariable("id") int id) {
        if (directorReviewRepository.findById(id).isPresent()) {
            return directorReviewRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @PostMapping("/directorReview")
    public ResponseEntity<Void> add(@RequestBody DirectorReview directorReview){
        directorReviewRepository.save(directorReview);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/directorReview/{id}")
    public ResponseEntity<Void> update (@PathVariable("id") int id, @RequestBody DirectorReview updatedDirectorReview) {
        DirectorReview directorReview = directorReviewRepository.getReferenceById(id);
        if (updatedDirectorReview.getDate_added() != null) {
            directorReview.setDate_added(updatedDirectorReview.getDate_added());
        }
        if (updatedDirectorReview.getTitle() != null) {
            directorReview.setTitle(updatedDirectorReview.getTitle());
        }
        if (updatedDirectorReview.getDescription() != null) {
            directorReview.setDescription(updatedDirectorReview.getDescription());
        }
        if (updatedDirectorReview.getRating() >= 0 && updatedDirectorReview.getRating() <= 10) {
            directorReview.setRating(updatedDirectorReview.getRating());
        }
        if (updatedDirectorReview.getDirector() != null) {
            directorReview.setDirector(updatedDirectorReview.getDirector());
        }
        if (updatedDirectorReview.getUser() != null) {
            directorReview.setUser(updatedDirectorReview.getUser());
        }
        try {
            directorReviewRepository.save(directorReview);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/directorReview/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        try {
            directorReviewRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
