package edu.luc.etl.cs313.android.simpletimer.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

import edu.luc.etl.cs313.android.simpletimer.R;
import edu.luc.etl.cs313.android.simpletimer.common.Constants;
import edu.luc.etl.cs313.android.simpletimer.common.TimerUIUpdateListener;
import edu.luc.etl.cs313.android.simpletimer.model.ConcreteTimerModelFacade;
import edu.luc.etl.cs313.android.simpletimer.model.TimerModelFacade;

/**
 * A thin adapter component for the timer.
 *
 * @author laufer
 */
public class TimerAdapter extends Activity implements TimerUIUpdateListener {

    private static String TAG = "timer-android-activity";

    /**
     * The state-based dynamic model.
     */
    private TimerModelFacade model;

    protected void setModel(final TimerModelFacade model) {
        this.model = model;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inject dependency on view so this adapter receives UI events
        setContentView(R.layout.activity_main);
        // inject dependency on model into this so model receives UI events
        this.setModel(new ConcreteTimerModelFacade());
        // inject dependency on this into model to register for UI updates
        model.setUIUpdateListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        model.onStart();
    }

    /**
     * Updates the seconds and minutes in the UI.
     * @param time
     */
    public void updateTime(final int time) {
        // UI adapter responsibility to schedule incoming events on UI thread
        runOnUiThread(() -> {
            final TextView tvS = (TextView) findViewById(R.id.seconds);
            //final int seconds = time % Constants.SEC_PER_MIN;
            tvS.setText(String.format(Locale.getDefault(), "%02d", time));
        });
    }

    public int getTimeAdam(){
        final TextView tvS = findViewById(R.id.seconds);
        String timeString = tvS.getText().toString();
        int time = Integer.parseInt(timeString);
        tvS.setText(String.format(Locale.getDefault(), "%02d", time));
        return (time > 99) ? 99 : time;
    }

    /**
     * Updates the state name in the UI.
     * @param stateId
     */
    public void updateState(final int stateId) {
        // UI adapter responsibility to schedule incoming events on UI thread
        runOnUiThread(() -> {
            final TextView stateName = (TextView) findViewById(R.id.stateName);
            stateName.setText(getString(stateId));
        });
    }

    // forward event listener methods to the model
    public void onButtonPress(final View view) {
        model.onButtonPress();
    }

}
