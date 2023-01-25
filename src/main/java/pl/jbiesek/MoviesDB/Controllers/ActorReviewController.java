package pl.jbiesek.MoviesDB.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jbiesek.MoviesDB.Entities.ActorReview;
import pl.jbiesek.MoviesDB.Services.ActorReviewService;

import java.util.List;

@RestController
public class ActorReviewController {

    @Autowired
    ActorReviewService actorReviewService;

    @GetMapping("/actorReviews")
    public List<ActorReview> getAll() {
        return actorReviewService.getAll();
    }

    @GetMapping("actorReview/{id}")
    public ActorReview getById(@PathVariable("id") int id) {
        return actorReviewService.getById(id);
    }

    @PostMapping("/actorReview")
    public ResponseEntity<Void> add(@RequestBody ActorReview actorReview) {
        actorReviewService.add(actorReview);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/actorReview/{actorId}/{userId}")
    public ResponseEntity<Void> addWithActor(@RequestBody ActorReview actorReview, @PathVariable("actorId") int actorId, @PathVariable("userId") int userId) {
        if (actorReviewService.addWithActor(actorReview, actorId, userId)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/actorReview/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody ActorReview updatedActorReview) {
        if (actorReviewService.update(id, updatedActorReview)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/actorReview/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        if (actorReviewService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/actorReviews/byActor/{id}")
    public List<ActorReview> getActorReviewByActorId(@PathVariable("id") int id) {
        return actorReviewService.getActorReviewByActorId(id);
    }

    @GetMapping("/actorReviews/byUser/{id}")
    public List<ActorReview> getActorReviewByUserId(@PathVariable("id") int id) {
        return actorReviewService.getActorReviewByUserId(id);
    }

    @GetMapping("/actorReview/getRating/{id}")
    public float getActorRating(@PathVariable("id") int id) {
        return actorReviewService.getActorRating(id);
    }
}
