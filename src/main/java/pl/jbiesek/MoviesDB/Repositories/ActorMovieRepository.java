package pl.jbiesek.MoviesDB.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jbiesek.MoviesDB.Entities.ActorMovie;

@Repository
public interface ActorMovieRepository extends JpaRepository<ActorMovie, Integer> {
}
