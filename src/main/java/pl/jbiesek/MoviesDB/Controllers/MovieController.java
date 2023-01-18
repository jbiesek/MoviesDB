package pl.jbiesek.MoviesDB.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import pl.jbiesek.MoviesDB.Entities.Director;
import pl.jbiesek.MoviesDB.Entities.Movie;
import pl.jbiesek.MoviesDB.Repositories.DirectorRepository;
import pl.jbiesek.MoviesDB.Repositories.MovieRepository;

import java.io.IOException;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    DirectorRepository directorRepository;

    @GetMapping("/movies")
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @GetMapping("/movie/{id}")
    public Movie getById(@PathVariable("id") int id) {
        if (movieRepository.findById(id).isPresent()) {
            return movieRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @PostMapping("/movie")
    public ResponseEntity<Void> add(@RequestBody Movie movie){
        movieRepository.save(movie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/movie/{directorId}")
    public ResponseEntity<Void> addWithDirector(@RequestBody Movie movie, @PathVariable("directorId") int id){
        Director director;
        if (directorRepository.findById(id).isPresent()) {
            director = directorRepository.findById(id).get();
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        movie.setDirector(director);
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

    @GetMapping("/movies/byActor/{id}")
    public List<Movie> getMoviesByActor(@PathVariable("id") int id) {
        return movieRepository.getMoviesByActor(id);
    }

    @GetMapping("/movies/byActorWithRole/{id}")
    public List<Object> getMoviesByActorWithRole(@PathVariable("id") int id) {
        return movieRepository.getMoviesByActorWithRole(id);
    }

    @GetMapping("/movies/byDirector/{id}")
    public List<Movie> getMoviesByDirector(@PathVariable("id") int id) {
        return movieRepository.getMoviesByDirector(id);
    }

    @GetMapping("/movies/byUser/{id}")
    public List<Movie> getMoviesByUser(@PathVariable("id") int id) {
        return movieRepository.getMoviesByUser(id);
    }

    @RequestMapping(value = "/movie/image/{id}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("id") int id) throws IOException {
        String imgPath = "Images/Movie/" + id + ".jpg";
        var imgFile = new ClassPathResource(imgPath);
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
}
