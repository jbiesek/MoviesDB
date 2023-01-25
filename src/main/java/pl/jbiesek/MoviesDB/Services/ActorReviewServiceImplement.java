package pl.jbiesek.MoviesDB.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jbiesek.MoviesDB.Entities.Actor;
import pl.jbiesek.MoviesDB.Entities.ActorReview;
import pl.jbiesek.MoviesDB.Entities.User;
import pl.jbiesek.MoviesDB.Repositories.ActorRepository;
import pl.jbiesek.MoviesDB.Repositories.ActorReviewRepository;
import pl.jbiesek.MoviesDB.Repositories.UserRepository;

import java.util.List;

@Service
public class ActorReviewServiceImplement implements ActorReviewService {
    @Autowired
    ActorReviewRepository actorReviewRepository;

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<ActorReview> getAll() {
        return actorReviewRepository.findAll();
    }

    @Override
    public ActorReview getById(int id) {
        if (actorReviewRepository.findById(id).isPresent()) {
            return actorReviewRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public Boolean add(ActorReview actorReview) {
        actorReviewRepository.save(actorReview);
        return true;
    }

    @Override
    public Boolean addWithActor(ActorReview actorReview, int actorId, int userId) {
        Actor actor;
        if (actorRepository.findById(actorId).isPresent()) {
            actor = actorRepository.findById(actorId).get();
        } else {
            return false;
        }
        actorReview.setActor(actor);
        User user;
        if (userRepository.findById(userId).isPresent()) {
            user = userRepository.findById(userId).get();
        } else {
            return false;
        }
        actorReview.setUser(user);
        actorReviewRepository.save(actorReview);
        return true;
    }

    @Override
    public Boolean update(int id, ActorReview updatedActorReview) {
        ActorReview actorReview = actorReviewRepository.getReferenceById(id);
        if (updatedActorReview.getDate_added() != null) {
            actorReview.setDate_added(updatedActorReview.getDate_added());
        }
        if (updatedActorReview.getTitle() != null) {
            actorReview.setTitle(updatedActorReview.getTitle());
        }
        if (updatedActorReview.getDescription() != null) {
            actorReview.setDescription(updatedActorReview.getDescription());
        }
        if (updatedActorReview.getRating() >= 0 && updatedActorReview.getRating() <= 10) {
            actorReview.setRating(updatedActorReview.getRating());
        }
        if (updatedActorReview.getActor() != null) {
            actorReview.setActor(updatedActorReview.getActor());
        }
        if (updatedActorReview.getUser() != null) {
            actorReview.setUser(updatedActorReview.getUser());
        }
        try {
            actorReviewRepository.save(actorReview);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public Boolean delete(int id) {
        try {
            actorReviewRepository.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public List<ActorReview> getActorReviewByActorId(int id) {
        return actorReviewRepository.getReviewByActorId(id);
    }

    @Override
    public List<ActorReview> getActorReviewByUserId(int id) {
        return actorReviewRepository.getReviewByUserId(id);
    }

    @Override
    public float getActorRating(int id) {
        return actorReviewRepository.getActorRating(id);
    }
}
