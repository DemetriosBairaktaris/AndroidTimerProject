package group4.android.timerproject.common;

import android.view.View;
import android.widget.EditText;

/**
 * Any class that implements this is a listener
 * to button events.
 * Created by demetribairaktaris on 3/23/16.
 */
public interface TimerUIListener {

    void onButton ();
    void setTime(EditText editText);
}
