package pl.jbiesek.MoviesDB.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jbiesek.MoviesDB.Entities.ActorReview;

@Repository
public interface ActorReviewRepository extends JpaRepository<ActorReview, Integer> {
}