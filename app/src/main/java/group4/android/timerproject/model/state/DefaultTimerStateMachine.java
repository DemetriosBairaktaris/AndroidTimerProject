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
   public DefaultTimerStateMachine(ClockModel c, TimeModel t)
   {
       //TODO write constructor with correct parameters;
       //will need to add instance variables
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

   //Transitions
    @Override public void toRunningState() { this.setState(this.runningState);}
    @Override public void toStoppedState() {this.setState(this.stoppedState);}
    @Override public void toIncrementState() {this.setState(this.incrementState);}
    @Override public void toAlarmState() {this.setState(this.alarmState);}
    //Actions
    @Override public void actionInit() {}
    @Override public void actionReset() {}
    @Override public void actionStart() {}
    @Override public void actionStop() {}
    @Override public void actionInc() {}
    @Override public void actionDec() {}
    @Override public void actionUpdateView() {}


    @Override public void updateUIRuntime() {}

    @Override public void onButton() {} //forwards the button event call to the state

    @Override public void setUIUpdateListener(TimerUIUpdateListener listener) {

        //to set a reference to a UIUpdateListener
    }

    @Override
    public void onTick() {

    }
}
