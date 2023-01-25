package pl.jbiesek.MoviesDB.Services;

import pl.jbiesek.MoviesDB.Entities.Movie;

import java.io.IOException;
import java.util.List;

public interface MovieService {

    List<Movie> getAll();

    Movie getById(int id);

    Boolean add(Movie movie);

    Boolean addWithDirector(Movie movie, int id);

    Boolean update(int id, Movie updatedMovie);

    Boolean delete(int id);

    List<Movie> getMoviesByActor(int id);

    List<Object> getMoviesByActorWithRole(int id);

    List<Movie> getMoviesByDirector(int id);

    List<Movie> getMoviesByUser(int id);

    byte[] getImage(int id) throws IOException;

}
