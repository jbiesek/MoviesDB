package pl.jbiesek.MoviesDB.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.jbiesek.MoviesDB.Entities.ActorReview;

import java.util.List;

@Repository
public interface ActorReviewRepository extends JpaRepository<ActorReview, Integer> {

    @Query(value = "SELECT * FROM actor_review WHERE actor_id = :id", nativeQuery = true)
    List<ActorReview> getReviewByActorId(int id);

    @Query(value = "SELECT * FROM actor_review WHERE user_id = :id", nativeQuery = true)
    List<ActorReview> getReviewByUserId(int id);

    @Query(value = "select AVG(rating) from actor_review where actor_id = :id", nativeQuery = true)
    float getActorRating(int id);

}
