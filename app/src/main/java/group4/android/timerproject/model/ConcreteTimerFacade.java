package group4.android.timerproject.model;

/**
 * Created by demetribairaktaris on 4/6/16.
 */


import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Console;
import java.util.Random;

import group4.android.timerproject.common.TimerUIUpdateListener;
import group4.android.timerproject.model.clock.ClockModel;
import group4.android.timerproject.model.clock.DefaultClockModel;
import group4.android.timerproject.model.state.DefaultTimerStateMachine;
import group4.android.timerproject.model.state.TimerStateMachine;
import group4.android.timerproject.model.time.DefaultTimeModel;
import group4.android.timerproject.model.time.TimeModel;

/**
 * An implementation of the model facade.
 *
 * @author demetribairaktaris
 *
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

    /**
     * Implementation of the onStart() method declared in <<TimerFacade>>
     * Calling this method will initiate the state machine.
     */
    @Override
    public void onStart() {
        stateMachine.actionInit();
    }
    /**
     * Implementation of the onButton() method declared in <<TimerUIListener>>
     * Calling this method will  forward a button event to the state machine.
     */
    @Override
    public void onButton() {
        this.stateMachine.onButton();
    }

    @Override
    public void setTime(EditText editText) {

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(stateMachine.getState() != "Stopped"){return handled ; }
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    if(v.getText().toString().length() > 2 ){
                        return handled ;
                    }
                    else if (v.getText().toString().length() == 0){ return handled; }
                    int timeValue = Integer.parseInt(v.getText().toString());
                    stateMachine.actionSetTime(timeValue);
                    handled = true;
                }
                return handled;
            }
        });
        this.stateMachine.setTime(editText);
    }

    /**
     * Implementation of the setUIUpdateListener() method declared in <<TimerUIUpdateSourcer>>
     * Calling this method will pass a TimerUiUpdateListener to the state machine
     * @param listener the listener object.
     */
    @Override
    public void setUIUpdateListener(TimerUIUpdateListener listener) {
        this.stateMachine.setUIUpdateListener(listener);
    }
}