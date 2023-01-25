package pl.jbiesek.MoviesDB.Services;

import pl.jbiesek.MoviesDB.Entities.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getById(int id);

    Boolean add(User user);

    Boolean update(int id, User updatedUser);

    Boolean delete(int id);

    User getUserByLogin(String login);

    List<String> getUserLoginByMovie(int id);

    List<String> getUserLoginByActor(int id);

    List<String> getUserLoginByDirector(int id);
}
