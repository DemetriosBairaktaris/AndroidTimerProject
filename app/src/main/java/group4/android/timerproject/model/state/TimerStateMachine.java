package group4.android.timerproject.model.state;

import group4.android.timerproject.common.TimerUIListener;
import group4.android.timerproject.common.TimerUIUpdateSource;
import group4.android.timerproject.model.clock.OnTickListener;

/**
 * Created by demetribairaktaris on 3/23/16.
 */

//StopwatchUIListener, OnTickListener, StopwatchUIUpdateSource, StopwatchSMStateVie
public interface TimerStateMachine extends TimerUIListener, OnTickListener, TimerUIUpdateSource, TimerSMStateView {

}
