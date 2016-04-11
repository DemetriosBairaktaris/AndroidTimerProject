package group4.android.timerproject.model.clock;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The default clock model that sends out tick events to its OnTickListener.
 * Created by demetribairaktaris on 3/23/16.
 */
public class DefaultClockModel implements ClockModel, OnTickSource {

        private Timer timer;

        private OnTickListener listener;

    /**
     * Sets the OnTickListener that will listen to this objects tick events.
     * @param listener listens to tick events
     */
        @Override
        public void setOnTickListener(final OnTickListener listener) {
            this.listener = listener;
        }

    /**
     * Simple method to start the clock and emit tick event every 1 second.
     */
        @Override
        public void start() {
            timer = new Timer();

            // The clock model runs onTick every 1000 milliseconds
            timer.schedule(new TimerTask() {
                @Override public void run() {
                    // fire event
                    listener.onTick();
                }
            }, /*initial delay*/ 1000, /*periodic delay*/ 1000);


        }

    /**
     * Stops the clock and emits no tick events
     */
        @Override
        public void stop() {
            try{timer.cancel();}catch(Exception e){}//the golden ticket
        }
    }
