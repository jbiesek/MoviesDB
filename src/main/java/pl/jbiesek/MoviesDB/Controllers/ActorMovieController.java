package pl.jbiesek.MoviesDB.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jbiesek.MoviesDB.Entities.ActorMovie;
import pl.jbiesek.MoviesDB.Repositories.ActorMovieRepository;

import java.util.List;

@RestController
public class ActorMovieController {

    @Autowired
    ActorMovieRepository actorMovieRepository;

    @GetMapping("/actormovies")
    public List<ActorMovie> getAll() {
        return actorMovieRepository.findAll();
    }

    @GetMapping("actormovie/{id}")
    public ActorMovie getById(@PathVariable("id") int id) {
        return actorMovieRepository.getReferenceById(id);
    }

    @PostMapping("/actormovie")
    public ResponseEntity<Void> add(@RequestBody ActorMovie actorMovie) {
        actorMovieRepository.save(actorMovie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
