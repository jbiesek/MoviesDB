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


}
