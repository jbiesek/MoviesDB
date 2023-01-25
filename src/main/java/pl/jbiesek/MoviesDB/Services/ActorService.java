package pl.jbiesek.MoviesDB.Services;

import pl.jbiesek.MoviesDB.Entities.Actor;

import java.io.IOException;
import java.util.List;

public interface ActorService {
    List<Actor> getAll();

    Actor getById(int id);

    Boolean add(Actor actor);

    Boolean update(int id, Actor updatedActor);

    Boolean delete(int id);

    Actor getActorByReview(int id);

    List<Actor> getActorsByUser(int id);

    List<Actor> getActorsByMovie(int id);

    List<Object> getActorsByMovieWithRole(int id);

    byte[] getImage(int id) throws IOException;
}
