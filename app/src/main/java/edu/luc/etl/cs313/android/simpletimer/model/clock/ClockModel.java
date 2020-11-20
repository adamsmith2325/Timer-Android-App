package edu.luc.etl.cs313.android.simpletimer.model.clock;

/**
 * The active model of the internal clock that periodically emits tick events.
 *
 * @author laufer
 */
public interface ClockModel extends OnTickSource {
    // TODO: don't think any changes will be needed but I could be wrong
    void start();
    void stop();
    void playNotification();
}
