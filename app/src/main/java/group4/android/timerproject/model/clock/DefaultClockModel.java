package group4.android.timerproject.model.clock;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by demetribairaktaris on 3/23/16.
 */
public class DefaultClockModel implements ClockModel, OnTickSource {

        private Timer timer;

        private OnTickListener listener;

        @Override
        public void setOnTickListener(final OnTickListener listener) {
            this.listener = listener;
        }

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

            timer = null;
        }

        @Override
        public void stop() {
            if (timer != null) {
                timer.cancel();
            }
        }
    }
