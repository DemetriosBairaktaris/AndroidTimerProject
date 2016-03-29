package group4.android.timerproject.model;


import group4.android.timerproject.common.TimerUIListener;
import group4.android.timerproject.common.TimerUIUpdateSource;

/**ConcreteTimerFacade will implement this.
 * Is what the client needs to control the model.
 * Along with being a TimerFacade, any class implementing this will
 * also be a listener button events a source for UIUpdates.
 * Created by demetribairaktaris on 3/23/16.
 */
public interface TimerFacade extends TimerUIListener, TimerUIUpdateSource{
    void onStart();
}
