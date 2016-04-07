package group4.android.timerproject.model.time;

/**
 * Created by demetribairaktaris on 3/23/16.
 */
public interface TimeModel {


    void resetRuntime();
    void incRuntime();
    void decRuntime();
    int getRuntime();
    void setRunTime(int runTime);
}

