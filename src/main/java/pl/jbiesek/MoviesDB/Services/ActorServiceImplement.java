package pl.jbiesek.MoviesDB.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import pl.jbiesek.MoviesDB.Entities.Actor;
import pl.jbiesek.MoviesDB.Repositories.ActorRepository;

import java.io.IOException;
import java.util.List;

@Service
public class ActorServiceImplement implements ActorService {
    @Autowired
    ActorRepository actorRepository;

    @Override
    public List<Actor> getAll() {
        return actorRepository.findAll();
    }

    @Override
    public Actor getById(int id) {
        if (actorRepository.findById(id).isPresent()) {
            return actorRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public Boolean add(Actor actor) {
        actorRepository.save(actor);
        return true;
    }

    @Override
    public Boolean update(int id, Actor updatedActor) {
        Actor actor = actorRepository.getReferenceById(id);
        if (updatedActor.getName() != null) {
            actor.setName(updatedActor.getName());
        }
        if (updatedActor.getSurname() != null) {
            actor.setSurname(updatedActor.getSurname());
        }
        if (updatedActor.getBirth_date() != null) {
            actor.setBirth_date(updatedActor.getBirth_date());
        }
        if (updatedActor.getCountry() != null) {
            actor.setCountry(updatedActor.getCountry());
        }
        try {
            actorRepository.save(actor);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public Boolean delete(int id) {
        try {
            actorRepository.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public Actor getActorByReview(int id) {
        return actorRepository.getActorByReview(id);
    }

    @Override
    public List<Actor> getActorsByUser(int id) {
        return actorRepository.getActorsByUser(id);
    }

    @Override
    public List<Actor> getActorsByMovie(int id) {
        return actorRepository.getActorsByMovie(id);
    }

    @Override
    public List<Object> getActorsByMovieWithRole(int id) {
        return actorRepository.getActorsByMovieWithRole(id);
    }

    @Override
    public byte[] getImage(int id) throws IOException {
        String imgPath = "Images/Actor/" + id + ".jpg";
        var imgFile = new ClassPathResource(imgPath);
        return StreamUtils.copyToByteArray(imgFile.getInputStream());
    }
}
