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
    @Override public void toRunningState() {
        clock.stop();
        playDefaultNotification();
        this.setState(this.runningState);
        clock.start();

    }
    @Override public synchronized void toStoppedState() {
        this.clock.stop();
        this.setState(this.stoppedState);}
    @Override public synchronized void toIncrementState() {this.setState(this.incrementState);}
    @Override public synchronized void toAlarmState() {this.setState(this.alarmState);}
    //Actions
    @Override public synchronized void actionInit() {toStoppedState(); actionReset();}
    @Override public synchronized void actionReset() {time.resetRuntime(); actionUpdateView();}
    @Override public synchronized void actionStart() {clock.start();}
    @Override public synchronized void actionStop() {clock.stop();}
    @Override public synchronized void actionInc() {
        if(time.getRuntime() < 99) {
            actionStop();
            time.incRuntime();
            actionUpdateView();
            if (time.getRuntime()==99){toRunningState();return;}
            clock.start();
        }
    }
    @Override public synchronized void actionDec() {

       // if (time.getRuntime() != 0) {
            time.decRuntime();
            actionUpdateView();
        //}

    }
    @Override public synchronized void actionUpdateView() {state.updateView();}

    @Override
    public void playDefaultNotification() {
        UIUpdateListener.playDefaultNotification();
    }


    @Override public synchronized void updateUIRuntime() {
        UIUpdateListener.updateTime(time.getRuntime());
    }

    @Override public synchronized void onButton() {
        state.onButton();
    }

    private TimerUIUpdateListener UIUpdateListener;
    @Override public void setUIUpdateListener(TimerUIUpdateListener listener) {
        UIUpdateListener = listener;
    }

    @Override
    public synchronized void onTick() {
        this.state.onTick();
    }
}
