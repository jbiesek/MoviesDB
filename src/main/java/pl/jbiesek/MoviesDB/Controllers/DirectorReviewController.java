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
        return directorReviewRepository.getReferenceById(id);
    }

    @PostMapping("/directorReview")
    public ResponseEntity<Void> add(@RequestBody DirectorReview directorReview){
        directorReviewRepository.save(directorReview);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
