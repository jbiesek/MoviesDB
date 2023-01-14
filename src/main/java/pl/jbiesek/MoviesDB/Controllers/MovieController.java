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

    @PutMapping("/movie/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Movie updatedMovie) {
        Movie movie = movieRepository.getReferenceById(id);
        if (updatedMovie.getTitle() != null) {
            movie.setTitle(updatedMovie.getTitle());
        }
        if (updatedMovie.getCountry() != null) {
            movie.setCountry(updatedMovie.getCountry());
        }
        if (updatedMovie.getDescription() != null) {
            movie.setDescription(updatedMovie.getDescription());
        }
        if (updatedMovie.getYear() >= 1900 && updatedMovie.getYear() <= 2030) {
            movie.setYear(updatedMovie.getYear());
        }
        if (updatedMovie.getDuration()>0 && updatedMovie.getDuration() < 500) {
            movie.setDuration(updatedMovie.getDuration());
        }
        if (updatedMovie.getDirector() != null){
            movie.setDirector(updatedMovie.getDirector());
        }
        try {
            movieRepository.save(movie);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        try {
            movieRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
