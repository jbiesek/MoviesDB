package pl.jbiesek.MoviesDB.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jbiesek.MoviesDB.Entities.User;
import pl.jbiesek.MoviesDB.Repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(int id) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public Boolean add(User user) {
        userRepository.save(user);
        return true;
    }

    @Override
    public Boolean update(int id, User updatedUser) {
        User user = userRepository.getReferenceById(id);
        if (updatedUser.getLogin() != null) {
            user.setLogin(updatedUser.getLogin());
        }
        if (updatedUser.getPassword() != null) {
            user.setPassword(updatedUser.getPassword());
        }
        if (updatedUser.getDate_joined() != null) {
            user.setDate_joined(updatedUser.getDate_joined());
        }
        try {
            userRepository.save(user);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public Boolean delete(int id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }

    @Override
    public List<String> getUserLoginByMovie(int id) {
        return userRepository.getUserLoginByMovie(id);
    }

    @Override
    public List<String> getUserLoginByActor(int id) {
        return userRepository.getUserLoginByActor(id);
    }

    @Override
    public List<String> getUserLoginByDirector(int id) {
        return userRepository.getUserLoginByDirector(id);
    }
}
