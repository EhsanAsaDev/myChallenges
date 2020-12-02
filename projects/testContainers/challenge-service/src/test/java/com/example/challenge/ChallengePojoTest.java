package com.example.challenge;

import org.junit.Assert;
import org.junit.Test;

public class ChallengePojoTest {

    @Test
    public void create(){
        Challenge challenge = new Challenge(1l,"Ehsan's Challenge");
        Assert.assertEquals(challenge.getName(),"Ehsan's Challenge");

    }
}
