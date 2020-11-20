package edu.luc.etl.cs313.android.simpletimer.test.model.time;

import static edu.luc.etl.cs313.android.simpletimer.common.Constants.SEC_PER_HOUR;
import static edu.luc.etl.cs313.android.simpletimer.common.Constants.SEC_PER_MIN;
import static edu.luc.etl.cs313.android.simpletimer.common.Constants.SEC_PER_TICK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.luc.etl.cs313.android.simpletimer.model.time.TimeModel;

/**
 * Testcase superclass for the time model abstraction.
 * This is a simple unit test of an object without dependencies.
 *
 * @author laufer
 * @see http://xunitpatterns.com/Testcase%20Superclass.html
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
     * Verifies that runtime and laptime are initially 0 or less.
     */
    @Test
    public void testPreconditions() {
        assertEquals(0, model.getRuntime());
        assertTrue(model.getWaittime() <= 0);
    }

    /**
     * Verifies that runtime is incremented correctly.
     */
    @Test
    public void testIncrementRuntimeOne() {
        final int rt = model.getRuntime();
        final int lt = model.getWaittime();
        model.decRuntime();
        assertEquals((rt + SEC_PER_TICK) % SEC_PER_MIN, model.getRuntime());
        assertEquals(lt, model.getWaittime());
    }

    /**
     * Verifies that runtime turns over correctly.
     */
    @Test
    public void testIncrementRuntimeMany() {
        final int rt = model.getRuntime();
        final int lt = model.getWaittime();
        for (int i = 0; i < SEC_PER_HOUR; i ++) {
            model.decRuntime();
        }
        assertEquals(rt, model.getRuntime());
        assertEquals(lt, model.getWaittime());
    }

    /**
     * Verifies that laptime works correctly.
     */
//    @Test
////    public void testLaptime() {
////        final int rt = model.getRuntime();
////        final int lt = model.getSettime();
////        for (int i = 0; i < 5; i ++) {
////            model.decRuntime();
////        }
////        assertEquals(rt + 5, model.getRuntime());
////        assertEquals(lt, model.getSettime());
////        model.incSettime();
////        assertEquals(rt + 5, model.getSettime());
////        for (int i = 0; i < 5; i ++) {
////            model.decRuntime();
////        }
////        assertEquals(rt + 10, model.getRuntime());
////        assertEquals(rt + 5, model.getSettime());
////    }
}
