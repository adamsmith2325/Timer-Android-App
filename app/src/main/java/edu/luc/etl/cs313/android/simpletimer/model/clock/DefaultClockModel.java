package edu.luc.etl.cs313.android.simpletimer.model.clock;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.media.RingtoneManager;
import android.media.Ringtone;
import android.net.Uri;

import edu.luc.etl.cs313.android.simpletimer.android.MyApp;

/**
 * An implementation of the internal clock.
 *
 * @author laufer
 */
public class DefaultClockModel extends Activity implements ClockModel {
    // TODO: don't think any changes will be needed but I could be wrong

    private Timer timer;

    private OnTickListener listener;



    @Override
    public void setOnTickListener(final OnTickListener listener) {
        this.listener = listener;
    }

    @Override
    public void start() {
        timer = new Timer();

        // The clock model runs onTick every 1000 milliseconds
        timer.schedule(new TimerTask() {
            @Override public void run() {
                // fire event
                listener.onTick();
            }
        }, /*initial delay*/ 1000, /*periodic delay*/ 1000);
    }

    @Override
    public void stop() {
        timer.cancel();
    }

    @Override
    public void playNotification(){
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone alarm = RingtoneManager.getRingtone(MyApp.getAppContext(), notification);
            alarm.play();
        } catch (Exception IgnoreCase) { }

    }




}