package pl.jbiesek.MoviesDB.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.jbiesek.MoviesDB.Entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from user where login=:login", nativeQuery = true)
    public User getUserByLogin(String login);

    @Query(value = "select u.login from user u join movie_review m on m.user_id = u.id where m.movie_id = :id", nativeQuery = true)
    public List<String> getUserLoginByMovie(int id);

    @Query(value = "select u.login from user u join actor_review a on a.user_id = u.id where a.actor_id = :id", nativeQuery = true)
    public List<String> getUserLoginByActor(int id);

    @Query(value = "select u.login from user u join director_review d on u.id = d.user_id where d.director_id = :id", nativeQuery = true)
    public List<String> getUserLoginByDirector(int id);
}
