package edu.luc.etl.cs313.android.simpletimer.android;

import android.test.ActivityInstrumentationTestCase2;

import edu.luc.etl.cs313.android.simpletimer.test.android.AbstractTimerActivityTest;

/**
 * Concrete Android test subclass. Has to inherit from framework class
 * and uses delegation to concrete subclass of abstract test superclass.
 * IMPORTANT: project must export JUnit 4 to make it available on the
 * device.
 *
 * @author laufer
 * @see "https://developer.android.com/training/testing/ui-testing/"
 */
public class StopwatchActivityTest extends ActivityInstrumentationTestCase2<TimerAdapter> {

    /**
     * Creates an {@link ActivityInstrumentationTestCase2} for the
     * {@link TimerAdapter} activity.
     */
    public StopwatchActivityTest() {
        super(TimerAdapter.class);
        actualTest = new AbstractTimerActivityTest() {
            @Override
            protected TimerAdapter getActivity() {
                // return activity instance provided by instrumentation test
                if (timerAdapter == null)
                    timerAdapter = StopwatchActivityTest.this.getActivity();
                return timerAdapter;
            }
        };
    }

    private static TimerAdapter timerAdapter = null;
    private AbstractTimerActivityTest actualTest;

    public void testActivityCheckTestCaseSetUpProperly() {
        actualTest.testActivityCheckTestCaseSetUpProperly();
    }

    public void testActivityScenarioRun() throws Throwable {
        actualTest.testActivityScenarioRun();
    }

}
