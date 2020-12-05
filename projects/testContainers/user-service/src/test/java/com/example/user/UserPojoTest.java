package com.example.user;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Ehsan Sh
 */


public class UserPojoTest {

    @Test
    public void userPojoTest() {
        User user = new User(1L, "Ehsan", "Shakeri", "EhsanAsaDev@hotmail.com", "@EhsanAsaDev");
        Assert.assertEquals(user.getId(), new Long(1));
        Assert.assertEquals(user.getFirstName(), "Ehsan");
        Assert.assertEquals(user.getLastName(), "Shakeri");
        Assert.assertEquals(user.getMail(), "EhsanAsaDev@hotmail.com");
        Assert.assertEquals(user.getGitHubUserName(), "@EhsanAsaDev");

    }

}   
