package pl.jbiesek.MoviesDB.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.jbiesek.MoviesDB.Entities.DirectorReview;

import java.util.List;

@Repository
public interface DirectorReviewRepository extends JpaRepository<DirectorReview, Integer> {

    @Query(value = "select * from director_review where director_id = :id", nativeQuery = true)
    List<DirectorReview> getReviewByDirector(int id);

    @Query(value = "select * from director_review where user_id = :id", nativeQuery = true)
    List<DirectorReview> getReviewByUser(int id);

    @Query(value = "select AVG(rating) from director_review where director_id = :id", nativeQuery = true)
    float getDirectorRating(int id);
}
