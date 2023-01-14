package pl.jbiesek.MoviesDB.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jbiesek.MoviesDB.Entities.ActorReview;
import pl.jbiesek.MoviesDB.Repositories.ActorReviewRepository;

import java.util.List;

@RestController
public class ActorReviewController {

    @Autowired
    ActorReviewRepository actorReviewRepository;

    @GetMapping("/actorReviews")
    public List<ActorReview> getAll() {
        return actorReviewRepository.findAll();
    }

    @GetMapping("actorReview/{id}")
    public ActorReview getById(@PathVariable("id") int id) {
        return actorReviewRepository.getReferenceById(id);
    }

    @PostMapping("/actorReview")
    public ResponseEntity<Void> add(@RequestBody ActorReview actorReview){
        actorReviewRepository.save(actorReview);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/actorReview/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody ActorReview updatedActorReview) {
        ActorReview actorReview = actorReviewRepository.getReferenceById(id);
        if (updatedActorReview.getDate_added() != null) {
            actorReview.setDate_added(updatedActorReview.getDate_added());
        }
        if (updatedActorReview.getTitle() != null) {
            actorReview.setTitle(updatedActorReview.getTitle());
        }
        if (updatedActorReview.getDescription() != null) {
            actorReview.setDescription(updatedActorReview.getDescription());
        }
        if (updatedActorReview.getRating() >= 0 && updatedActorReview.getRating() <= 10) {
            actorReview.setRating(updatedActorReview.getRating());
        }
        if (updatedActorReview.getActor() != null) {
            actorReview.setActor(updatedActorReview.getActor());
        }
        if (updatedActorReview.getUser() != null) {
            actorReview.setUser(updatedActorReview.getUser());
        }
        try {
            actorReviewRepository.save(actorReview);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/actorReview/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        try {
            actorReviewRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
