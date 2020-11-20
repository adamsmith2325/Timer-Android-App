package edu.luc.etl.cs313.android.simpletimer.model.time;

import static edu.luc.etl.cs313.android.simpletimer.common.Constants.*;

/**
 * An implementation of the timer data model.
 */

// TODO: May need changes

public class DefaultTimeModel implements TimeModel {


    private int runningTime = 0;
    private int waitTime = 0;

    @Override
    public void resetRuntime() {
        runningTime = 0;
    }

    @Override
    public void incRuntime() {
        runningTime = runningTime >= 99 ? 99 : (runningTime + SEC_PER_TICK);
    }

    @Override
    public void decRuntime() {
        runningTime = (runningTime - SEC_PER_TICK);
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
}