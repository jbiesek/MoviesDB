package pl.jbiesek.MoviesDB.Services;

import pl.jbiesek.MoviesDB.Entities.MovieReview;

import java.util.List;

public interface MovieReviewService {

    List<MovieReview> getAll();

    MovieReview getById(int id);

    Boolean add(MovieReview movieReview);

    Boolean addWithMovie(MovieReview movieReview, int movieId, int userId);

    Boolean update(int id, MovieReview updatedMovieReview);

    Boolean delete(int id);

    List<MovieReview> getReviewsByMovie(int id);

    List<MovieReview> getReviewsByUser(int id);

    float getMovieRating(int id);
}
