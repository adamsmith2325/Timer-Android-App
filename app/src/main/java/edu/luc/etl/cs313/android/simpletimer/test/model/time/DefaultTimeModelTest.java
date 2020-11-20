package edu.luc.etl.cs313.android.simpletimer.test.model.time;

import org.junit.After;
import org.junit.Before;

import edu.luc.etl.cs313.android.simpletimer.model.time.DefaultTimeModel;

/**
 * Concrete testcase subclass for the default time model implementation.
 *
 * @author laufer
 * @see http://xunitpatterns.com/Testcase%20Superclass.html
 */
public class DefaultTimeModelTest extends AbstractTimeModelTest {

    @Before
    public void setUp() throws Exception {
        setModel(new DefaultTimeModel());
    }
    // TODO: don't think we need to test this feature

    @After
    public void tearDown() throws Exception {
        setModel(null);
    }
}
