package pl.jbiesek.MoviesDB.Services;

import pl.jbiesek.MoviesDB.Entities.ActorMovie;

import java.util.List;

public interface ActorMovieService {
    List<ActorMovie> getAll();

    ActorMovie getById(int id);

    Boolean add(ActorMovie actorMovie);

    Boolean addWithMovieAndActor(ActorMovie actorMovie, int movieId, int actorId);

    Boolean update(int id, ActorMovie updatedActorMovie);

    Boolean delete(int id);

    String getRoleByActorIdMovieId(int id1, int id2);
}
