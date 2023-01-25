package pl.jbiesek.MoviesDB.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jbiesek.MoviesDB.Entities.User;
import pl.jbiesek.MoviesDB.Services.UserService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/user/{id}")
    public User getById(@PathVariable("id") int id) {
        return userService.getById(id);
    }

    @PostMapping("/user")
    public ResponseEntity<Void> add(@RequestBody User user) {
        userService.add(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody User updatedUser) {
        if (userService.update(id, updatedUser)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        if (userService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/user/byLogin")
    public User getUserByLogin(@RequestParam String login) {
        return userService.getUserByLogin(login);
    }

    @GetMapping("/user/byMovie/{id}")
    public List<String> getUserLoginByMovie(@PathVariable("id") int id) {
        return userService.getUserLoginByMovie(id);
    }

    @GetMapping("/user/byActor/{id}")
    public List<String> getUserLoginByActor(@PathVariable("id") int id) {
        return userService.getUserLoginByActor(id);
    }

    @GetMapping("/user/byDirector/{id}")
    public List<String> getUserLoginByDirector(@PathVariable("id") int id) {
        return userService.getUserLoginByDirector(id);
    }
}
