package com.example.smartdrone;

import java.util.TimerTask;
import java.util.Timer;

/**
 * Class for removing notes that have become inactive.
 * Notes that are in the List of active notes can remain in there so
 * long without being played before they are removed from the list.
 */
class NoteTimerTask extends TimerTask {
    private KeyFinder _keyFinder;
    private Note _toRemove;
    private Timer _timer;

    public NoteTimerTask(KeyFinder keyFinder, Note toRemove) {
        _keyFinder = keyFinder;
        _toRemove = toRemove;
        _timer = toRemove.getTimer();
    }
    @Override
    public void run() {
        _keyFinder.removeNoteFromList(_toRemove);
        System.out.println("\n*Removed Expired Note: " + _toRemove.getName() + "*");   // DEBUG STATEMENT
        Test.pickActiveKeyTestPrintInfo(_keyFinder);                                   // DEBUG STATEMENT
        _timer.cancel();
    }

}