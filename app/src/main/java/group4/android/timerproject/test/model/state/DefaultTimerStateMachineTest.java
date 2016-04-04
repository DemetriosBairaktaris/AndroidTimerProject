package group4.android.timerproject.test.model.state;

import org.junit.After;
import org.junit.Before;

import group4.android.timerproject.model.state.DefaultTimerStateMachine;

/**
 * Concrete testcase subclass for the default stopwatch state machine
 * implementation.
 *
 * @author laufer
 * @see http://xunitpatterns.com/Testcase%20Superclass.html
 */

/**
 * Converted from laufer's stopwatch code to cs313p4 timer code by group4 on 4/2/16
 */
public class DefaultTimerStateMachineTest extends AbstractTimerStateMachineTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        setModel(new DefaultTimerStateMachine(getDependency(), getDependency()));
    }

    @After
    public void tearDown() {
        setModel(null);
        super.tearDown();
    }
}
