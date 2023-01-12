package pl.jbiesek.MoviesDB.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jbiesek.MoviesDB.Entities.Actor;
import pl.jbiesek.MoviesDB.Repositories.ActorRepository;

import java.util.List;

@RestController
public class ActorController {

    @Autowired
    ActorRepository actorRepository;

    @GetMapping("/actors")
    public List<Actor> getAll() {
        return actorRepository.findAll();
    }

    @GetMapping("actor/{id}")
    public Actor getById(@PathVariable("id") int id) {
        return actorRepository.getReferenceById(id);
    }

    @PostMapping("/actor")
    public ResponseEntity<Void> add(@RequestBody Actor actor) {
        actorRepository.save(actor);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
