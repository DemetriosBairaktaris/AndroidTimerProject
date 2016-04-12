package group4.android.timerproject.model.state;



import android.widget.EditText;

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

   public DefaultTimerStateMachine(TimeModel t, ClockModel c) {
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

    /**
     * Returns the current runtime
     * @return current runtime
     */
    @Override public int getTime() { return this.time.getRuntime();}

   //Transitions

    /**
     * transtions the timer to the running state
     */
    @Override public void toRunningState() {
        clock.stop();
        playDefaultNotification();
        this.setState(this.runningState);
        clock.start();
    }

    /**
     * transitions the timer to the stopped state
     */
    @Override public synchronized void toStoppedState() {
        this.clock.stop();
        this.setState(this.stoppedState);}

    /**
     * transitons timer to the increment state
     */
    @Override public synchronized void toIncrementState() {this.setState(this.incrementState);}

    /**
     * transtions timer to the alarm state
     */
    @Override public synchronized void toAlarmState() {this.setState(this.alarmState);}


    //Actions

    /**
     * Initializes the model to stopped state .
     */
    @Override public synchronized void actionInit() {toStoppedState(); actionReset();}

    /**
     * resets the runtime and updates the view to 00
     */
    @Override public synchronized void actionReset() {time.resetRuntime(); actionUpdateView();}

    /**
     * starts the underlying clock
     */
    @Override public synchronized void actionStart() {clock.start();}

    /**
     * stops the underlying clock
     */
    @Override public synchronized void actionStop() {clock.stop();}

    /**
     * Increments run time and displays it to the screen.
     * Will increment to a max of 99
     */
    @Override public synchronized void actionInc() {
        if(time.getRuntime() < 99) {
            actionStop();
            time.incRuntime();
            actionUpdateView();
            if (time.getRuntime()==99){toRunningState();return;}
            clock.start();
        }
    }
    @Override
    public synchronized void actionSetTime(int timeValue){
        time.setRunTime(timeValue);
        actionUpdateView();
        toRunningState();
    }

    /**
     * decrements the runtime and updates the screen
     */
    @Override public synchronized void actionDec() {

       // if (time.getRuntime() != 0) {
            time.decRuntime();
            actionUpdateView();
        //}

    }

    @Override public synchronized void actionUpdateView() {state.updateView();}

    /**
     * plays the default ringtone notification on devices
     */
    @Override
    public void playDefaultNotification() {
        UIUpdateListener.playDefaultNotification();
    }

    /**
     * updates the UI with current runtime
     */
    @Override public synchronized void updateUIRuntime() {
        UIUpdateListener.updateTime(time.getRuntime());
    }

    /**
     * forwards button event to the current state
     */
    @Override public synchronized void onButton() {
        state.onButton();
    }

    @Override
    public void setTime(EditText editText) {
        state.setTime(editText);
    }

    private TimerUIUpdateListener UIUpdateListener;
    /**
     * Sets the TimerUIUpdateListener for the state machine.
     * @param listener the listener for UIUpdates
     */
    @Override public void setUIUpdateListener(TimerUIUpdateListener listener) {
        UIUpdateListener = listener;
    }

    /**
     * Tick event gets forwarded to the current state.
     */
    @Override
    public synchronized void onTick() {
        this.state.onTick();
    }

}
