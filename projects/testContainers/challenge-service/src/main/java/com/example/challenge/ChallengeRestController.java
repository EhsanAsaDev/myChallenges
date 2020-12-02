package com.example.challenge;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author Ehsan Sh</a>
 */

@RestController
public class ChallengeRestController {

    private final ChallengeRepository repository;

    public ChallengeRestController(ChallengeRepository challengeRepository) {
        this.repository = challengeRepository;
    }

    @GetMapping(path = "/challenges/{id}")
    public ResponseEntity<Challenge> getChallenge(@PathVariable Long id){
        return repository.findById(id)
                .map(value -> new ResponseEntity(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("Nothing was found with this id:"+id,HttpStatus.NOT_FOUND));

//        return address.isPresent()
//                ? new ResponseEntity(address.get(), HttpStatus.OK)
//                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/challenges")
    public Collection<Challenge> customers() {
        return this.repository.findAll();
    }
}
