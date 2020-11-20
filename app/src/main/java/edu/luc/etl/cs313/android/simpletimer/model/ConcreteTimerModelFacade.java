package edu.luc.etl.cs313.android.simpletimer.model;

import edu.luc.etl.cs313.android.simpletimer.common.TimerUIUpdateListener;
import edu.luc.etl.cs313.android.simpletimer.model.clock.ClockModel;
import edu.luc.etl.cs313.android.simpletimer.model.clock.DefaultClockModel;
import edu.luc.etl.cs313.android.simpletimer.model.state.DefaultTimerStateMachine;
import edu.luc.etl.cs313.android.simpletimer.model.state.TimerStateMachine;
import edu.luc.etl.cs313.android.simpletimer.model.time.DefaultTimeModel;
import edu.luc.etl.cs313.android.simpletimer.model.time.TimeModel;

/**
 * An implementation of the model facade.
 *
 * @author laufer
 */
public class ConcreteTimerModelFacade implements TimerModelFacade {

    private TimerStateMachine stateMachine;

    private ClockModel clockModel;

    private TimeModel timeModel;

    public ConcreteTimerModelFacade() {
        timeModel = new DefaultTimeModel();
        clockModel = new DefaultClockModel();
        stateMachine = new DefaultTimerStateMachine(timeModel, clockModel);
        clockModel.setOnTickListener(stateMachine);
    }

    @Override
    public void onStart() {
        stateMachine.actionInit();
    }

    @Override
    public void setUIUpdateListener(final TimerUIUpdateListener listener) {
        stateMachine.setUIUpdateListener(listener);
    }

    @Override
    public void onButtonPress() {
        stateMachine.onButtonPress();
    }


}
