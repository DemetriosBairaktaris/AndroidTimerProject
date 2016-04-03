package group4.android.timerproject.model.state;

import group4.android.timerproject.common.TimerUIListener;
import group4.android.timerproject.model.clock.OnTickListener;

/**
 * Created by demetribairaktaris on 3/23/16.
 * Interface for any state the timer can be in.
 * Implementing classes will also be listening to button events and ticks
 */
public interface TimerState extends TimerUIListener, OnTickListener{
        void updateView();
        public String getState();
}
