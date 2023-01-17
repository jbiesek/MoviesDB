package pl.jbiesek.MoviesDB.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.jbiesek.MoviesDB.Entities.ActorMovie;

@Repository
public interface ActorMovieRepository extends JpaRepository<ActorMovie, Integer> {
    @Query(value = "select role from actor_movie where actor_id = 1 and movie_id = 1", nativeQuery = true)
    String getRoleByActorAndMovie (int id1, int id2);
}

