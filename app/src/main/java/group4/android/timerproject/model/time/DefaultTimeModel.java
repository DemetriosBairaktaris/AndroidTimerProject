package group4.android.timerproject.model.time;

/**
 * Created by demetribairaktaris on 3/23/16.
 *
 * manages what is on the display
 */
public class DefaultTimeModel implements TimeModel {

    private int runTime = 0 ;


    @Override
    public void resetRuntime() {
        this.runTime = 0 ;
    }

    @Override
    public void incRuntime() {
        this.runTime += 1 ;
    }

    @Override
    public void decRuntime() {
        this.runTime -= 1;
    }

    @Override
    public int getRuntime() {
        return this.runTime;
    }
    @Override
    public void setRunTime(int runTime){
        this.runTime = runTime ;
    }
}
