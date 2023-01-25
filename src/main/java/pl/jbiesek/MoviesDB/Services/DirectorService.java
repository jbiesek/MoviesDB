package pl.jbiesek.MoviesDB.Services;

import pl.jbiesek.MoviesDB.Entities.Director;

import java.io.IOException;
import java.util.List;

public interface DirectorService {

    List<Director> getAll();

    Director getById(int id);

    Boolean add(Director director);

    Boolean update(int id, Director updatedDirector);

    Boolean delete(int id);

    Director getDirectorById(int id);

    List<Director> getDirectorsByUser(int id);

    Director getDirectorByMovie(int id);

    byte[] getImage(int id) throws IOException;
}
