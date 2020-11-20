package edu.luc.etl.cs313.android.simpletimer.model.time;

/**
 * The passive data model of the timer.
 * It does not emit any events.
 *
 * @author laufer
 */
public interface TimeModel {
    void resetRuntime();
    void incRuntime();
    void decRuntime();
    int getRuntime();
    void resetWaittime();
    void incWaittime();
    void setRuntime(int time);
    int getWaittime();
}
