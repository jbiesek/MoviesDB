package pl.jbiesek.MoviesDB.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jbiesek.MoviesDB.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
