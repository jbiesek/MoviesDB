package pl.jbiesek.MoviesDB.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jbiesek.MoviesDB.Entities.User;
import pl.jbiesek.MoviesDB.Repositories.UserRepository;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getById(@PathVariable("id") int id) {
        return userRepository.getReferenceById(id);
    }

    @PostMapping("/user")
    public ResponseEntity<Void> add (@RequestBody User user){
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
