package pl.jbiesek.MoviesDB.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jbiesek.MoviesDB.Entities.Director;
import pl.jbiesek.MoviesDB.Repositories.DirectorRepository;

import java.util.List;

@RestController
public class DirectorController {

    @Autowired
    DirectorRepository directorRepository;

    @GetMapping("/directors")
    public List<Director> getAll(){
        return directorRepository.findAll();
    }

    @GetMapping("/director/{id}")
    public Director getById(@PathVariable("id") int id){
        if (directorRepository.findById(id).isPresent()) {
            return directorRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @PostMapping("/director")
    public ResponseEntity<Void> add(@RequestBody Director director){
        directorRepository.save(director);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/director/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Director updatedDirector) {
        Director director = directorRepository.getReferenceById(id);
        if (updatedDirector.getName() != null) {
            director.setName(updatedDirector.getName());
        }
        if (updatedDirector.getSurname() != null) {
            director.setSurname(updatedDirector.getSurname());
        }
        if (updatedDirector.getCountry() != null) {
            director.setCountry(updatedDirector.getCountry());
        }
        if (updatedDirector.getBirth_date() != null) {
            director.setBirth_date(updatedDirector.getBirth_date());
        }
        try {
            directorRepository.save(director);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/director/{id}")
    public ResponseEntity<Void> delete (@PathVariable("id") int id) {
        try {
            directorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/director/byReview/{id}")
    public Director getDirectorById(@PathVariable("id") int id) {
        return directorRepository.getDirectorByReview(id);
    }

    @GetMapping("/directors/byUser/{id}")
    public List<Director> getDirectorsByUser(@PathVariable("id") int id) {
        return directorRepository.getDirectorsByUser(id);
    }

    @GetMapping("/director/byMovie/{id}")
    public Director getDirectorByMovie(@PathVariable("id") int id) {
        return directorRepository.getDirectorByMovie(id);
    }

}
