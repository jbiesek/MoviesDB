package pl.jbiesek.MoviesDB.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.jbiesek.MoviesDB.Entities.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query(value = "select m.id, m.title, m.year, m.duration, m.description, m.country, m.director_id from movie m join actor_movie am on m.id = am.movie_id where actor_id = :id", nativeQuery = true)
    List<Movie> getMoviesByActor(int id);

    @Query(value = "select m.id, m.title, m.year, m.duration, m.description, m.country, m.director_id, am.role from movie m join actor_movie am on m.id = am.movie_id where am.actor_id = :id", nativeQuery = true)
    List<Object> getMoviesByActorWithRole(int id);

    @Query(value = "select m.id, m.title, m.country, m.year, m.description, m.duration, m.director_id from movie m join director d on m.director_id = d.id where d.id = :id;", nativeQuery = true)
    List<Movie> getMoviesByDirector(int id);

    @Query(value = "select m.id, m.title, m.country, m.year, m.description, m.duration, m.director_id from movie_review join movie m on m.id = movie_review.movie_id where user_id = :id", nativeQuery = true)
    List<Movie> getMoviesByUser(int id);
}
