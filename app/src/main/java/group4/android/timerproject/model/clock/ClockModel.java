package group4.android.timerproject.model.clock;

/**
 *
 * Clock Model interface.
 * Created by demetribairaktaris on 3/23/16.
 *
 */
public interface ClockModel extends OnTickSource {
    void start() ;
    void stop() ;
}
