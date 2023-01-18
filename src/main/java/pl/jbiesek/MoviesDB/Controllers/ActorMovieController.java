package pl.jbiesek.MoviesDB.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jbiesek.MoviesDB.Entities.*;
import pl.jbiesek.MoviesDB.Repositories.ActorMovieRepository;
import pl.jbiesek.MoviesDB.Repositories.ActorRepository;
import pl.jbiesek.MoviesDB.Repositories.MovieRepository;

import java.util.List;

@RestController
public class ActorMovieController {

    @Autowired
    ActorMovieRepository actorMovieRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ActorRepository actorRepository;

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

    @PostMapping("/actorMovie/{actorId}/{movieId}")
    public ResponseEntity<Void> addWithMovieAndActor(@RequestBody ActorMovie actorMovie, @PathVariable("movieId") int movieId, @PathVariable("actorId") int actorId){
        Movie movie;
        if (movieRepository.findById(movieId).isPresent()) {
            movie = movieRepository.findById(movieId).get();
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        actorMovie.setMovie(movie);
        Actor actor;
        if (actorRepository.findById(actorId).isPresent()) {
            actor = actorRepository.findById(actorId).get();
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        actorMovie.setActor(actor);
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

    @GetMapping("actorMovie/role/{id1}/{id2}")
    public String getRoleByActorIdMovieId (@PathVariable("id1") int id1, @PathVariable("id2") int id2){
        return actorMovieRepository.getRoleByActorAndMovie(id1, id2);
    }
}
