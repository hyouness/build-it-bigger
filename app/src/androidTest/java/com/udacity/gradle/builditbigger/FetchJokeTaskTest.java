package com.udacity.gradle.builditbigger;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;


@RunWith(AndroidJUnit4.class)
public class FetchJokeTaskTest {

    private String joke;
    private CountDownLatch countDownLatch;

    @Rule
    final public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup() {
        countDownLatch = new CountDownLatch(1);
    }

    @Test
    public void whenAsyncTaskIsExecuted_jokeIsReturned() throws InterruptedException {
        final MainActivity mainActivity = activityTestRule.getActivity();

        FetchJokeTask fetchJokeTask = new FetchJokeTask(mainActivity) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                joke = result;
                countDownLatch.countDown();
            }
        };

        mainActivity.runOnUiThread(fetchJokeTask::execute);
        countDownLatch.await();

        Assert.assertNotNull(joke);
        Assert.assertNotEquals("", joke);
    }

    @After
    public void tearDown() {
        countDownLatch.countDown();
    }
}
