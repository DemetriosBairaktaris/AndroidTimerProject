package group4.android.timerproject.test.model.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import group4.android.timerproject.R;
import group4.android.timerproject.common.TimerUIUpdateListener;
import group4.android.timerproject.model.clock.ClockModel;
import group4.android.timerproject.model.clock.OnTickListener;
import group4.android.timerproject.model.state.TimerStateMachine;
import group4.android.timerproject.model.time.TimeModel;

/**
 * Testcase superclass for the timer state machine model. Unit-tests the state
 * machine in fast-forward mode by directly triggering successive tick events
 * without the presence of a pseudo-real-time clock. Uses a single unified mock
 * object for all dependencies of the state machine model.
 *
 * @author laufer
 * @see http://xunitpatterns.com/Testcase%20Superclass.html
 */

/**
 * Converted from laufer's stopwatch code to cs313p4 timer code by group4 on 4/2/16
 */

public abstract class AbstractTimerStateMachineTest {

    private TimerStateMachine model;

    private UnifiedMockDependency dependency;

    @Before
    public void setUp() throws Exception {
        model.actionInit();
        dependency = new UnifiedMockDependency();
    }

    @After
    public void tearDown() {
        model = null;
        dependency = null;
    }

    /**
     * Setter for dependency injection. Usually invoked by concrete testcase
     * subclass.
     *
     * @param model
     */
    protected void setModel(final TimerStateMachine model) {
        this.model = model;
        if (model == null)
            return;
        this.model.setUIUpdateListener(dependency);
        this.model.actionInit();
    }

    protected UnifiedMockDependency getDependency() {
        return dependency;
    }

    /**
     * Verifies that we're initially in the stopped state (and told the listener
     * about it).
     */
    @Test
    public void testPreconditions() {
        assertEquals(R.string.STOPPED, model.getState());
        assertEquals(0, dependency.getRuntime());

    }

    /**
     * Verifies the following scenario: time is 0, state is Stopped, press button.
     * Increments time by 1, is in Increment State.
     */
    @Test
    public void testScenarioStoppedIncrement() { //ok
        assertTimeEquals(0);
        assertEquals("Stopped", model.getState());
        // directly invoke the button press event handler methods
        // send to Increment State
        model.onButton();
        assertEquals("Increment", model.getState());
        assertTimeEquals(1);
    }

    /**
     * Verifies the following scenario: go from Running to Increment state, time is 1.
     * Increment time to max time (99).
     *
     */
    @Test
    public void testScenarioIncrementMax() {
        assertTimeEquals(0);
        model.onButton();
        assertEquals("Increment", model.getState());
        onButtonRepeat(98);
        assertTimeEquals(99);
        model.onButton();
        assertTimeEquals(99); //check that user can't increment past 99
    }

    /**
     * Verifies the following scenario: increase time to 5, wait 3 seconds.
     * In Running State
     * @throws InterruptedException
     */
    @Test
    public void testScenarioIncrementRunning() throws InterruptedException {
        assertTimeEquals(0);
        model.onButton();
        // Increment time to 5 seconds
        onButtonRepeat(4);
        assertTimeEquals(5);
        onTickRepeat(3); //mock 3-second-tick
        // Send to Running State
        assertEquals("Running", model.getState());
    }

    /**
     * Verifies the following scenario: increase time to 5, wait 3 seconds,
     * interrupt with button click.
     * Time is 0, state is Stopped.
     */
    @Test
    public void testScenarioRunningStopped() throws InterruptedException {
        assertTimeEquals(0);
        model.onButton();
        onButtonRepeat(4);
        assertTimeEquals(5);
        onTickRepeat(3);
        assertEquals("Running",model.getState());
        model.onButton();// Press button to send to Stopped State,should reset the runTime
        assertTimeEquals(0);
        assertEquals("Stopped", model.getState());

    }

    /**
     * Verifies that value of the runtime is 3 after 2 seconds of countdown.
     * @throws InterruptedException
     */

    @Test
    public void testScenarioRunningValues() throws InterruptedException
    {
        assertTimeEquals(0);
        model.onButton();
        onButtonRepeat(4);
        onTickRepeat(3); //get past 3 second interval
        onTickRepeat(2); //count down 2 seconds
        assertTimeEquals(3);
    }

    /**
     * Verifies that the alarm state is reached after 1 second count-down from running state.
     * @throws InterruptedException
     */

    @Test
    public void testScenarioRunningAlarm() throws InterruptedException {
        assertTimeEquals(0);
        model.onButton();
        onTickRepeat(3);
        onTickRepeat(1);
        assertEquals("Alarm",model.getState());
    }


    /**
     * Verifies scenerio :  press button, wait and then check if reaches alarm state, press button
     * and check if in stopped state.
     * @throws InterruptedException
     */
    @Test
    public void testScenarioAlarmStopped() throws InterruptedException {
        assertTimeEquals(0);
        model.onButton();
        onTickRepeat(3);
        onTickRepeat(1);
        assertEquals("Alarm", model.getState());
        model.onButton();
        assertEquals("Stopped", model.getState());
    }

    /**
     * Sends the given number of button events to the model.
     *
     *  @param n the number of tick events
     */
    protected void onButtonRepeat(final int n) {
        for (int i = 0; i < n; i++)
            model.onButton();
    }

    /**
     * sends the given number of tick events to the model
     */
    protected void onTickRepeat(final int n)
    {
        for(int i = 0 ; i < n ; i++){
            model.onTick();
        }

    }

    /**
     * Checks whether the model has invoked the expected time-keeping
     * methods on the mock object.
     */
    protected void assertTimeEquals(final int t) {
        assertEquals(t, dependency.getTime());
    }
}

/**
 * Manually implemented mock object that unifies the three dependencies of the
 * stopwatch state machine model. The three dependencies correspond to the three
 * interfaces this mock object implements.
 *
 * @author laufer
 */

/**
 * Converted from laufer's stopwatch code to cs313p4 timer code by group4 on 4/2/16
 */
class UnifiedMockDependency implements TimeModel, ClockModel, TimerUIUpdateListener {

    private int timeValue = -1, stateId = -1;

    private int runTime = 0;

    private boolean started = false;

    public int getTime() {
        return timeValue;
    }


    public boolean isStarted() {
        return started;
    }

    @Override
    public void updateTime(final int timeValue) {
        this.timeValue = timeValue;
    }

    @Override
    public void updateState(final int stateId) {
        this.stateId = stateId;
    }

    @Override
    public void setOnTickListener(OnTickListener listener) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void start() {
        started = true;
    }

    @Override
    public void stop() {
        started = false;
    }

    @Override
    public void resetRuntime() {
        runTime = 0;
    }

    @Override
    public void incRuntime() {
        runTime++;
    }

    @Override
    public void decRuntime() {runTime-- ;}

    @Override
    public int getRuntime() {
        return runTime;
    }

    @Override
    public void setRunTime(int runTime) {
        this.runTime = runTime ;
    }
}
