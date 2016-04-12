package group4.android.timerproject.model.state;

import android.widget.EditText;

/**
 * An implementation of the running state of the timer.
 * Created by demetribairaktaris on 3/23/16.
 */
public class RunningState implements TimerState {

    private TimerSMStateView sm ;

    public RunningState(TimerSMStateView sm)
    {
        this.sm = sm ;
    }

    /**
     * Updates the view on screen
     */
    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    /**
     * Button event will stop the timer and runtime will display 00
     */
    @Override
    public void onButton() {
        sm.actionReset();
        sm.actionStop();
        sm.toStoppedState();
    }

    @Override
    public void setTime(EditText editText) {
        editText.setEnabled(false);
    }

    /**
     * Tick event will decrement the runtime by 1 until 0, then timer goes to alarm state.
     */
    @Override
    public void onTick() {
        sm.actionDec();
        if (sm.getTime() == 0) {
            sm.toAlarmState();
            return ;
        }
    }

    @Override
     public String getState(){
        return "Running";
    }

}
