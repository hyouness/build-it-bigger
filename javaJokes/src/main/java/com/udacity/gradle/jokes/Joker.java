package com.udacity.gradle.jokes;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Joker {

    private static Random random = new Random();

    // Credit: https://www.rd.com/funny-stuff/short-jokes/
    private static List<String> jokes = Arrays.asList(
            "What's the best thing about Switzerland?\n\nI don't know but the flag is a big plus",
            "I invented a new word!\n\nPlagiarism!",
            "Did you hear about the mathematician who's afraid of negative numbers?\n\nHe'll stop at nothing to avoid them.",
            "Why do we tell actors to \"break a leg?\"\n\nBecause every play has a cast.",
            "Hear about the new restaurant called Karma?\n\nThere's no menu: You get what you deserve."
    );

    public static String tellAJoke() {
        int jokeIndex = random.nextInt(jokes.size());
        return jokes.get(jokeIndex);
    }
}
