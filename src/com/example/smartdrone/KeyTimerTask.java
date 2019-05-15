package com.example.smartdrone;

import java.util.TimerTask;
import java.util.Timer;

/**
 * Timer used to determine key changes from the user.
 * For a key to become the new active key it must:
 *   1. Have a greater strength than the current active key for VARIABLE amount of time.
 *   2. Have max strength (can be same as other inactive keys) for VARIABLE amount of time.
 */
public class KeyTimerTask extends TimerTask {
    private KeyFinder _keyFinder;
    private Key _toMonitor;
    private Timer _timer;

    public KeyTimerTask(KeyFinder keyFinder, Key toMonitor) {
        _keyFinder = keyFinder;
        _toMonitor = toMonitor;
        _timer = toMonitor.getTimer();
    }
    @Override
    public void run() {
        _keyFinder.setActiveKey(_toMonitor);
        _toMonitor.setIsContender(false);
        _keyFinder.cancelAllKeyTimersExcept(_toMonitor);
    }
}


// TODO: 1) edge case; active key is null. 2) test to see if works.