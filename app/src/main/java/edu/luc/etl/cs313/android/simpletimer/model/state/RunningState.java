package edu.luc.etl.cs313.android.simpletimer.model.state;

import edu.luc.etl.cs313.android.simpletimer.R;

// TODO: Use state diagram to verify functionality

class RunningState implements TimerState {

    public RunningState(final TimerSMStateView sm) {
        this.sm = sm;
    }

    private final TimerSMStateView sm;

    @Override
    public void onButtonPress() {
       sm.actionSetRuntime();
       sm.actionInit();
    }

    // TODO: Verify states perform correct actions running should decrease timer
    @Override
    public void onTick() {
        if(sm.actionGetRuntime() <= 0){
            sm.toAlarmingState();
            sm.actionNotification();
        } else{
            sm.actionDec();
            sm.actionUpdateView();
        }
    }


    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.RUNNING;
    }
}
