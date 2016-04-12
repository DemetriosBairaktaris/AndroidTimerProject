package group4.android.timerproject.model.state;

import android.widget.EditText;

/**
 * A implementation of the alarm state of the Timer
 * Created by demetribairaktaris on 3/23/16.
 */
public class AlarmState implements TimerState {

    private TimerSMStateView sm ;

    public AlarmState(TimerSMStateView sm)
    {
        this.sm = sm ;
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    /**
     * Button event will cause the alarm to stop and the timer will transition to stopped.
     */
    @Override
    public void onButton() {
        sm.actionReset();
        sm.actionStop();
        sm.toStoppedState();
    }


    /**
     * Every tick event causes default notification ringtone of phone to be played
     */
    @Override
    public void onTick() {
       sm.playDefaultNotification();
    }
    @Override
    public void setTime(EditText editText) {
        editText.setEnabled(false);
    }

    @Override
    public String getState(){
        return "Alarm";
    }
}
