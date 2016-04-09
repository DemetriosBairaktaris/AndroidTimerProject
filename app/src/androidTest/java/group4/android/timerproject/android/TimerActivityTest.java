package group4.android.timerproject.android;

import android.test.ActivityInstrumentationTestCase2;

import group4.android.timerproject.test.android.AbstractTimerActivityTest;


/**
 * Concrete Android test subclass. Has to inherit from framework class
 * and uses delegation to concrete subclass of abstract test superclass.
 * IMPORTANT: project must export JUnit 4 to make it available on the
 * device.
 *
 * @author laufer
 * @see http://developer.android.com/tools/testing/activity_testing.html
 */
public class TimerActivityTest extends ActivityInstrumentationTestCase2<TimerAdapter> {

    /**
     * Creates an {@link ActivityInstrumentationTestCase2} for the
     * {@link SkeletonActivity} activity.
     */
    public TimerActivityTest() {
        super(TimerAdapter.class);
        actualTest = new AbstractTimerActivityTest() {
            @Override
            protected TimerAdapter getActivity() {
                // return activity instance provided by instrumentation test
                return TimerActivityTest.this.getActivity();
            }
        };
    }

    private AbstractTimerActivityTest actualTest;

    public void testActivityScenarioInit() throws Throwable {
        actualTest.testActivityScenarioInit();
    }

    public void testActivityCheckTestCaseSetUpProperly() {
        actualTest.testActivityCheckTestCaseSetUpProperly();
    }

    public void testActivityScenarioIncAndRun() throws Throwable {
        actualTest.testActivityScenarioIncAndRun();
    }

    public void testActivityScenarioRunToStop() throws Throwable {
        actualTest.testActivityScenarioRunToStop();
    }

    public void testActivityScenarioRunToAlarmToStop() throws Throwable {
        actualTest.testActivityScenarioRunToAlarmToStop();
    }
    public void testScreenRotation() throws Throwable{
        actualTest.testScreenRotation();
    }

}
