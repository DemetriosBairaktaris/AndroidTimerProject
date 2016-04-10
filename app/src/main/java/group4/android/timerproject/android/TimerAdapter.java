package group4.android.timerproject.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import group4.android.timerproject.R;
import group4.android.timerproject.common.TimerUIUpdateListener;
import group4.android.timerproject.model.ConcreteTimerFacade;
import group4.android.timerproject.model.TimerFacade;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.RingtoneManager;
import android.net.Uri;
import java.io.IOException;

/**
 * Created by demetribairaktaris on 3/23/16.
 */
public class TimerAdapter extends Activity implements TimerUIUpdateListener {

    TimerFacade timerFacade ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerFacade = new ConcreteTimerFacade();
        timerFacade.setUIUpdateListener(this);


    }
    @Override
    public void onStart() {
        super.onStart();
        timerFacade.onStart();

    }

    /**
     * Updates the time in the UI.
     *
     * @param time the time in seconds
     */
    @Override
    public void updateTime(int time) {
            // UI adapter responsibility to schedule incoming events on UI thread
            runOnUiThread(() -> {
                final TextView tvS = (TextView) findViewById(R.id.seconds);
                final int seconds = time;
                tvS.setText(Integer.toString(seconds / 10) + Integer.toString(seconds % 10));
            });
    }

    @Override
    public void updateState(int stateId) {
    }

    /**
     * onButton() receives button events from UI and forwards to the Facade;
     * @param view
     */
    public void onButton(View view){
        this.timerFacade.onButton();
    }


    public void playDefaultNotification() {
        final Uri defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final MediaPlayer mediaPlayer = new MediaPlayer();
        final Context context = getApplicationContext();

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

}
