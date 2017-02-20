package ca.csf.mobile2.tp3;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.csf.mobile2.tp3.activity.CreateNewReminderActivity_;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CreateNewReminderActivityTest {

    @Rule
    public ActivityTestRule<CreateNewReminderActivity_> activityRule = new ActivityTestRule<>(CreateNewReminderActivity_.class);

    @Test
    public void typeDescription(){
        onView(withId(R.id.descriptionEditText)).perform(typeText("qwerty123456"));
        onView(withId(R.id.descriptionEditText)).check(matches(withText("qwerty123456")));
    }

    @Test
    public void selectImportance(){
        onView(withId(R.id.notImportantButton)).perform(click());
        onView(withId(R.id.notImportantButton)).check(matches(isSelected()));
        onView(withId(R.id.importantButton)).check(matches(not(isSelected())));
        onView(withId(R.id.veryImportantButton)).check(matches(not(isSelected())));

        onView(withId(R.id.importantButton)).perform(click());
        onView(withId(R.id.notImportantButton)).check(matches(not(isSelected())));
        onView(withId(R.id.importantButton)).check(matches(isSelected()));
        onView(withId(R.id.veryImportantButton)).check(matches(not(isSelected())));

        onView(withId(R.id.veryImportantButton)).perform(click());
        onView(withId(R.id.notImportantButton)).check(matches(not(isSelected())));
        onView(withId(R.id.importantButton)).check(matches(not(isSelected())));
        onView(withId(R.id.veryImportantButton)).check(matches(isSelected()));

    }

}
