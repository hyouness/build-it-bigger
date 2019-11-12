package com.udacity.gradle.jokes;

import org.junit.Assert;
import org.junit.Test;

public class JokerTest {

    @Test
    public void whenJokerTellsJoke_jokeIsReturned() {
        String joke = Joker.tellAJoke();
        Assert.assertNotNull(joke);
        Assert.assertNotEquals(joke, "");
    }
}
