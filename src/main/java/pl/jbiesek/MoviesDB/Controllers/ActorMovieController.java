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

    @GetMapping("/actorMovies")
    public List<ActorMovie> getAll() {
        return actorMovieRepository.findAll();
    }

    @GetMapping("actorMovie/{id}")
    public ActorMovie getById(@PathVariable("id") int id) {
        if (actorMovieRepository.findById(id).isPresent()) {
            return actorMovieRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @PostMapping("/actorMovie")
    public ResponseEntity<Void> add(@RequestBody ActorMovie actorMovie) {
        actorMovieRepository.save(actorMovie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/actorMovie/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody ActorMovie updatedActorMovie) {
        ActorMovie actorMovie = actorMovieRepository.getReferenceById(id);
        if (updatedActorMovie.getRole() != null) {
            actorMovie.setRole(updatedActorMovie.getRole());
        }
        if (updatedActorMovie.getActor() != null) {
            actorMovie.setActor(updatedActorMovie.getActor());
        }
        if (updatedActorMovie.getMovie() != null) {
            actorMovie.setMovie(updatedActorMovie.getMovie());
        }
        try {
            actorMovieRepository.save(actorMovie);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/actorMovie/{id}")
    public ResponseEntity<Void> delete (@PathVariable("id") int id) {
        try {
            actorMovieRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
