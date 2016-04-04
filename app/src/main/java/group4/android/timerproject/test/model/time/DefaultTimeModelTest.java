package group4.android.timerproject.test.model.time;

import org.junit.After;
import org.junit.Before;

import group4.android.timerproject.model.time.DefaultTimeModel;

/**
 * Concrete testcase subclass for the default time model implementation.
 *
 * @author laufer
 * @see http://xunitpatterns.com/Testcase%20Superclass.html
 */

/**
 * Converted from laufer's stopwatch code to cs313p4 timer code by Eddy on 4/3/16
 */
public class DefaultTimeModelTest extends AbstractTimeModelTest {

    @Before
    public void setUp() throws Exception {
        setModel(new DefaultTimeModel());
    }

    @After
    public void tearDown() throws Exception {
        setModel(null);
    }
}