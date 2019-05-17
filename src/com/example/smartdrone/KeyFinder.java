package com.example.smartdrone;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Determine which key has the strongest relationship to the notes being received.
 * Notes are added to a list when received, and removed from the when they have become inactive.
 * The active key is the key with the strongest strength. If more than one key has max strength then
 * the active key will be chosen at random.
 */
public class KeyFinder {
    /**
     * Collection of all notes;
     * ie: Chromatic Scale
     */
    private NoteCollection _allNotes;

    /**
     * A list of all active notes.
     * Active notes affect key strength.
     */
    private LinkedList<Note> _activeNotes;

    /**
     * Object that contains all key objects.
     */
    private KeyCollection _allKeys;

    /**
     * The max strength among all keys.
     * Not necessarily the active key.
     */
    private int _maxStrength;

    /**
     * The key that has the strongest correlation with the active notes.
     */
    private Key _activeKey;

    /**
     * Number of seconds for the note timer task.
     */
    private int _noteTimerLength;

    /**
     * Flag for a note being removed the active note list.
     */
    private boolean _noteHasBeenRemoved;

    /**
     * The last note that was removed.
     */
    private Note _removedNote;

    /**
     * Number of seconds for the key timer task.
     */
    private int _keyTimerLength;

    /**
     * Flag for a checking if the active key has changed.
     */
    private boolean _activeKeyHasBeenUpdated;

            //TODO: if working, then delete.
//    /**
//     * List of inactive keys that are in the running for the new active key.
//     */
//    private LinkedList<Key> _contenderKeys;

    /**
     * Constructor.
     */
    public KeyFinder() {
        _activeNotes = new LinkedList<>();
        _allKeys = new KeyCollection();
        _activeKey = null;
        _allNotes = _allKeys.getAllNotes();
        _noteTimerLength = 2;
        _keyTimerLength = 2;
        _noteHasBeenRemoved = false;
        _activeKeyHasBeenUpdated = false;
    }

    /**
     * Get object that contains all note objects.
     * @return      NoteCollection; object with all note objects.
     */
    public NoteCollection getAllNotes() {
        return _allNotes;
    }

    /**
     * Returns the List of active notes.
     * @return      LinkedList; all active notes.
     */
    public LinkedList<Note> getActiveNotes() {
        return this._activeNotes;
    }

    /**
     * Add notes to active notes.
     * Updates key strengths.
     * @param       targetNote Note; note to be added to active notes.
     */
    public void addNoteToList(Note targetNote) {
        // System.out.println("*Adding note: " + targetNote.getName() + "*"); // DEBUG STATEMENT
        // If note in list.
        if (activeNotesContain(targetNote)) {
            // Do nothing.
            // App restarts timer.
            return;
        }
        // If note not in list.
        else {
            // Add to list.
            this._activeNotes.add(targetNote);
            incrementKeysWithNote(targetNote);
            updateMaxStrength();
            updateContenderKeys();
        }
    }

    /**
     * Removes notes from active notes.
     * Updates key strengths.
     * @param       targetNote Note; note to be removed from active notes.
     */
    public void removeNoteFromList(Note targetNote) {
        // If note not in _activeNotes.
        if (!activeNotesContain(targetNote)) {
            return;
        }
        // Remove note from _activeNotes.
        else {
            this._activeNotes.remove(targetNote);
            targetNote.cancelNoteTimer();
            // Flag note removed. Used for debugging.
            _noteHasBeenRemoved = true;
            _removedNote = targetNote;
            // Decrement strength of all keys containing this note.
            decrementKeysWithNote(targetNote);
            updateMaxStrength();
            updateContenderKeys();
        }
    }

    /**
     * Check if active notes list contains target note.
     * @param       targetNote Note; note to check.
     * @return      boolean; true if active notes contains target note.
     */
    private boolean activeNotesContain(Note targetNote) {
        return this._activeNotes.contains(targetNote);
    }

    /**
     * Returns key collection object.
     * @return      KeyCollection; object containing all keys.
     */
    public KeyCollection getAllKeys() {
        return this._allKeys;
    }

    /**
     * Increment strength of all keys containing target note.
     * @param       target Note; target note.
     */
    private void incrementKeysWithNote(Note target) {
        int curKeyOffset;
        int targetNoteIx = target.getIx();
        // For each key that contains note.
        for (int i = 0; i < MusicTheory.DIATONIC_SCALE_SIZE; i++) { // DIATONIC_SCALE_SIZE = 7
            curKeyOffset = MusicTheory.PHRYGIAN_SCALE_SEQUENCE[i];
            // Increment keys strength.
            _allKeys.getMajorKeyAtIndex(
                    (targetNoteIx + curKeyOffset) % MusicTheory.TOTAL_NOTES).incrementStrength(); // TOTAL_NOTES = 12
        }
    }

    /**
     * Decrement strength of all keys containing target note.
     * @param       target Note; target note.
     */
    private void decrementKeysWithNote(Note target) {
        int curKeyOffset;
        int targetNoteIx = target.getIx();
        // For each key that contains note.
        for (int i = 0; i < MusicTheory.DIATONIC_SCALE_SIZE; i++) { // DIATONIC_SCALE_SIZE = 7
            curKeyOffset = MusicTheory.PHRYGIAN_SCALE_SEQUENCE[i];
            // Decrement keys strength.
            _allKeys.getMajorKeyAtIndex(
                    (targetNoteIx + curKeyOffset) % MusicTheory.TOTAL_NOTES).decrementStrength(); // TOTAL_NOTES = 12
        }
    }

