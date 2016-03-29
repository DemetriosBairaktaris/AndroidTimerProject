package group4.android.timerproject.model.state;

/**
 * Created by demetribairaktaris on 3/23/16.
 */
public class IncrementState implements TimerState {
    private TimerSMStateView sm ;

    public IncrementState(TimerSMStateView sm)
    {
        this.sm = sm ;
    }
    @Override
    public void updateView() {

    }

    @Override
    public void onButton() {

    }
}
