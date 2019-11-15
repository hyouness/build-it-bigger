package com.udacity.gradle.jokedisplay;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_KEY = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        setTitle("Joke");
        String joke = getIntent().getStringExtra(JOKE_KEY);
        if (!TextUtils.isEmpty(joke)) {
            String jokeSetup = joke.substring(0, joke.indexOf("\n"));
            TextView jokeSetupTV = findViewById(R.id.joke_setup_tv);
            jokeSetupTV.setText(jokeSetup);

            String jokePunchline = joke.substring(joke.lastIndexOf("\n") + 1);
            TextView jokePunchLineTV = findViewById(R.id.joke_punchline_tv);
            jokePunchLineTV.setText(jokePunchline);
        }
    }

}
