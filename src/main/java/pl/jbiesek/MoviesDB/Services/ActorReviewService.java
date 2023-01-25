package pl.jbiesek.MoviesDB.Services;

import pl.jbiesek.MoviesDB.Entities.ActorReview;

import java.util.List;

public interface ActorReviewService {
    List<ActorReview> getAll();

    ActorReview getById(int id);

    Boolean add(ActorReview actorReview);

    Boolean addWithActor(ActorReview actorReview, int actorId, int userId);

    Boolean update(int id, ActorReview updatedActorReview);

    Boolean delete(int id);

    List<ActorReview> getActorReviewByActorId(int id);

    List<ActorReview> getActorReviewByUserId(int id);

    float getActorRating(int id);

}
