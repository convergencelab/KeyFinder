package com.example.smartdrone;

import java.util.Timer;

public class Key {
    /**
     * Index of the key center.
     * Ordered from 'C' to 'B' (ascending).
     * (0 = 'C'; 1 = 'C#'; 2 = 'D'; ...)
     */
    private int _ix;

    /**
     * Name of key center.
     */
    private String _name;

    /**
     * Array containing all note objects corresponding to the key.
     */
    private Note[] _notes;

    /**
     * Strength of key.
     * Strength gets one point for every corresponding note that is active.
     * @deprecated use _keyStrengths in KeyFinder class.
     */
    @Deprecated
    private int _strength;

    /**
     * Timer object that keeps track of the number of seconds a
     * key object has been a contender as the new active key.
     */
    @Deprecated
    private Timer _timer;

    /**
     * TimerTask to set the key as new active key.
     */
    @Deprecated
    private KeyTimerTask _keyTimerTask;

    /**
     * Flag for active key contender.
     * @deprecated use _isContender in KeyFinder class.
     */
    @Deprecated
    private boolean _isContender;

    /**
     * Code represents how the notes should be spelled; sharp or flat.
     * 1 = sharp; 2 = flat.
     */
    private int _spellingCode;

    /**
     * Constructs a container of all the notes in the key based on the key center given.
     * @param       keyCenterIx int; index of the key center.
     * @param       noteCollection NoteCollection; contains every note object.
     */
    public Key(int keyCenterIx, NoteCollection noteCollection) {
        this._ix = keyCenterIx;
        this._name = MusicTheory.CHROMATIC_SCALE_SHARP[this._ix];
        this._notes = new Note[MusicTheory.DIATONIC_SCALE_SIZE];
        this._strength = 0;
        this._isContender = false;
        this._spellingCode = MusicTheory.SPELLING_CODE[keyCenterIx];
        inflateKeyNotes(noteCollection);
    }

    /**
     * Constructs a container of all the notes in the key based on the key center given.
     * @param       keyCenterIx int; index of the key center.
     */
    public Key(int keyCenterIx) {
        this(keyCenterIx, null);
    }

    /**
     * Returns index of key center.
     * @return      int; index of key center.
     */
    public int getIx() {
        return this._ix;
    }

    /**
     * Returns name of key center.
     * @return      String; name of key center.
     */
    public String getName() {
        return this._name;
    }

    /**
     * Return name of note with flat enharmonic spelling.
     * @return      String; name of note.
     */
    public String getNameFlat() {
        return MusicTheory.CHROMATIC_SCALE_FLAT[_ix];
    }

    /**
     * Returns name of name with sharp enharmonic spelling.
     * @return      String; name of note.
     */
    public String getNameSharp() {
        return MusicTheory.CHROMATIC_SCALE_SHARP[_ix];
    }

    /**
     * Return note from key based on scale degree.
     * @param       scaleDegree int; degree of scale. (base zero indexing, where traditional music theory uses base one indexing.)
     * @return      Note; corresponding note to scale degree.
     */
    public Note getDegree(int scaleDegree) {
        return this._notes[scaleDegree];
    }

    /**
     * Returns strength of key.
     * @return      int; strength of key.
     *
     * @deprecated use getStrength() in KeyFinder class instead.
     */
    @Deprecated
    public int getStrength() {
        return _strength;
    }

    /**
     * Returns Timer object.
     * @return      Timer; timer object for key.
     */
    @Deprecated
    public Timer getTimer() {
        return this._timer;
    }

    /**
     * Increase key strength by 1.
     *
     * @deprecated use incrementStrength() in KeyFinder class instead.
     */
    @Deprecated
    public void incrementStrength() {
        this._strength++;
    }

    /**
     * Reduce key strength by 1.
     *
     * @deprecated use decrementStrength() in KeyFinder class instead.
     */
    @Deprecated
    public void decrementStrength() {
        this._strength--;
    }

    /**
     * Starts a background thread to set contender key as new active key.
     * @param       keyFinder KeyFinder; object containing all active notes.
     * @param       seconds int; length of timer.
     */
    @Deprecated
    public void startKeyTimer(KeyFinder keyFinder, int seconds) {
        _timer = new Timer("Key Timer");
        // Schedule timer task for key.
        _keyTimerTask = new KeyTimerTask(keyFinder, this);
        _timer.schedule(_keyTimerTask, seconds * 1000);
    }

    /**
     * Terminates key timer task.
     * Used when key is no longer a contender.
     */
    @Deprecated
    public void cancelKeyTimer() {
        if (_keyTimerTask != null) {
            _keyTimerTask.cancel();
        }
    }

    /**
     * Return contender flag.
     * @return      boolean; true if contender.
     */
    @Deprecated
    public boolean isContender() {
        return _isContender;
    }

    /**
     * Set contender flag.
     * @param       status boolean; true if contender.
     */
    @Deprecated
    public void setIsContender(boolean status) {
        _isContender = status;
    }

    /**
     * Construct and return a string containing all notes found in the key.
     * NOTE: Since a HashSet is used to store note objects, notes may appear unordered.
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

    public int getSpellingCode() {
        return _spellingCode;
    }

    /**
     * Fill key object with corresponding notes.
     * @param       noteCollection NoteCollection; collection of already constructed notes.
     */
    private void inflateKeyNotes(NoteCollection noteCollection) {
        int offset;
        int curNoteIx;
        // Construct from note collection.
        if (noteCollection != null) {
            for (int i = 0; i < MusicTheory.DIATONIC_SCALE_SIZE; i++) {
                offset = MusicTheory.MAJOR_SCALE_SEQUENCE[i];
                curNoteIx = (this._ix + offset) % MusicTheory.TOTAL_NOTES; // TOTAL_NOTES = 12
                _notes[i] = noteCollection.getNoteAtIndex(curNoteIx);
            }
        }
        // Construct only notes needed.
        else {
            for (int i = 0; i < MusicTheory.DIATONIC_SCALE_SIZE; i++) {
                offset = MusicTheory.MAJOR_SCALE_SEQUENCE[i];
                curNoteIx = (this._ix + offset) % MusicTheory.TOTAL_NOTES; // TOTAL_NOTES = 12
                _notes[i] = new Note(curNoteIx);
            }
        }
    }
}