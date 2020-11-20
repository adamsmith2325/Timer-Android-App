package edu.luc.etl.cs313.android.simpletimer.model.state;

import edu.luc.etl.cs313.android.simpletimer.R;

// TODO: Use state diagram to verify functionality

class SettingState implements TimerState {

    public SettingState(final TimerSMStateView sm) {
        this.sm = sm;
    }

    private final TimerSMStateView sm;

    @Override
    public void onButtonPress() {
       if(sm.actionGetRuntime() < 99){
           sm.actionResetWait();
           sm.actionInc();
           sm.actionUpdateView();
       }else{
           sm.actionNotification();
           sm.actionResetWait();
           sm.toRunningState();
       }
    }

    @Override
    public void onTick() {
       if(sm.actionGetWaittime() < 3){
           sm.actionIncWait();
       }else{
           sm.actionNotification();
           sm.actionResetWait();
           sm.actionDec();
           sm.toRunningState();
       }
    }

    // TODO: Verify states perform correct actions

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.SETTING;
    }
}
