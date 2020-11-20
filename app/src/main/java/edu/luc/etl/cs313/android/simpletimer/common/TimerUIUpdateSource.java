package edu.luc.etl.cs313.android.simpletimer.common;

/**
 * A source of UI update events for the timer.
 * This interface is typically implemented by the model.
 *
 * @author laufer
 */
public interface TimerUIUpdateSource {
    // TODO: don't think any changes will be needed but I could be wrong
    void setUIUpdateListener(TimerUIUpdateListener listener);
}
