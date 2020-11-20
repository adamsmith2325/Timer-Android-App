package edu.luc.etl.cs313.android.simpletimer.model.clock;

/**
 * A source of onTick events for the timer.
 * This interface is typically implemented by the model.
 *
 * @author laufer
 */
public interface OnTickSource {
    // TODO: don't think any changes will be needed but I could be wrong
    void setOnTickListener(OnTickListener listener);
}
