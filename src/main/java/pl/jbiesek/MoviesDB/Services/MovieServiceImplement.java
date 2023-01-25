package pl.jbiesek.MoviesDB.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import pl.jbiesek.MoviesDB.Entities.Director;
import pl.jbiesek.MoviesDB.Entities.Movie;
import pl.jbiesek.MoviesDB.Repositories.DirectorRepository;
import pl.jbiesek.MoviesDB.Repositories.MovieRepository;

import java.io.IOException;
import java.util.List;

@Service
public class MovieServiceImplement implements MovieService {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    DirectorRepository directorRepository;

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getById(int id) {
        if (movieRepository.findById(id).isPresent()) {
            return movieRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public Boolean add(Movie movie) {
        movieRepository.save(movie);
        return true;
    }

    @Override
    public Boolean addWithDirector(Movie movie, int id) {
        Director director;
        if (directorRepository.findById(id).isPresent()) {
            director = directorRepository.findById(id).get();
        } else {
            return false;
        }
        movie.setDirector(director);
        movieRepository.save(movie);
        return true;
    }

    @Override
    public Boolean update(int id, Movie updatedMovie) {
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
        if (updatedMovie.getDuration() > 0 && updatedMovie.getDuration() < 500) {
            movie.setDuration(updatedMovie.getDuration());
        }
        if (updatedMovie.getDirector() != null) {
            movie.setDirector(updatedMovie.getDirector());
        }
        try {
            movieRepository.save(movie);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public Boolean delete(int id) {
        try {
            movieRepository.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public List<Movie> getMoviesByActor(int id) {
        return movieRepository.getMoviesByActor(id);
    }

    @Override
    public List<Object> getMoviesByActorWithRole(int id) {
        return movieRepository.getMoviesByActorWithRole(id);
    }

    @Override
    public List<Movie> getMoviesByDirector(int id) {
        return movieRepository.getMoviesByDirector(id);
    }

    @Override
    public List<Movie> getMoviesByUser(int id) {
        return movieRepository.getMoviesByUser(id);
    }

    @Override
    public byte[] getImage(int id) throws IOException {
        String imgPath = "Images/Movie/" + id + ".jpg";
        var imgFile = new ClassPathResource(imgPath);
        return StreamUtils.copyToByteArray(imgFile.getInputStream());
    }
}
