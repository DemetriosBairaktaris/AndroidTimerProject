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
 * Testcase superclass for the stopwatch state machine model. Unit-tests the state
 * machine in fast-forward mode by directly triggering successive tick events
 * without the presence of a pseudo-real-time clock. Uses a single unified mock
 * object for all dependencies of the state machine model.
 *
 * @author laufer
 * @see http://xunitpatterns.com/Testcase%20Superclass.html
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
        assertEquals(R.string.STOPPED, dependency.getState());
        assertEquals(0, dependency.getRuntime());

    }

    /**
     * Verifies the following scenario: time is 0, state is Stopped, press button.
     * Increments time by 1, is in Increment State.
     */
    @Test
    public void testScenarioStoppedIncrement() {
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
        // Send to Running State
        Thread.sleep(3500);
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
        Thread.sleep(3500);
        // Press button to send to Stopped State
        model.onButton();
        assertTimeEquals(0);
        assertEquals("Stopped", model.getState());
    }

    @Test
    public void testScenarioRunningValues() throws InterruptedException
    {
        assertTimeEquals(0);
        model.onButton();
        onButtonRepeat(4);
        Thread.sleep(5500);
        assertTimeEquals(3);
    }

    @Test
    public void testScenarioRunningAlarm() throws InterruptedException {
        assertTimeEquals(0);
        model.onButton();
        Thread.sleep(4500);
        assertEquals("Alarm",model.getState());
    }

    @Test
    public void testScenarioAlarmStopped() throws InterruptedException{
        assertTimeEquals(0);
        model.onButton();
        Thread.sleep(4500);
        assertEquals("Alarm",model.getState());
        model.onButton();
        assertEquals("Stopped", model.getState());
    }

    /**
     * Verifies the following scenario: time is 0, press start, wait 5+ seconds,
     * expect time 5, press lap, wait 4 seconds, expect time 5, press start,
     * expect time 5, press lap, expect time 9, press lap, expect time 0.
     *
     * @throws Throwable
     */


    /**
     * Sends the given number of tick events to the model.
     *
     *  @param n the number of tick events
     */
    protected void onButtonRepeat(final int n) {
        for (int i = 0; i < n; i++)
            model.onButton();
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
class UnifiedMockDependency implements TimeModel, ClockModel, TimerUIUpdateListener {

    private int timeValue = -1, stateId = -1;

    private int runningTime = 0, lapTime = -1;

    private boolean started = false;

    public int getTime() {
        return timeValue;
    }

    public int getState() {
        return stateId;
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
        runningTime = 0;
    }

    @Override
    public void incRuntime() {
        runningTime++;
    }

    @Override
    public void decRuntime() {

    }

    @Override
    public int getRuntime() {
        return runningTime;
    }
}
