package edu.luc.etl.cs313.android.simpletimer.test.model.state;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.TextView;
import android.app.Activity;

import static edu.luc.etl.cs313.android.simpletimer.common.Constants.SEC_PER_TICK;
import static org.junit.Assert.assertEquals;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import edu.luc.etl.cs313.android.simpletimer.R;
import edu.luc.etl.cs313.android.simpletimer.android.MyApp;
import edu.luc.etl.cs313.android.simpletimer.common.TimerUIUpdateListener;
import edu.luc.etl.cs313.android.simpletimer.model.clock.ClockModel;
import edu.luc.etl.cs313.android.simpletimer.model.clock.OnTickListener;
import edu.luc.etl.cs313.android.simpletimer.model.state.TimerStateMachine;
import edu.luc.etl.cs313.android.simpletimer.model.time.TimeModel;

/**
 * Testcase superclass for the timer state machine model. Unit-tests the state
 * machine in fast-forward mode by directly triggering successive tick events
 * without the presence of a pseudo-real-time clock. Uses a single unified mock
 * object for all dependencies of the state machine model.
 *
 * @author laufer
 *
 */
public abstract class AbstractTimerStateMachineTest {

    private TimerStateMachine model;

    private UnifiedMockDependency dependency;

    @Before
    public void setUp() throws Exception {
        dependency = new UnifiedMockDependency();
    }

    @After
    public void tearDown() {
        dependency = null;
    }

    // TODO Section-4: Add test cases

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
        assertEquals(R.string.ALARMING, dependency.getState());
    }

    /**
     * Verifies the following scenario: time is 0, press start, wait 5+ seconds,
     * expect time 5.
     */
    @Test
    public void testScenarioRun() {
        assertTimeEquals(0);
        // directly invoke the button press event handler methods
        model.onButtonPress();
        onTickRepeat(5);
        assertTimeEquals(5);
    }

    /**
     * Verifies the following scenario: time is 0, press start, wait 5+ seconds,
     * expect time 5, press lap, wait 4 seconds, expect time 5, press start,
     * expect time 5, press lap, expect time 9, press lap, expect time 0.
     *
     * @throws Throwable
     */
//    @Test
//    public void testScenarioRunLapReset() {
//        assertTimeEquals(0);
//        // directly invoke the button press event handler methods
//        model.onButtonPress();
//        assertEquals(R.string.RUNNING, dependency.getState());
//        assertTrue(dependency.isStarted());
//        onTickRepeat(5);
//        assertTimeEquals(5);
//        model.onLapReset();
//        assertEquals(R.string.LAP_RUNNING, dependency.getState());
//        assertTrue(dependency.isStarted());
//        onTickRepeat(4);
//        assertTimeEquals(5);
//        model.onButtonPress();
//        assertEquals(R.string.LAP_STOPPED, dependency.getState());
//        assertFalse(dependency.isStarted());
//        assertTimeEquals(5);
//        model.onLapReset();
//        assertEquals(R.string.STOPPED, dependency.getState());
//        assertFalse(dependency.isStarted());
//        assertTimeEquals(9);
//        model.onLapReset();
//        assertEquals(R.string.STOPPED, dependency.getState());
//        assertFalse(dependency.isStarted());
//        assertTimeEquals(0);
//    }

    /**
     * Sends the given number of tick events to the model.
     *
     *  @param n the number of tick events
     */
    protected void onTickRepeat(final int n) {
        for (int i = 0; i < n; i++)
            model.onTick();
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
 * timer state machine model. The three dependencies correspond to the three
 * interfaces this mock object implements.
 *
 * @author laufer
 */
class UnifiedMockDependency implements TimeModel, ClockModel, TimerUIUpdateListener {

    private int timeValue = -1, stateId = -1;

    private int runningTime = 0;

    private int waitTime = 0;

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
        runningTime--;
    }

    @Override
    public int getRuntime() {
        return runningTime;
    }

    @Override
    public void resetWaittime() {
        waitTime = 0;
    }

    @Override
    public int getWaittime() {
        return waitTime;
    }

    @Override
    public void incWaittime() {
        waitTime = (waitTime + SEC_PER_TICK);
    }

    @Override
    public void setRuntime(int time){
        runningTime = (time > 99) ? 99 : time;
    }

    @Override
    public void playNotification(){
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone alarm = RingtoneManager.getRingtone(MyApp.getAppContext(), notification);
            alarm.play();
        } catch (Exception IgnoreCase) { }

    }

    @Override
    public int getTimeAdam(){
       /* final TextView tvS = findViewById
        String timeString = tvS.getText().toString();
        int time = Integer.parseInt(timeString);
        tvS.setText(String.format(Locale.getDefault(), "%02d", time));
        return (time > 99) ? 99 : time; */
        return 0;
    }
}
