package ro.pub.cs.systems.eim.practicaltest02;

import android.util.Log;

public class ServerThread extends Thread {
    boolean keepGoing = true;

    public void run() {
        while(keepGoing) {
            // TODO ...
            try {
                sleep(60000);
            } catch (Exception ex) {
                Log.e(Constants.TAG, ex.getMessage())
            }

        }
    }
}
