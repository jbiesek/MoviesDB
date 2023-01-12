package pl.jbiesek.MoviesDB.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jbiesek.MoviesDB.Entities.DirectorReview;

@Repository
public interface DirectorReviewRepository extends JpaRepository<DirectorReview, Integer> {
}
