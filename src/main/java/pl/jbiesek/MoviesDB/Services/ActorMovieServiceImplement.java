package pl.jbiesek.MoviesDB.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jbiesek.MoviesDB.Entities.Actor;
import pl.jbiesek.MoviesDB.Entities.ActorMovie;
import pl.jbiesek.MoviesDB.Entities.Movie;
import pl.jbiesek.MoviesDB.Repositories.ActorMovieRepository;
import pl.jbiesek.MoviesDB.Repositories.ActorRepository;
import pl.jbiesek.MoviesDB.Repositories.MovieRepository;

import java.util.List;

@Service
public class ActorMovieServiceImplement implements ActorMovieService {

    @Autowired
    ActorMovieRepository actorMovieRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ActorRepository actorRepository;

    @Override
    public List<ActorMovie> getAll() {
        return actorMovieRepository.findAll();
    }

    @Override
    public ActorMovie getById(int id) {
        if (actorMovieRepository.findById(id).isPresent()) {
            return actorMovieRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public Boolean add(ActorMovie actorMovie) {
        actorMovieRepository.save(actorMovie);
        return true;
    }

    @Override
    public Boolean addWithMovieAndActor(ActorMovie actorMovie, int movieId, int actorId) {
        Movie movie;
        if (movieRepository.findById(movieId).isPresent()) {
            movie = movieRepository.findById(movieId).get();
        } else {
            return false;
        }
        actorMovie.setMovie(movie);
        Actor actor;
        if (actorRepository.findById(actorId).isPresent()) {
            actor = actorRepository.findById(actorId).get();
        } else {
            return false;
        }
        actorMovie.setActor(actor);
        actorMovieRepository.save(actorMovie);
        return true;
    }

    @Override
    public Boolean update(int id, ActorMovie updatedActorMovie) {
        ActorMovie actorMovie = actorMovieRepository.getReferenceById(id);
        if (updatedActorMovie.getRole() != null) {
            actorMovie.setRole(updatedActorMovie.getRole());
        }
        if (updatedActorMovie.getActor() != null) {
            actorMovie.setActor(updatedActorMovie.getActor());
        }
        if (updatedActorMovie.getMovie() != null) {
            actorMovie.setMovie(updatedActorMovie.getMovie());
        }
        try {
            actorMovieRepository.save(actorMovie);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public Boolean delete(int id) {
        try {
            actorMovieRepository.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String getRoleByActorIdMovieId(int id1, int id2) {
        return actorMovieRepository.getRoleByActorAndMovie(id1, id2);
    }
}
