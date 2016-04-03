package group4.android.timerproject.common;

/**Any class implementing this will listen
 * to a TimerUIUpdateSource and update the UI accordingly.
 * Created by demetribairaktaris on 3/23/16.
 */
public interface TimerUIUpdateListener {

    void updateTime(int time);

    void updateState(int stateId);
}
