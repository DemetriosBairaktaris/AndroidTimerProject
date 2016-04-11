package group4.android.timerproject.model.state;

/**
 * An Implementation of the alarm state of the timer
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

    /**
     * Button event transitions timer into incrementstate and increments timer by one
     */
    @Override
    public void onButton() {
        sm.toIncrementState();
        sm.actionInc();
    }

    /**
     * There should be no tick events in the stop state.
     * @throws UnsupportedOperationException
     */
    @Override
    public void onTick() throws UnsupportedOperationException{
        throw new UnsupportedOperationException("onTick unsupported for StoppedState");
    }

    @Override
    public String getState(){
        return "Stopped";
    }
}
