package pl.jbiesek.MoviesDB.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.jbiesek.MoviesDB.Entities.Director;

import java.util.List;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {

    @Query(value = "select  d.id, d.name, d.surname, d.birth_date, d.country from director d join director_review dr on d.id = dr.director_id where dr.id = :id", nativeQuery = true)
    Director getDirectorByReview(int id);

    @Query(value = "select  d.id, d.name, d.surname, d.birth_date, d.country from director d join director_review dr on d.id = dr.director_id where dr.user_id = :id", nativeQuery = true)
    List<Director> getDirectorsByUser(int id);

    @Query(value = "select d.id, d.name, d.surname, d.birth_date, d.country from director d join movie m on d.id = m.director_id where m.id = :id", nativeQuery = true)
    Director getDirectorByMovie(int id);
}
