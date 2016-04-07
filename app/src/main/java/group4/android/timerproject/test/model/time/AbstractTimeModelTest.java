package group4.android.timerproject.test.model.time;

import static group4.android.timerproject.common.Constants.SEC_PER_HOUR;
import static group4.android.timerproject.common.Constants.SEC_PER_MIN;
import static group4.android.timerproject.common.Constants.SEC_PER_TICK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import group4.android.timerproject.model.time.TimeModel;

/**
 * Testcase superclass for the time model abstraction.
 * This is a simple unit test of an object without dependencies.
 *
 * @author laufer
 * @see http://xunitpatterns.com/Testcase%20Superclass.html
 */

/**
 * Converted from laufer's stopwatch code to cs313p4 timer code by Eddy on 4/3/16
 */
public abstract class AbstractTimeModelTest {

    private TimeModel model;

    /**
     * Setter for dependency injection. Usually invoked by concrete testcase
     * subclass.
     *
     * @param model
     */
    protected void setModel(final TimeModel model) {
        this.model = model;
    }

    /**
     * Verifies that runtime is initially 0 or less.
     */
    @Test
    public void testPreconditions() {
        assertEquals(0, model.getRuntime());

    }

    /**
     * Verifies that runtime is incremented correctly.
     */
    @Test
    public void testIncrementRuntimeOne() {
        final int rt = model.getRuntime();
        model.incRuntime();
        assertEquals((rt + SEC_PER_TICK) % SEC_PER_MIN, model.getRuntime());
    }

    /**
     * Verifies that runtime turns over correctly.
     */
    @Test
    public void testIncrementRuntimeMany() {
        for (int i = 0; i < 99; i ++) {
            model.incRuntime();
        }
        assertEquals(99, model.getRuntime());

    }

    /**
     * Verifies that runtime decreases correctly
     */
    @Test
    public void testDecrementRuntime(){
        model.incRuntime();
        final int rt = model.getRuntime();
        model.decRuntime();
        assertEquals(0, model.getRuntime());
        assertNotEquals(rt, model.getRuntime());
    }

    @Test
    /**
     * Verifies that runtime resetes correctly
     */
    public void testResetRuntime(){
        for (int i = 0; i < SEC_PER_MIN; i ++){
            model.incRuntime();
        }
        model.resetRuntime();
        assertEquals(0, model.getRuntime());
    }
}
