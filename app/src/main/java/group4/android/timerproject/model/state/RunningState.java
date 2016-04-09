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
        sm.actionReset();
        sm.actionStop();
        sm.toStoppedState();
    }

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
