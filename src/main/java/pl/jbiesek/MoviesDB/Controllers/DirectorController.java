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
        return directorRepository.getReferenceById(id);
    }

    @PostMapping("/director")
    public ResponseEntity<Void> add(@RequestBody Director director){
        directorRepository.save(director);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
