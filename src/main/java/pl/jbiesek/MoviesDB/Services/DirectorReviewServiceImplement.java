package pl.jbiesek.MoviesDB.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jbiesek.MoviesDB.Entities.Director;
import pl.jbiesek.MoviesDB.Entities.DirectorReview;
import pl.jbiesek.MoviesDB.Entities.User;
import pl.jbiesek.MoviesDB.Repositories.DirectorRepository;
import pl.jbiesek.MoviesDB.Repositories.DirectorReviewRepository;
import pl.jbiesek.MoviesDB.Repositories.UserRepository;

import java.util.List;

@Service
public class DirectorReviewServiceImplement implements DirectorReviewService {
    @Autowired
    DirectorReviewRepository directorReviewRepository;

    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<DirectorReview> getAll() {
        return directorReviewRepository.findAll();
    }

    @Override
    public DirectorReview getById(int id) {
        if (directorReviewRepository.findById(id).isPresent()) {
            return directorReviewRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public Boolean add(DirectorReview directorReview) {
        directorReviewRepository.save(directorReview);
        return true;
    }

    @Override
    public Boolean addWithDirector(DirectorReview directorReview, int directorId, int userId) {
        Director director;
        if (directorRepository.findById(directorId).isPresent()) {
            director = directorRepository.findById(directorId).get();
        } else {
            return false;
        }
        directorReview.setDirector(director);
        User user;
        if (userRepository.findById(userId).isPresent()) {
            user = userRepository.findById(userId).get();
        } else {
            return false;
        }
        directorReview.setUser(user);
        directorReviewRepository.save(directorReview);
        return true;
    }

    @Override
    public Boolean update(int id, DirectorReview updatedDirectorReview) {
        DirectorReview directorReview = directorReviewRepository.getReferenceById(id);
        if (updatedDirectorReview.getDate_added() != null) {
            directorReview.setDate_added(updatedDirectorReview.getDate_added());
        }
        if (updatedDirectorReview.getTitle() != null) {
            directorReview.setTitle(updatedDirectorReview.getTitle());
        }
        if (updatedDirectorReview.getDescription() != null) {
            directorReview.setDescription(updatedDirectorReview.getDescription());
        }
        if (updatedDirectorReview.getRating() >= 0 && updatedDirectorReview.getRating() <= 10) {
            directorReview.setRating(updatedDirectorReview.getRating());
        }
        if (updatedDirectorReview.getDirector() != null) {
            directorReview.setDirector(updatedDirectorReview.getDirector());
        }
        if (updatedDirectorReview.getUser() != null) {
            directorReview.setUser(updatedDirectorReview.getUser());
        }
        try {
            directorReviewRepository.save(directorReview);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public Boolean delete(int id) {
        try {
            directorReviewRepository.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public List<DirectorReview> getReviewsByDirector(int id) {
        return directorReviewRepository.getReviewByDirector(id);
    }

    @Override
    public List<DirectorReview> getReviewsByUser(int id) {
        return directorReviewRepository.getReviewByUser(id);
    }

    @Override
    public float getDirectorRating(int id) {
        return directorReviewRepository.getDirectorRating(id);
    }
}
