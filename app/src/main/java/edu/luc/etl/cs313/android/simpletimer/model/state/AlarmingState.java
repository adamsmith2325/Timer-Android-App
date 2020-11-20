package edu.luc.etl.cs313.android.simpletimer.model.state;


import edu.luc.etl.cs313.android.simpletimer.R;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import java.io.IOException;


// TODO jjge: Use state diagram to verify functionality

class AlarmingState implements TimerState {

    public AlarmingState(final TimerSMStateView sm) {
        this.sm = sm;
    }

    private final TimerSMStateView sm;

    @Override
    public void onButtonPress() {
        sm.actionStop();
        sm.actionInit();
    }

    @Override
    public void onTick() {
        // TODO jjge: Add alarm sounding logic by having a sound occur when method is called
        // Yacobellis made a note about this piece of the project, he said this may help, thought I'd comment it in in case you want it. Trash if you please - Adam
        //playDefaultNotification();
        sm.actionNotification();
    }



    @Override
    public void updateView() {
        sm.actionUpdateView();
    }

    @Override
    public int getId() {

        return R.string.ALARMING;
    }

    /*protected void playDefaultNotification(){
        final Uri defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final MediaPlayer mediaPlayer = new MediaPlayer();
        final Context context = getActivity().getApplicationContext();

        try {
            mediaPlayer.setDataSource(context, defaultRingtoneUri);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
            mediaPlayer.prepare();
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);
            mediaPlayer.start();
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }

    }

*/



}
