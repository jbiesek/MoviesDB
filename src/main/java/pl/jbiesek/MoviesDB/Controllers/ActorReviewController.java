package pl.jbiesek.MoviesDB.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jbiesek.MoviesDB.Entities.Actor;
import pl.jbiesek.MoviesDB.Entities.ActorReview;
import pl.jbiesek.MoviesDB.Entities.User;
import pl.jbiesek.MoviesDB.Repositories.ActorRepository;
import pl.jbiesek.MoviesDB.Repositories.ActorReviewRepository;
import pl.jbiesek.MoviesDB.Repositories.UserRepository;

import java.util.List;

@RestController
public class ActorReviewController {

    @Autowired
    ActorReviewRepository actorReviewRepository;

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/actorReviews")
    public List<ActorReview> getAll() {
        return actorReviewRepository.findAll();
    }

    @GetMapping("actorReview/{id}")
    public ActorReview getById(@PathVariable("id") int id) {
        if (actorReviewRepository.findById(id).isPresent()) {
            return actorReviewRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @PostMapping("/actorReview")
    public ResponseEntity<Void> add(@RequestBody ActorReview actorReview){
        actorReviewRepository.save(actorReview);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/actorReview/{actorId}/{userId}")
    public ResponseEntity<Void> addWithActor(@RequestBody ActorReview actorReview, @PathVariable("actorId") int actorId, @PathVariable("userId") int userId){
        Actor actor;
        if (actorRepository.findById(actorId).isPresent()) {
            actor = actorRepository.findById(actorId).get();
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        actorReview.setActor(actor);
        User user;
        if (userRepository.findById(userId).isPresent()) {
            user = userRepository.findById(userId).get();
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        actorReview.setUser(user);
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

    @GetMapping("/actorReview/byActor/{id}")
    public List<ActorReview> getActorReviewByActorId(@PathVariable("id") int id) {
        return actorReviewRepository.getReviewByActorId(id);
    }

    @GetMapping("/actorReview/byUser/{id}")
    public List<ActorReview> getActorReviewByUserId(@PathVariable("id") int id) {
        return actorReviewRepository.getReviewByUserId(id);
    }

    @GetMapping("/actorReview/getRating/{id}")
    public float getActorRating(@PathVariable("id") int id) {
        return actorReviewRepository.getActorRating(id);
    }
}
