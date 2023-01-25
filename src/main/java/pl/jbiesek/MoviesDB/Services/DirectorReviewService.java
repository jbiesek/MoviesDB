package pl.jbiesek.MoviesDB.Services;

import pl.jbiesek.MoviesDB.Entities.DirectorReview;

import java.util.List;

public interface DirectorReviewService {

    List<DirectorReview> getAll();

    DirectorReview getById(int id);

    Boolean add(DirectorReview directorReview);

    Boolean addWithDirector(DirectorReview directorReview, int directorId, int userId);

    Boolean update(int id, DirectorReview updatedDirectorReview);

    Boolean delete(int id);

    List<DirectorReview> getReviewsByDirector(int id);

    List<DirectorReview> getReviewsByUser(int id);

    float getDirectorRating(int id);
}
