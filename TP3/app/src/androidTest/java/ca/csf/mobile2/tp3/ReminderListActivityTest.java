package ca.csf.mobile2.tp3;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.csf.mobile2.tp3.activity.ReminderListActivity_;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ReminderListActivityTest {

    @Rule
    public ActivityTestRule<ReminderListActivity_> activityRule = new ActivityTestRule<>(ReminderListActivity_.class);

    @Test
    public void selectSortButton(){
        onView(withId(R.id.sortByImportanceButton)).perform(click());
        onView(withId(R.id.sortByImportanceButton)).check(matches(isSelected()));
        onView(withId(R.id.sortByTimeButton)).check(matches(not(isSelected())));

        onView(withId(R.id.sortByTimeButton)).perform(click());
        onView(withId(R.id.sortByTimeButton)).check(matches(isSelected()));
        onView(withId(R.id.sortByImportanceButton)).check(matches(not(isSelected())));

    }

}
