package com.example.smartdrone;

import java.util.*;

public class Key {
    /**
     * Index of the key center.
     * The number of semitones offset from 'C'.
     * Used for array lookup.
     * (0 = 'C'; 1 = 'C#'; 2 = 'D'; ...)
     */
    private int _ix;

    /**
     * The name of  key center.
     */
    private String _name;

    /**
     * A set that contains all note objects corresponding to the key.
     * Useful for checking if a note is found in the key; O(1) lookup time.
     */
    private Set<Note> _notes;

    /**
     * An int that represents the strength of the key in regards to the active notes list.
     * The keys strength is incremented when one of its notes is added to the list.
     * and reduced when its note is removed from the queue.
     */
    private int _strength;

    /**
     * Timer object that keeps track of the number of seconds a
     * key object has been a contender as the new active key.
     */
    private Timer _timer;

    /**
     * TimerTask to set the key as new active key.
     */
    private KeyTimerTask _keyTimerTask;

    /**
     * Constructs a container of all the notes in the key based on the key center given.
     * @param       keyCenterIx int; index of the key center.
     * @param       allNotes NoteCollection; contains every note object.
     */
    public Key(int keyCenterIx, NoteCollection allNotes) {

        // TODO: Error check for falsely given notes

        // Initialize key center.
        this._ix = keyCenterIx;
        this._name = MusicTheory.CHROMATIC_SCALE[this._ix];
        this._notes = new HashSet<>();
        this._strength = 0;
        // Set up for target note index.
        int offset;
        int curNoteIx;
        // Get each note of the key.
        for (int i = 0; i < MusicTheory.DIATONIC_SCALE_SIZE; i++) {
            offset = MusicTheory.MAJOR_SCALE_SEQUENCE[i];
            curNoteIx = (this._ix + offset) % MusicTheory.TOTAL_NOTES; // TOTAL_NOTES = 12
            // Add note object to set.
            _notes.add(allNotes.getNoteAtIndex(curNoteIx));
        }
    }

    /**
     * Returns the index of the key center.
     * @return      int; index of the key center.
     */
    public int getIx() {
        return this._ix;
    }

    /**
     * Returns the note representing the key center.
     * @return      String; name of key center.
     */
    public String getName() {
        return this._name;
    }

    /**
     * Returns the set of all notes in the key.
     * @return      Set; all note objects in key.
     */
    public Set<Note> getNotes() {
        return this._notes;
    }

    /**
     * Returns an int representing the strength value of the key.
     * @return      int; strength of key.
     */
    public int getStrength() {
        return _strength;
    }

    /**
     * Returns Timer object.
     * @return      Timer; timer object for key.
     */
    public Timer getTimer() {
        return this._timer;
    }

    /**
     * Increase the keys strength by 1.
     */
    public void incrementStrength() {
        this._strength++;
    }

    /**
     * Reduce the keys strength by 1.
     */
    public void decrementStrength() {
        this._strength--;
    }

    /**
     * Starts a background thread to set contender key as new active key.
     * @param       keyFinder KeyFinder; object that contains all active notes.
     * @param       seconds int; length of timer.
     */
    public void startKeyTimer(KeyFinder keyFinder, int seconds) {
        _timer = new Timer();
        // Schedule the monitor timer for the key.
        _keyTimerTask = new KeyTimerTask(keyFinder, this);
        _timer.schedule(_keyTimerTask, seconds * 1000);
    }

    /**
     * Terminates the removal task of Note.
     * Used when key is no longer a contender.
     */
    public void cancelNoteTimer() {
        _keyTimerTask.cancel();
    }

    /**
     * Construct and return a string containing all notes found in the key.
     * NOTE: Since a HashSet is used to store the notes, the notes are unordered.
     * @return      string; *key name* : *notes in key*
     */
    public String toString() {
        String notes = this._name + ": ";
        // Add each note from the scale to the string
        for (Note note : this._notes) {

            notes += note.getName() + " ";
        }
        return notes;
    }
}