package group4.android.timerproject.model.state;

/**
 * Created by demetribairaktaris on 3/23/16.
 */
public class RunningState implements TimerState {

    private TimerSMStateView sm ;

    public RunningState(TimerSMStateView sm)
    {
        this.sm = sm ;
    }
    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public void onButton() {
        sm.toStoppedState();
        sm.actionReset();
        sm.actionStop();
    }

    @Override
    public void onTick() {
        if (sm.getTime() == 0) {
            sm.toAlarmState();
            return;
        }
        sm.actionDec();
    }

    @Override
     public String getState(){
        return "Running";
    }


}
