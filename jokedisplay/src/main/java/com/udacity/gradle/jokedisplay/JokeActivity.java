package com.udacity.gradle.jokedisplay;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_KEY = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Joke");
        String joke = getIntent().getStringExtra(JOKE_KEY);
        if (!TextUtils.isEmpty(joke)) {
            String jokeSetup = joke.substring(0, joke.indexOf("\n"));
            TextView jokeSetupTV = findViewById(R.id.joke_setup_tv);
            jokeSetupTV.setText(jokeSetup);

            String jokePunchline = joke.substring(joke.lastIndexOf("\n"));
            TextView jokePunchLineTV = findViewById(R.id.joke_punchline_tv);
            jokePunchLineTV.setText(jokePunchline);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
