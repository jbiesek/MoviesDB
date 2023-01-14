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

    @PutMapping("/actor/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Actor updatedActor){
        Actor actor = actorRepository.getReferenceById(id);
        if (updatedActor.getName() != null) {
            actor.setName(updatedActor.getName());
        }
        if (updatedActor.getSurname() != null) {
            actor.setSurname(updatedActor.getSurname());
        }
        if (updatedActor.getBirth_date() != null) {
            actor.setBirth_date(updatedActor.getBirth_date());
        }
        if (updatedActor.getCountry() != null) {
            actor.setCountry(updatedActor.getCountry());
        }
        try {
            actorRepository.save(actor);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/actor/{id}")
    public ResponseEntity<Void> delete (@PathVariable("id") int id) {
        try {
            actorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
