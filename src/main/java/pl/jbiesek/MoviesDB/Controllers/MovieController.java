package pl.jbiesek.MoviesDB.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jbiesek.MoviesDB.Entities.Movie;
import pl.jbiesek.MoviesDB.Services.MovieService;

import java.io.IOException;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/movies")
    public List<Movie> getAll() {
        return movieService.getAll();
    }

    @GetMapping("/movie/{id}")
    public Movie getById(@PathVariable("id") int id) {
        return movieService.getById(id);
    }

    @PostMapping("/movie")
    public ResponseEntity<Void> add(@RequestBody Movie movie) {
        movieService.add(movie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/movie/{directorId}")
    public ResponseEntity<Void> addWithDirector(@RequestBody Movie movie, @PathVariable("directorId") int id) {
        if (movieService.addWithDirector(movie, id)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/movie/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Movie updatedMovie) {
        if (movieService.update(id, updatedMovie)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        if (movieService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/movies/byActor/{id}")
    public List<Movie> getMoviesByActor(@PathVariable("id") int id) {
        return movieService.getMoviesByActor(id);
    }

    @GetMapping("/movies/byActorWithRole/{id}")
    public List<Object> getMoviesByActorWithRole(@PathVariable("id") int id) {
        return movieService.getMoviesByActorWithRole(id);
    }

    @GetMapping("/movies/byDirector/{id}")
    public List<Movie> getMoviesByDirector(@PathVariable("id") int id) {
        return movieService.getMoviesByDirector(id);
    }

    @GetMapping("/movies/byUser/{id}")
    public List<Movie> getMoviesByUser(@PathVariable("id") int id) {
        return movieService.getMoviesByUser(id);
    }

    @RequestMapping(value = "/movie/image/{id}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("id") int id) throws IOException {
        byte[] bytes = movieService.getImage(id);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
}
