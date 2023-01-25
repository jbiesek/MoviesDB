package pl.jbiesek.MoviesDB.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jbiesek.MoviesDB.Entities.Movie;
import pl.jbiesek.MoviesDB.Entities.MovieReview;
import pl.jbiesek.MoviesDB.Entities.User;
import pl.jbiesek.MoviesDB.Repositories.MovieRepository;
import pl.jbiesek.MoviesDB.Repositories.MovieReviewRepository;
import pl.jbiesek.MoviesDB.Repositories.UserRepository;

import java.util.List;

@Service
public class MovieReviewServiceImplement implements MovieReviewService {

    @Autowired
    MovieReviewRepository movieReviewRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<MovieReview> getAll() {
        return movieReviewRepository.findAll();
    }

    @Override
    public MovieReview getById(int id) {
        if (movieReviewRepository.findById(id).isPresent()) {
            return movieReviewRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public Boolean add(MovieReview movieReview) {
        movieReviewRepository.save(movieReview);
        return true;
    }

    @Override
    public Boolean addWithMovie(MovieReview movieReview, int movieId, int userId) {
        Movie movie;
        if (movieRepository.findById(movieId).isPresent()) {
            movie = movieRepository.findById(movieId).get();
        } else {
            return false;
        }
        movieReview.setMovie(movie);
        User user;
        if (userRepository.findById(userId).isPresent()) {
            user = userRepository.findById(userId).get();
        } else {
            return false;
        }
        movieReview.setUser(user);
        movieReviewRepository.save(movieReview);
        return true;
    }

    @Override
    public Boolean update(int id, MovieReview updatedMovieReview) {
        MovieReview movieReview = movieReviewRepository.getReferenceById(id);
        if (updatedMovieReview.getTitle() != null) {
            movieReview.setTitle(updatedMovieReview.getTitle());
        }
        if (updatedMovieReview.getDate_added() != null) {
            movieReview.setDate_added(updatedMovieReview.getDate_added());
        }
        if (updatedMovieReview.getDescription() != null) {
            movieReview.setDescription(updatedMovieReview.getDescription());
        }
        if (updatedMovieReview.getRating() >= 0 && updatedMovieReview.getRating() <= 10) {
            movieReview.setRating(updatedMovieReview.getRating());
        }
        if (updatedMovieReview.getMovie() != null) {
            movieReview.setMovie(updatedMovieReview.getMovie());
        }
        if (updatedMovieReview.getUser() != null) {
            movieReview.setUser(updatedMovieReview.getUser());
        }
        try {
            movieReviewRepository.save(movieReview);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public Boolean delete(int id) {
        try {
            movieReviewRepository.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public List<MovieReview> getReviewsByMovie(int id) {
        return movieReviewRepository.getReviewsByMovie(id);
    }

    @Override
    public List<MovieReview> getReviewsByUser(int id) {
        return movieReviewRepository.getReviewsByUser(id);
    }

    @Override
    public float getMovieRating(int id) {
        return movieReviewRepository.getMovieRating(id);
    }
}
