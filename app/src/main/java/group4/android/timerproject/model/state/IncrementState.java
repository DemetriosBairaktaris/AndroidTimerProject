package group4.android.timerproject.model.state;

/**
 * Created by demetribairaktaris on 3/23/16.
 */
public class IncrementState implements TimerState {
    private TimerSMStateView sm ;
    private int delay;

    public IncrementState(TimerSMStateView sm)
    {
        this.sm = sm ;
        delay = 1;
    }
    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public void onButton() {
        delay = 1;
        sm.actionInc();
    }

    @Override
    public void onTick() {
        if (delay == 3) {
            sm.toRunningState();
        }else {
            delay++;
        }
    }

    @Override
    public String getState(){
        return "Increment";
    }
}
