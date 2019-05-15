package com.example.smartdrone;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * This class uses methods to determine which key has the strongest relationship to the notes being received.
 * It utilizes a LinkedList to store notes that are received. This notes all share an equal weight while in the queue,
 * though they have a timer that removes them from the queue if they have not been played after a certain amount
 * of time.
 * To monitor the relationship of each key, there is an array, _keyStrength, that stores the strength of the key.
 */

public class KeyFinder {
    /**
     * Collection of all notes;
     * ie: Chromatic Scale
     */
    private NoteCollection _allNotes;

    /**
     * A LinkedList that adds newly received notes at the end of the list.
     * The front of the list will be checked to see if the timer has expired.
     * When a new note is received, the list will be scanned to see if it already exists.
     * If it does, than the timer will be reset and the note will be placed at the back of the list.
     */
    private LinkedList<Note> _activeNotes;

    /**
     * KeyCollection contains an array of all 12 keys.
     * Each key contains an array of all notes found in the key,
     * as well as a set of all the note indexes for O(1) lookup time.
     */
    private KeyCollection _keys;

    /**
     * An int that keeps track of the maximum strength of all keys in the _keyStrength array.
     * NOTE: This will not necessarily be the active key.
     */
    private int _maxStrength;

    /**
     * The current key that is being output by the application.
     */
    private Key _activeKey;

    /**
     * Number of seconds an active note can remain in the active note list since it has last been added.
     */
    private int _noteTimerLength;

    private boolean _noteHasBeenRemoved;

    /**
     * Constructs key finder object.
     */
    public KeyFinder() {
        _activeNotes = new LinkedList<>();
        _keys = new KeyCollection();
        _activeKey = null;
        _allNotes = _keys.getAllNotes();
        _noteTimerLength = 5;
        _noteHasBeenRemoved = false;
    }

    /**
     * Get object that contains array of all note objects.
     * @return      NoteCollection; object with all note objects.
     */
    public NoteCollection getAllNotes() {
        return _allNotes;
    }

    /**
     * Returns the List of notes that are currently active.
     * @return      LinkedList; all active notes.
     */
    public LinkedList<Note> getActiveNotes() {
        return this._activeNotes;
    }

    /**
     * Method to add notes to _activeNotes.
     * If not already exists, it will move it to the rear of the list.
     * If it does not, it will add it to the rear of the list.
     * This method has a side effect of incrementing the
     * strength of keys that contain the target note.
     * @param       targetNote Note; note that is to be added to the list.
     */
    public void addNoteToList(Note targetNote) {
        System.out.println("*Adding note: " + targetNote.getName() + "*"); // DEBUG STATEMENT
        // If note in list.
        if (this._activeNotes.contains(targetNote)) {
            // Remove it and add it back.
            // ie: change it to the rear of the list.
            _activeNotes.remove(targetNote);
            targetNote.restartTimer(this);
            _activeNotes.add(targetNote);
        }
        // If note not in list.
        else {
            // Add it to the rear.
            this._activeNotes.add(targetNote);
            // Start timer on note.
            targetNote.startNoteTimer(this, _noteTimerLength);
            // Increment strength of all keys containing this note.
            incrementKeysWithNote(targetNote);
            updateMaxStrength();
            updateActiveKey();
        }
    }

    /**
     * Method that removes notes from _activeNotes.
     * This method has a side effect of decrementing the
     * strength of keys that contain the target note.
     * @param       targetNote Note; note to be removed from LinkedList.
     * @return      true if targetNote in list; false otherwise.
     */
    public boolean removeNoteFromList(Note targetNote) {
        // If note not in _activeNotes.
        if (!_activeNotes.contains(targetNote)) {
            // TODO: should there be an exception thrown here?
            return false;
        }
        // Remove note from _activeNotes.
        else {
            this._activeNotes.remove(targetNote);
            targetNote.cancelNoteTimer();
            _noteHasBeenRemoved = true;
            // Decrement strength of all keys containing this note.
            decrementKeysWithNote(targetNote);
            updateMaxStrength();
            updateActiveKey();
            return true;
        }
    }

    /**
     * Returns key collection object.
     * @return      KeyCollection; object containing all keys.
     */
    public KeyCollection getAllKeys() {
        return this._keys;
    }

