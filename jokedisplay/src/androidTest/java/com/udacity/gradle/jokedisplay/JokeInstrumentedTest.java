package com.udacity.gradle.jokedisplay;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@SmallTest
@RunWith(AndroidJUnit4.class)
public class JokeInstrumentedTest {

    private static final String JOKE_SETUP = "What's the best thing about Switzerland?";
    private static final String JOKE_PUNCHLINE = "I don't know but the flag is a big plus";
    private static final String JOKE = JOKE_SETUP + "\n" + JOKE_PUNCHLINE;

    @Rule
    final public ActivityTestRule<JokeActivity> activityTestRule =
            new ActivityTestRule<JokeActivity>(JokeActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Intent activityIntent = new Intent();
                    activityIntent.putExtra(JokeActivity.JOKE_KEY, JOKE);
                    return activityIntent;
                }
            };

    @Test
    public void whenJokeActivityIsLaunched_JokeSetupAndPunchlineTextViewsAreFilled() {
        onView(withId(R.id.joke_setup_tv)).check(matches(withText(JOKE_SETUP)));
        onView(withId(R.id.joke_punchline_tv)).check(matches(withText(JOKE_PUNCHLINE)));
    }
}
