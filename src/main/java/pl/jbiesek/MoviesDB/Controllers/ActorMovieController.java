package pl.jbiesek.MoviesDB.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jbiesek.MoviesDB.Entities.ActorMovie;
import pl.jbiesek.MoviesDB.Services.ActorMovieService;

import java.util.List;

@RestController
public class ActorMovieController {

    @Autowired
    ActorMovieService actorMovieService;


    @GetMapping("/actorMovies")
    public List<ActorMovie> getAll() {
        return actorMovieService.getAll();
    }

    @GetMapping("actorMovie/{id}")
    public ActorMovie getById(@PathVariable("id") int id) {
        return actorMovieService.getById(id);
    }

    @PostMapping("/actorMovie")
    public ResponseEntity<Void> add(@RequestBody ActorMovie actorMovie) {
        if (actorMovieService.add(actorMovie)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/actorMovie/{actorId}/{movieId}")
    public ResponseEntity<Void> addWithMovieAndActor(@RequestBody ActorMovie actorMovie, @PathVariable("movieId") int movieId, @PathVariable("actorId") int actorId) {
        if (actorMovieService.addWithMovieAndActor(actorMovie, movieId, actorId)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/actorMovie/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody ActorMovie updatedActorMovie) {
        if (actorMovieService.update(id, updatedActorMovie)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/actorMovie/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        if (actorMovieService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("actorMovie/role/{id1}/{id2}")
    public String getRoleByActorIdMovieId(@PathVariable("id1") int id1, @PathVariable("id2") int id2) {
        return actorMovieService.getRoleByActorIdMovieId(id1, id2);
    }
}
