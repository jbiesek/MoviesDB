package pl.jbiesek.MoviesDB.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.jbiesek.MoviesDB.Entities.Actor;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

    @Query(value = "select  a.id, a.name, a.surname, a.birth_date, a.country from actor a join actor_review ar on a.id = ar.actor_id where ar.id = :id", nativeQuery = true)
    Actor getActorByReview(int id);

    @Query(value = "select  a.id, a.name, a.surname, a.birth_date, a.country from actor a join actor_review ar on a.id = ar.actor_id where ar.user_id = :id", nativeQuery = true)
    List<Actor> getActorsByUser(int id);

    @Query(value = "select a.id, a.name, a.surname, a.birth_date, a.country from actor_movie am join actor a on am.actor_id = a.id where am.movie_id = :id", nativeQuery = true)
    List<Actor> getActorsByMovie(int id);

    @Query(value = "select a.id, a.name, a.surname, a.birth_date, a.country, am.role from actor_movie am join actor a on am.actor_id = a.id where am.movie_id = :id", nativeQuery = true)
    List<Object> getActorsByMovieWithRole(int id);

}
