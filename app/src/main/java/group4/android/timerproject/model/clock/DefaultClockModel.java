package group4.android.timerproject.model.clock;

import java.util.Timer;

/**
 * Created by demetribairaktaris on 3/23/16.
 */
public class DefaultClockModel implements ClockModel, OnTickSource {
    Timer timer ;
    OnTickListener listener ;

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void setOnTickListener(OnTickListener listener) {
        this.listener = listener ;
    }

}
