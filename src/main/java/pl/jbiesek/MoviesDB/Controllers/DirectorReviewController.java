package pl.jbiesek.MoviesDB.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jbiesek.MoviesDB.Entities.Director;
import pl.jbiesek.MoviesDB.Entities.DirectorReview;
import pl.jbiesek.MoviesDB.Entities.User;
import pl.jbiesek.MoviesDB.Repositories.DirectorRepository;
import pl.jbiesek.MoviesDB.Repositories.DirectorReviewRepository;
import pl.jbiesek.MoviesDB.Repositories.UserRepository;

import java.util.List;

@RestController
public class DirectorReviewController {

    @Autowired
    DirectorReviewRepository directorReviewRepository;

    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    UserRepository userRepository;

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

    @PostMapping("/directorReview/{directorId}/{userId}")
    public ResponseEntity<Void> addWithDirector(@RequestBody DirectorReview directorReview, @PathVariable("directorId") int directorId, @PathVariable("userId") int userId){
        Director director;
        if (directorRepository.findById(directorId).isPresent()) {
            director = directorRepository.findById(directorId).get();
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        directorReview.setDirector(director);
        User user;
        if (userRepository.findById(userId).isPresent()) {
            user = userRepository.findById(userId).get();
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        directorReview.setUser(user);
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

    @GetMapping("/directorReviews/byDirector/{id}")
    public List<DirectorReview> getReviewsByDirector (@PathVariable("id") int id) {
        return directorReviewRepository.getReviewByDirector(id);
    }

    @GetMapping("/directorReviews/byUser/{id}")
    public List<DirectorReview> getReviewsByUser(@PathVariable("id") int id) {
        return directorReviewRepository.getReviewByUser(id);
    }

    @GetMapping("/directorReview/getRating/{id}")
    public float getDirectorRating(@PathVariable("id") int id) {
        return directorReviewRepository.getDirectorRating(id);
    }
}
