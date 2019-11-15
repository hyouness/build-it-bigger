package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.udacity.gradle.jokedisplay.JokeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.joke_pb)
    ProgressBar progressBar;

    @BindView(R.id.fragment_overlay)
    RelativeLayout fragmentOverlay;

    @BindView(R.id.mask_ll)
    LinearLayout maskLayout;

    Snackbar snackBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        new FetchJokeTask(this).execute();
    }

    public void showJokeDisplay(String joke) {
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY, joke);
        startActivity(intent);
    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        fragmentOverlay.setAlpha(0.85f);
        maskLayout.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        fragmentOverlay.setAlpha(1f);
        maskLayout.setVisibility(View.INVISIBLE);
    }

    public void checkConnectionTimeOut() {
        if (snackBar == null) {
            snackBar = Snackbar.make(fragmentOverlay, "Connection Timed Out.", Snackbar.LENGTH_LONG);
        }
        snackBar.show();
    }
}
