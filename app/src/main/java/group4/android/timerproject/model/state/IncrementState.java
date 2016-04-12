package group4.android.timerproject.model.state;

import android.widget.EditText;

/**
 * An implementation of the incrementing state of the timer
 * Created by demetribairaktaris on 3/23/16.
 */
public class IncrementState implements TimerState {
    private TimerSMStateView sm ;
    private int delay;

    public IncrementState(TimerSMStateView sm)
    {
        this.sm = sm ;
        delay = 0;
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    /**
     * Button event will increment time by one
     */
    @Override
    public void onButton() {
        delay = 0;
        sm.actionInc();
    }

    /**
     * Three tick events that happen after a button event and before the next button event will
     * cause the timer to transiton to the running state
     */
    @Override
    public void onTick() {
        if (delay == 2) {
            sm.toRunningState();
        }else {
            delay++;
        }
    }
    @Override
    public void setTime(EditText editText) {
        editText.setEnabled(false);
    }

    @Override
    public String getState(){
        return "Increment";
    }
}
