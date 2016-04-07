package group4.android.timerproject.model.state;



import group4.android.timerproject.common.TimerUIUpdateListener;
import group4.android.timerproject.model.clock.ClockModel;
import group4.android.timerproject.model.time.TimeModel;

/**
 * Created by demetribairaktaris on 3/23/16.
 */

public class DefaultTimerStateMachine implements TimerStateMachine {

   private TimerState state ;  ///Reference to the current state ;
   private TimerState runningState = new RunningState(this);
   private TimerState stoppedState = new StoppedState(this);
   private TimerState incrementState = new IncrementState(this);
   private TimerState alarmState = new AlarmState(this);
   private ClockModel clock ;
   private TimeModel time ;

   public DefaultTimerStateMachine(TimeModel t, ClockModel c)
   {
        this.clock = c ;
        this.time = t ;
   }

    public void setState(TimerState state)
    {
        this.state = state ;
    }

    public String getState(){
        return this.state.getState();
    }

    //Updates
    @Override public int getTime() { return this.time.getRuntime();}

   //Transitions
    @Override public void toRunningState() { this.setState(this.runningState);}
    @Override public void toStoppedState() {this.setState(this.stoppedState);}
    @Override public void toIncrementState() {this.setState(this.incrementState);}
    @Override public void toAlarmState() {this.setState(this.alarmState);}
    //Actions
    @Override public void actionInit() {toStoppedState(); actionReset();}
    @Override public void actionReset() {time.resetRuntime(); actionUpdateView();}
    @Override public void actionStart() {clock.start();}
    @Override public void actionStop() {clock.stop();}
    @Override public void actionInc() {
        if(time.getRuntime() < 99) {
            clock.stop();
            time.incRuntime();
            actionUpdateView();
            clock.start();
        }
    }
    @Override public void actionDec() {
        if (time.getRuntime() != 0) {
            time.decRuntime();
            actionUpdateView();
        }
        else {

        }
    }
    @Override public void actionUpdateView() {state.updateView();}

    @Override
    public void playDefaultNotification() {
        UIUpdateListener.playDefaultNotification();
    }


    @Override public void updateUIRuntime() {
        UIUpdateListener.updateTime(time.getRuntime());
    }

    @Override public void onButton() {
        state.onButton();
    }

    private TimerUIUpdateListener UIUpdateListener;
    @Override public void setUIUpdateListener(TimerUIUpdateListener listener) {
        UIUpdateListener = listener;
    }

    @Override
    public void onTick() {
        this.state.onTick();
    }
}
