package edu.luc.etl.cs313.android.simpletimer.model.state;

import edu.luc.etl.cs313.android.simpletimer.common.TimerUIUpdateListener;
import edu.luc.etl.cs313.android.simpletimer.model.clock.ClockModel;
import edu.luc.etl.cs313.android.simpletimer.model.time.TimeModel;

/**
 * An implementation of the state machine for the timer.
 *
 * @author laufer
 */
public class DefaultTimerStateMachine implements TimerStateMachine {

    public DefaultTimerStateMachine(final TimeModel timeModel, final ClockModel clockModel) {
        this.timeModel = timeModel;
        this.clockModel = clockModel;
    }

    private final TimeModel timeModel;

    private final ClockModel clockModel;

    /**
     * The internal state of this adapter component. Required for the State pattern.
     */
    private TimerState state;

    protected void setState(final TimerState state) {
        this.state = state;
        uiUpdateListener.updateState(state.getId());
    }

    private TimerUIUpdateListener uiUpdateListener;

    @Override
    public void setUIUpdateListener(final TimerUIUpdateListener uiUpdateListener) {
        this.uiUpdateListener = uiUpdateListener;
    }

    // forward event uiUpdateListener methods to the current state
    // these must be synchronized because events can come from the
    // UI thread or the timer thread

    @Override
    public synchronized void onButtonPress() {
        state.onButtonPress();
    }

    @Override
    public synchronized void onTick() {
        state.onTick();
    }

    @Override
    public void updateUIRuntime() {
        uiUpdateListener.updateTime(timeModel.getRuntime());
    }

    @Override
    public int getTimeAdam(){
        return uiUpdateListener.getTimeAdam();
    }

    // known states
    private final TimerState STOPPED = new StoppedState(this);
    private final TimerState ALARMING = new AlarmingState(this);
    private final TimerState RUNNING = new RunningState(this);
    private final TimerState SETTING = new SettingState(this);

    // transitions
    @Override
    public void toRunningState() {
        setState(RUNNING);
    }

    @Override
    public void toAlarmingState() {
        setState(ALARMING);
    }

    @Override
    public void toSettingState() {
        setState(SETTING);
    }

    @Override
    public void toStoppedState() {
        setState(STOPPED);
    }

    @Override
    public void actionInit() {
        toStoppedState();
        actionReset();
        actionStart();
        actionResetWait();
    }

    @Override
    public void actionReset() {
        timeModel.resetRuntime();
        actionUpdateView();
    }

    @Override
    public void actionStart() {
        clockModel.start();
    }

    @Override
    public void actionStop() {
        clockModel.stop();
    }

    @Override
    public void actionInc() {
        timeModel.incRuntime();
        actionUpdateView();
    }

    @Override
    public void actionDec() {
        timeModel.decRuntime();
        actionUpdateView();
    }

    @Override
    public void actionIncWait(){
        timeModel.incWaittime();
    }


    public void actionResetWait(){
        timeModel.resetWaittime();
    }

    @Override
    public void actionUpdateView() {
        state.updateView();
    }


    public int actionGetRuntime() {return timeModel.getRuntime();}

    @Override
    public int actionGetWaittime(){return timeModel.getWaittime();}

    @Override
    public void actionNotification(){clockModel.playNotification();}

    @Override
    public void actionSetRuntime(){
        timeModel.setRuntime(getTimeAdam());}










}
