package pl.jbiesek.MoviesDB.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jbiesek.MoviesDB.Entities.Actor;
import pl.jbiesek.MoviesDB.Services.ActorService;

import java.io.IOException;
import java.util.List;

@RestController
public class ActorController {

    @Autowired
    ActorService actorService;

    @GetMapping("/actors")
    public List<Actor> getAll() {
        return actorService.getAll();
    }

    @GetMapping("actor/{id}")
    public Actor getById(@PathVariable("id") int id) {
        return actorService.getById(id);
    }

    @PostMapping("/actor")
    public ResponseEntity<Void> add(@RequestBody Actor actor) {
        if(actorService.add(actor)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/actor/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Actor updatedActor){
        if(actorService.update(id, updatedActor)){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/actor/{id}")
    public ResponseEntity<Void> delete (@PathVariable("id") int id) {
        if(actorService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/actor/byReview/{id}")
    public Actor getActorByReview(@PathVariable("id") int id) {
        return actorService.getActorByReview(id);
    }

    @GetMapping("/actors/byUser/{id}")
    public List<Actor> getActorsByUser(@PathVariable("id") int id) {
        return actorService.getActorsByUser(id);
    }

    @GetMapping("/actors/byMovie/{id}")
    public List<Actor> getActorsByMovie(@PathVariable("id") int id) {
        return actorService.getActorsByMovie(id);
    }

    // i'm not sure if usage of object is correct, although it kinda works
    @GetMapping("/actors/byMovieWithRole/{id}")
    public List<Object> getActorsByMovieWithRole(@PathVariable("id") int id) {
        return actorService.getActorsByMovieWithRole(id);
    }

    @RequestMapping(value = "/actor/image/{id}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("id") int id) throws IOException {
        byte[] bytes = actorService.getImage(id);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
}
