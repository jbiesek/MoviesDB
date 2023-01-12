package pl.jbiesek.MoviesDB.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jbiesek.MoviesDB.Entities.Movie;
import pl.jbiesek.MoviesDB.Repositories.MovieRepository;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/movies")
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @GetMapping("/movie/{id}")
    public Movie getById(@PathVariable("id") int id) {
        return movieRepository.getReferenceById(id);
    }

    @PostMapping("/movie")
    public ResponseEntity<Void> add(@RequestBody Movie movie){
        movieRepository.save(movie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
