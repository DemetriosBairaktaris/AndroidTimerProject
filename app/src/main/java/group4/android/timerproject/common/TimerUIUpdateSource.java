package group4.android.timerproject.common;

/**Any class implementing this will be a source for UI update events
 * will send events to its UIUpdateListener instance variable
 * Created by demetribairaktaris on 3/23/16.
 */
public interface TimerUIUpdateSource {

    void setUIUpdateListener(TimerUIUpdateListener listener);
}
