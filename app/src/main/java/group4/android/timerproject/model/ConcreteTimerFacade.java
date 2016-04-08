package group4.android.timerproject.model;

/**
 * Created by demetribairaktaris on 4/6/16.
 */


import group4.android.timerproject.common.TimerUIUpdateListener;
import group4.android.timerproject.model.clock.ClockModel;
import group4.android.timerproject.model.clock.DefaultClockModel;
import group4.android.timerproject.model.state.DefaultTimerStateMachine;
import group4.android.timerproject.model.state.TimerStateMachine;
import group4.android.timerproject.model.time.DefaultTimeModel;
import group4.android.timerproject.model.time.TimeModel;

/**
 * An implementation of the model facade.

 * @author demetribairaktaris
 */
public class ConcreteTimerFacade implements TimerFacade {

    private TimerStateMachine stateMachine;

    private ClockModel clockModel;

    private TimeModel timeModel;

    public ConcreteTimerFacade() {
        timeModel = new DefaultTimeModel();
        clockModel = new DefaultClockModel();
        stateMachine = new DefaultTimerStateMachine(timeModel, clockModel);
        clockModel.setOnTickListener(stateMachine);
    }


    @Override
    public void onStart() {
        stateMachine.actionInit();
    }

    @Override
    public void onButton() {
        this.stateMachine.onButton();
    }

    @Override
    public void setUIUpdateListener(TimerUIUpdateListener listener) {
        this.stateMachine.setUIUpdateListener(listener);
    }
}