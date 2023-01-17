package pl.jbiesek.MoviesDB.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.jbiesek.MoviesDB.Entities.MovieReview;

import java.util.List;

@Repository
public interface MovieReviewRepository extends JpaRepository<MovieReview, Integer> {

    @Query(value = "select * from movie_review where movie_id = :id", nativeQuery = true)
    List<MovieReview> getReviewsByMovie(int id);

    @Query(value = "select * from movie_review where user_id = :id", nativeQuery = true)
    List<MovieReview> getReviewsByUser(int id);

    @Query(value = "select AVG(rating) from movie_review where movie_id = :id", nativeQuery = true)
    float getMovieRating(int id);

}