    /**
     * Return the largest integer in the key strength array.
     * @return      int representing the maximum strength from _keyStrength.
     */
    public int getMaxStrength() {
        return _maxStrength;
    }

    /**
     * Linear search through key strength;
     * return the largest strength.
     * MIN = 0; MAX = 7.
     * @return      int; max strength among all keys.
     */
    private int findMaxStrength() {
        int max = 0;
        Key curKey;
        // For each int in array.
        for (int i = 0; i < MusicTheory.TOTAL_NOTES; i++) {
            curKey = this._allKeys.getMajorKeyAtIndex(i);
            // If it's greater than current max.
            if (curKey.getStrength() > max) {
                // Max becomes current keys strength.
                max = curKey.getStrength();
            }
        }
        return max;
    }

    /**
     * Sets the max strength to the highest value;
     * finds highest value by calling findMaxStrength function.
     * @see #findMaxStrength()
     */
    private void updateMaxStrength() {
        _maxStrength = findMaxStrength();
    }

    /**
     * Returns a list with all keys that have max strength.
     * @return      ArrayList of Key objects.
     */
    public List<Key> getKeysWithMaxStrength() {
        return getAllKeys().getKeysWithStrength(getMaxStrength());
    }

    /**
     * Returns the index of the key that is actively being played.
     * NOTE: the active key isn't necessarily the strongest key.
     */
    public Key getActiveKey() {
        return this._activeKey;
    }

    /**
     * Sets the active key field to the key given.
     * @param       newActiveKey Key; new active key.
     */
    public void setActiveKey(Key newActiveKey) {
        this._activeKey = newActiveKey;
    }

    /**
     * Get active keys strength.
     * No active key returns 0.
     * @return      int; active keys strength.
     */
    private int getActiveKeyStrength() {
        if (_activeKey == null) {
            return 0;
        }
        return _allKeys.getMajorKeyAtIndex(_activeKey.getIx()).getStrength();
    }

    /**
     * Monitors keys that are in contention for becoming the new active key.
     */
    private void updateContenderKeys() {
        Key curKey;
        // For each key.
        for (int i = 0; i < MusicTheory.TOTAL_NOTES; i++) {
            curKey = getAllKeys().getMajorKeyAtIndex(i);
            // Don't check active key.
            if (curKey != _activeKey) {
                // There are 4 states an inactive key can be in,
                // however, only two require action:

                // 1. Key is contender and doesn't meet the requirements.
                if (curKey.isContender() && !meetsContenderRequirements(curKey)) {
                    // Cancel timer.
                    curKey.cancelKeyTimer();
                    // Not a contender.
                    curKey.setIsContender(false);
                }
                // 2. Key is not a contender and meets the requirements.
                else if (!curKey.isContender() && meetsContenderRequirements(curKey)) {
                    // Start timer.
                    curKey.startKeyTimer(this, _keyTimerLength);
                    // Is a contender.
                    curKey.setIsContender(true);
                }
            }
        }
    }

    /**
     * An inactive key is a contender if it meets two requirements:
     *   1. Key has greater strength than the current active key.
     *   2. Key has max strength (can be same as other inactive keys).
     * @param       curKey Key; key to be monitored.
     * @return      boolean; true if key meets requirements.
     */
    private boolean meetsContenderRequirements(Key curKey) {
        return curKey.getStrength() > getActiveKeyStrength() && curKey.getStrength() == _maxStrength;
    }

    /**
     * Return the number of seconds an active note can remain in the list.
     * @return      int; noteTimerLength
     */
    public int getNoteTimerLength() {
        return _noteTimerLength;
    }

    /**
     * Set the number of seconds an active note can remain in the list.
     * @param       seconds int; number of seconds.
     */
    public void setNoteTimerLength(int seconds) {
        _noteTimerLength = seconds;
    }

    /**
     * Flag for when a note is removed from the active note list.
     * @return      boolean; true if note has been removed.
     */
    public boolean noteHasBeenRemoved() {
        return _noteHasBeenRemoved;
    }

    /**
     * Reset the flag for note being removed.
     */
    public void resetNoteHasBeenRemoved() {
        _noteHasBeenRemoved = false;
    }

    /**
     * Get the last note that has been removed from active note list.
     * @return      Note; last note removed from active note list.
     */
    public Note getRemovedNote() {
        return _removedNote;
    }
    
    public void setKeyTimerLength(int seconds) {
        _keyTimerLength = seconds;
    }
    
    public boolean activeKeyHasBeenUpdated() {
        return _activeKeyHasBeenUpdated;
    }
    
    public void resetKeyHasBeenChanged() {
        _activeKeyHasBeenUpdated = false;
    }

    public int getKeyTimerLength() {
        return _keyTimerLength;
    }

    /**
     * UNUSED FUNCTION (I THINK)
     * Flag for when a note is removed from the active note list.
     * @return      boolean; true if note has been removed.
     */
    public void setNoteHasBeenRemoved(boolean bool) {
        _noteHasBeenRemoved = bool;
    }

    public void cancelAllKeyTimersExcept(Key ignoreKey) {
        Key curKey;
        for (int i = 0; i < MusicTheory.TOTAL_NOTES; i++) {
            curKey = getAllKeys().getMajorKeyAtIndex(i);
            if (curKey.isContender()) {
                curKey.cancelKeyTimer();
                curKey.setIsContender(false);
            }
        }
    }
}
