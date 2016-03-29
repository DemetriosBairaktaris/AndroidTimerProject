package group4.android.timerproject.model.state;


import group4.android.timerproject.common.TimerUIUpdateListener;

/**
 * Created by demetribairaktaris on 3/23/16.
 */
public class DefaultTimerStateMachine implements TimerStateMachine {

   private TimerState state ;  ///Reference to the current state ;
   private TimerState runningState = new RunningState();
   private TimerState stoppedState = new StoppedState();
   private TimerState incrementState = new IncrementState();
   private TimerState alarmState = new AlarmState();

   public DefaultTimerStateMachine()
   {
       //TODO write constructor with correct parameters;
       //will need to add instance variables

   }

    public void setState(TimerState state)
    {
        this.state = state ;
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

}