    /**
     * Increment the strength of all keys that contain the target note by 1.
     * @param       target Note; object that has entered List.
     */
    public void incrementKeysWithNote(Note target) {
        // Offset will represent the difference in semitones
        // away from the next key that contains the target note.
        // ie: next key.
        int offset;
        // Index of target note.
        int noteIx = target.getIx();
        // For each key that contains note.
        for (int i = 0; i < MusicTheory.DIATONIC_SCALE_SIZE; i++) { // DIATONIC_SCALE_SIZE = 7
            offset = MusicTheory.PHRYGIAN_SCALE_SEQUENCE[i];
            // Increment keys strength.
            this._keys.getMajorKeyAtIndex(
                    (noteIx + offset) % MusicTheory.TOTAL_NOTES).incrementStrength(); // TOTAL_NOTES = 12
        }
    }

    /**
     * Decrement the strength of all keys that contain the target note by 1.
     * @param       target Note; object that has exited List.
     */
    public void decrementKeysWithNote(Note target) {
        // Offset will represent the difference in semitones
        // away from the next key that contains the target note.
        // ie: next key.
        int offset;
        int noteIx = target.getIx();
        // For each key that contains note.
        for (int i = 0; i < MusicTheory.DIATONIC_SCALE_SIZE; i++) { // DIATONIC_SCALE_SIZE = 7
            offset = MusicTheory.PHRYGIAN_SCALE_SEQUENCE[i];
            // Decrement keys strength.
            this._keys.getMajorKeyAtIndex(
                    (noteIx + offset) % MusicTheory.TOTAL_NOTES).decrementStrength(); // TOTAL_NOTES = 12
        }
    }

    /**
     * Method that takes a list of keys and returns one at random.
     * Used when there are multiple keys that share the same strength.
     * @param       keyList List; of Key objects.
     * @return      Key; the winning key.
     */
    public Key getRandomKey(List<Key> keyList) {
        // Get random number between (0 and num of keys with max strength -1; inclusive)
        int randomIx = new Random().nextInt(keyList.size());
        return keyList.get(randomIx);
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
            curKey = this._keys.getMajorKeyAtIndex(i);
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
     * Returns the integer that represents the strength of the active key.
     * NOTE: The active keys strength is not necessarily the max strength.
     * @return      int; active keys strength.
     */
    public int getActiveKeyStrength() {
        return this._keys.getMajorKeyAtIndex(_activeKey.getIx()).getStrength();
    }

    /**
     * Check whether the active key has max strength.
     * @return      true if active key has max strength; false otherwise.
     */
    public boolean activeKeyHasMaxStrength() {
        return getActiveKeyStrength() == getMaxStrength();
    }

    /**
     * Method to determine what should be the active key.
     */
    public void updateActiveKey() {
        // No active notes in List.
        if (this._activeNotes.isEmpty()) {
            this._activeKey = null;
            return;
        }
        // If the active key still has max strength, return it.
        // The active key therefore takes precedence over other keys,
        // even if they all share max strength.
        if (getActiveKey() != null && activeKeyHasMaxStrength()) {
            return;
        }
        else {
            // Pick a random key that has max strength.
            this._activeKey = getRandomKey(getKeysWithMaxStrength());
        }
    }

    public void run() {
        Note curNote;
        int userNoteIx = Test.promptUserForNoteIndex();
        while (userNoteIx != -1) {
            System.out.println();
            // Construct Note.
            curNote = this.getAllNotes().getNoteAtIndex(userNoteIx);
            // Add to list.
            this.addNoteToList(curNote);
            // Print out all the keys strength.
            Test.pickActiveKeyTestPrintInfo(this);
            // Prompt user again
            System.out.println();
            userNoteIx = Test.promptUserForNoteIndex();
        }
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
    public boolean getNoteHasBeenRemoved() {
        return _noteHasBeenRemoved;
    }


    /**
     * Flag for when a note is removed from the active note list.
     * @return      boolean; true if note has been removed.
     */
    public void setNoteHasBeenRemoved(boolean bool) {
        _noteHasBeenRemoved = bool;
    }
}
