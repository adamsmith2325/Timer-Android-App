package edu.luc.etl.cs313.android.simpletimer.model.state;

/**
 * The restricted view states have of their surrounding state machine.
 * This is a client-specific interface in Peter Coad's terminology.
 *
 * @author laufer
 */
interface TimerSMStateView {

    // transitions
    void toRunningState();
    void toAlarmingState();
    void toSettingState();
    void toStoppedState();

    // actions
    void actionInit();
    void actionReset();
    void actionStart();
    void actionStop();
    void actionResetWait();
    void actionInc();
    void actionIncWait();
    void actionNotification();
    void actionDec();
    int actionGetRuntime();
    int actionGetWaittime();
    void actionUpdateView();
    void actionSetRuntime();

    // state-dependent UI updates

    void updateUIRuntime();
    int getTimeAdam();
}
