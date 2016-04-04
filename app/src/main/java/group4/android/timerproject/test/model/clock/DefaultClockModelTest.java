package group4.android.timerproject.test.model.clock;

import org.junit.After;
import org.junit.Before;

import group4.android.timerproject.model.clock.DefaultClockModel;

/**
 * Concrete testcase subclass for the default clock model implementation.
 *
 * @author laufer
 * @see http://xunitpatterns.com/Testcase%20Superclass.html
 */

/**
 * Converted from laufer's stopwatch code to cs313p4 timer code by group4 on 4/2/16
 */
public class DefaultClockModelTest extends AbstractClockModelTest {

    @Before
    public void setUp() throws Exception {
        setModel(new DefaultClockModel());
    }

    @After
    public void tearDown() throws Exception {
        setModel(null);
    }
}