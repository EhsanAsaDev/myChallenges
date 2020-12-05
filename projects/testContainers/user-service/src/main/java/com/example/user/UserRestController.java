package com.example.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ehsan Sh
 */

@RestController
public class UserRestController {

    private UserRepository userRepository;

    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity("Nothing was found with this id:" + id, HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/users")
    public ResponseEntity register(@RequestBody User user) {
        return new ResponseEntity(userRepository.save(user), HttpStatus.OK);
    }
}   
