package group4.android.timerproject.test.android;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import android.content.pm.ActivityInfo;
import android.widget.Button;
import android.widget.TextView;
import group4.android.timerproject.R;
import group4.android.timerproject.android.TimerAdapter;

import static group4.android.timerproject.common.Constants.SEC_PER_MIN;

/**
 * Abstract GUI-level test superclass of several essential stopwatch scenarios.
 *
 * @author laufer
 *
 * TODO move this and the other tests to src/test once Android Studio supports
 * non-instrumentation unit tests properly.
 */

/**
 * Converted from laufer's stopwatch code to cs313p4 timer code by group4 on 4/2/16
 */
public abstract class AbstractTimerActivityTest {

    /**
     * Verifies that the activity under test can be launched.
     */
    @Test
    public void testActivityCheckTestCaseSetUpProperly() {
        assertNotNull("activity should be launched successfully", getActivity());
    }

    /**
     * Verifies the following scenario: time is 0.
     *
     * @throws Throwable
     */
    @Test
    public void testActivityScenarioInit() throws Throwable {
        getActivity().runOnUiThread(() -> assertEquals(0, getDisplayedValue()));
    }

    /**
     * Verifies the following scenario: time is 0, increment to 5, wait 3 seconds for timer to start
     * , wait 2 seconds for timer to decrement, expect 3.
     *
     * @throws Throwable
     */
    @Test
    public void testActivityScenarioIncAndRun() throws Throwable {
        getActivity().runOnUiThread(() -> {
            assertEquals(0, getDisplayedValue());
            assertTrue(getButton().performClick());
            performClicks(4);
        });

        Thread.sleep(5500); // <-- do not run this in the UI thread!
        runUiThreadTasks();
        getActivity().runOnUiThread(() -> {
            assertEquals(3, getDisplayedValue());
        });
    }

    /**
     * Verifies the following scenario: Perform 5 clicks, wait 3 seconds to start
     *  and 1 second for time to decrement. Expect 4 seconds, click button,
     *  expect stopped.
     * @throws Throwable
     */
    @Test
    public void testActivityScenarioRunToStop() throws Throwable {
       getActivity().runOnUiThread(() -> performClicks(5));
        Thread.sleep(4500);
        getActivity().runOnUiThread(() -> {
            //assertEquals(4, getDisplayedValue());
            assertTrue(getButton().performClick());
            assertEquals(0, getDisplayedValue());
        });
    }

    @Test
    public void testActivityScenarioRunToAlarmToStop() throws Throwable{
        getActivity().runOnUiThread(()->performClicks(5));
        Thread.sleep(8500);
        getActivity().runOnUiThread(() -> {
            assertEquals(0, getDisplayedValue());
            assertTrue(getButton().performClick());
        });
    }

    @Test
    public void testScreenRotation() throws Throwable
    {
        getActivity().runOnUiThread(() -> {
            assertEquals(0,getDisplayedValue());
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            assertEquals(0, getDisplayedValue());
            performClicks(5);
        });
        Thread.sleep(4500);
        runUiThreadTasks();
        getActivity().runOnUiThread(()->{
            assertEquals(4,getDisplayedValue());
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            assertEquals(4,getDisplayedValue());
        });

    }
    // auxiliary methods for easy access to UI widgets

    protected abstract TimerAdapter getActivity();

    protected int tvToInt(final TextView t) {
        return Integer.parseInt(t.getText().toString().trim());
    }

    protected int getDisplayedValue() {
        final TextView ts = (TextView) getActivity().findViewById(R.id.seconds);
        return  tvToInt(ts);
    }

    protected Button getButton() {
       return (Button) getActivity().findViewById(R.id.stop_increment);
    }

    protected void performClicks(int click){
        Button b = getButton();
        for (int i = 0; i < click; i ++){
            b.performClick();
        }
    }
    /**
     * Explicitly runs tasks scheduled to run on the UI thread in case this is required
     * by the testing framework, e.g., Robolectric.
     */
    protected void runUiThreadTasks() { }
}
