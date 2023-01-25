package pl.jbiesek.MoviesDB.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jbiesek.MoviesDB.Entities.Director;
import pl.jbiesek.MoviesDB.Services.DirectorService;

import java.io.IOException;
import java.util.List;

@RestController
public class DirectorController {

    @Autowired
    DirectorService directorService;

    @GetMapping("/directors")
    public List<Director> getAll() {
        return directorService.getAll();
    }

    @GetMapping("/director/{id}")
    public Director getById(@PathVariable("id") int id) {
        return directorService.getById(id);
    }

    @PostMapping("/director")
    public ResponseEntity<Void> add(@RequestBody Director director) {
        directorService.add(director);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/director/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Director updatedDirector) {
        if (directorService.update(id, updatedDirector)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/director/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        if (directorService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/director/byReview/{id}")
    public Director getDirectorById(@PathVariable("id") int id) {
        return directorService.getDirectorById(id);
    }

    @GetMapping("/directors/byUser/{id}")
    public List<Director> getDirectorsByUser(@PathVariable("id") int id) {
        return directorService.getDirectorsByUser(id);
    }

    @GetMapping("/director/byMovie/{id}")
    public Director getDirectorByMovie(@PathVariable("id") int id) {
        return directorService.getDirectorByMovie(id);
    }

    @RequestMapping(value = "/director/image/{id}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("id") int id) throws IOException {
        byte[] bytes = directorService.getImage(id);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }

}
