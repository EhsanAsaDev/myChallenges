package com.example.user;

import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Ehsan Sh
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserEntityTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveWithRepository(){

        User user = new User(null,"Ehsan", "Shakeri","EhsanAsaDev@hotmail.com","@EhsanAsaDev");


        User saved = userRepository.save(user);

        BDDAssertions.then(saved.getId()).isNotNull();
        BDDAssertions.then(saved.getFirstName()).isEqualToIgnoringCase("Ehsan");
        BDDAssertions.then(saved.getLastName()).isEqualToIgnoringCase("shakeri");
        BDDAssertions.then(saved.getMail()).isEqualToIgnoringCase("EhsanAsaDev@hotmail.com");

    }

}   
