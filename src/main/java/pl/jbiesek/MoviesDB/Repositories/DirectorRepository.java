package pl.jbiesek.MoviesDB.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jbiesek.MoviesDB.Entities.Director;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {
}
