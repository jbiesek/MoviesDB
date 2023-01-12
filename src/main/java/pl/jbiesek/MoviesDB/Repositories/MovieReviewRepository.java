package pl.jbiesek.MoviesDB.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jbiesek.MoviesDB.Entities.MovieReview;

@Repository
public interface MovieReviewRepository extends JpaRepository<MovieReview, Integer> {
}
