package pl.jbiesek.MoviesDB.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import pl.jbiesek.MoviesDB.Entities.Director;
import pl.jbiesek.MoviesDB.Repositories.DirectorRepository;

import java.io.IOException;
import java.util.List;

@Service
public class DirectorServiceImplement implements DirectorService {

    @Autowired
    DirectorRepository directorRepository;

    @Override
    public List<Director> getAll() {
        return directorRepository.findAll();
    }

    @Override
    public Director getById(int id) {
        if (directorRepository.findById(id).isPresent()) {
            return directorRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public Boolean add(Director director) {
        directorRepository.save(director);
        return true;
    }

    @Override
    public Boolean update(int id, Director updatedDirector) {
        Director director = directorRepository.getReferenceById(id);
        if (updatedDirector.getName() != null) {
            director.setName(updatedDirector.getName());
        }
        if (updatedDirector.getSurname() != null) {
            director.setSurname(updatedDirector.getSurname());
        }
        if (updatedDirector.getCountry() != null) {
            director.setCountry(updatedDirector.getCountry());
        }
        if (updatedDirector.getBirth_date() != null) {
            director.setBirth_date(updatedDirector.getBirth_date());
        }
        try {
            directorRepository.save(director);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public Boolean delete(int id) {
        try {
            directorRepository.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public Director getDirectorById(int id) {
        return directorRepository.getDirectorByReview(id);
    }

    @Override
    public List<Director> getDirectorsByUser(int id) {
        return directorRepository.getDirectorsByUser(id);
    }

    @Override
    public Director getDirectorByMovie(int id) {
        return directorRepository.getDirectorByMovie(id);
    }

    @Override
    public byte[] getImage(int id) throws IOException {
        String imgPath = "Images/Director/" + id + ".jpg";
        var imgFile = new ClassPathResource(imgPath);
        return StreamUtils.copyToByteArray(imgFile.getInputStream());
    }
}
