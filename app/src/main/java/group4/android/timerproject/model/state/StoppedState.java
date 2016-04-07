package group4.android.timerproject.model.state;

/**
 * Created by demetribairaktaris on 3/23/16.
 */
public class StoppedState implements TimerState {

    private TimerSMStateView sm ;

    public StoppedState(TimerSMStateView sm)
    {
        this.sm = sm ;
    }
    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public void onButton() {
        sm.toIncrementState();
        sm.actionInc();
    }

    @Override
    public void onTick() throws UnsupportedOperationException{
        throw new UnsupportedOperationException("onTick unsupported for StoppedState");
    }

    @Override
    public String getState(){
        return "Stopped";
    }
}
