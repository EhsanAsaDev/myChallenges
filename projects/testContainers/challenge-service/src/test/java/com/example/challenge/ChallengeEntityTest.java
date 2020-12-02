package com.example.challenge;

import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ChallengeEntityTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Test
    public void persist() throws Exception{
        Challenge challenge = new Challenge(null,"Ehsan's Challenge");
        Challenge saved = this.testEntityManager.persistFlushFind(challenge);

        BDDAssertions.then(saved.getId()).isNotNull();
        BDDAssertions.then(saved.getName()).isEqualToIgnoringCase("Ehsan's Challenge");
        BDDAssertions.then(saved.getName()).isNotBlank();


    }

    @Test
    public void persistWithRepository() throws Exception{
        Challenge challenge = new Challenge(null,"Ehsan's Challenge");
        Challenge saved = this.challengeRepository.save(challenge);

        BDDAssertions.then(saved.getId()).isNotNull();
        BDDAssertions.then(saved.getName()).isEqualToIgnoringCase("Ehsan's Challenge");
        BDDAssertions.then(saved.getName()).isNotBlank();


    }
}
