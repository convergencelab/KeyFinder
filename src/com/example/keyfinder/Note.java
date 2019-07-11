package com.example.keyfinder;

import java.util.Objects;
import java.util.Timer;

/**
 * The Note class contains information regarding the name, octave, and index of the note.
 * The index of the note is a way of representing notes with integers.
 * For example: 0 = C; 1 = C#/Db; 2 = D; ...
 * The index can subsequently be viewed as the offset from C.
 * Note objects also have a Timer field. The timer starts a task to remove the note after
 * it has been inactive (not played) after a specified amount of time.
 * The task is activated any time they are added (played by the user) to the list of active notes.
 */
public class Note {
    /**
     * The number of semitones offset from 'C'.
     * Ordered from 'C' to 'B' (ascending).
     * (0 = 'C'; 1 = 'C#'; 2 = 'D'; ...)
     */
    private int _ix;

    /**
     * Name of note.
     */
    private String _name;

    /**
     * Octave of note.
     *   NOTE:: Currently this field is inactive, but may be used in later development.
     */
    private int _octave;

    /**
     * Timer object that keeps track of the seconds
     * since an active note has been played.
     */
    @Deprecated
    private Timer _timer;

    /**
     * TimerTask to remove a note if it becomes inactive;
     * timer reaches the expiration time.
     */
    @Deprecated
    private NoteTimerTask _noteTimerTask;

    /**
     * Constructs new note based on the index given; octave is set to -1.
     * @param       noteIx int; index of the note.
     * @see #_ix
     */
    public Note(int noteIx) {
        this._ix = noteIx;
        this._name = MusicTheory.CHROMATIC_SCALE_SHARP[getIx()];
        this._octave = noteIx / MusicTheory.TOTAL_NOTES;
    }

    /**
     * Constructs new note based on the index and octave given.
     * @param       noteIx int; index of note.
     * @param       octave int; octave of note.
     * @see #_ix
     */
    public Note(int noteIx, int octave) {
        this._ix = noteIx;
        this._name = MusicTheory.CHROMATIC_SCALE_SHARP[getIx()];
        this._octave = octave;
    }

    /**
     * Returns index of note.
     * No greater than 12.
     * @return      int; index of note.
     */
    public int getIx() {
        return this._ix % 12;
    }

    /**
     * Returns raw index of note.
     * Can be greater than 12.
     * @return      int; raw index of note.
     */
    public int getRawIx() {
        return this._ix;
    }

    /**
     * Returns name of note; defaults to using sharp spelling.
     * @return      String; name of note.
     */
    public String getName() {
        return this._name;
    }

    /**
     * Returns name of note with spelling based on code given.
     * @param       spellingCode int; note spelling code.
     * @return      String; name of note.
     */
    public String getName(int spellingCode) {
        if (spellingCode == MusicTheory.SHARP_SPELLING_CODE) {
            return getNameSharp();
        }
        else {
            return getNameFlat();
        }
    }

    /**
     * Return name of note with flat enharmonic spelling.
     * @return      String; name of note.
     */
    public String getNameFlat() {
        return MusicTheory.CHROMATIC_SCALE_FLAT[this.getIx()];
    }

    /**
     * Returns name of name with sharp enharmonic spelling.
     * @return      String; name of note.
     */
    public String getNameSharp() {
        return MusicTheory.CHROMATIC_SCALE_SHARP[this.getIx()];
    }

    /**
     * Returns octave of note.
     * @return      int; octave of note.
     */
    public int getOctave() {
        return this._octave;
    }

    /**
     * Returns Timer object.
     * @return      Timer; timer object for note.
     */
    @Deprecated
    public Timer getTimer() {
        return this._timer;
    }

    /**
     * Starts a background thread to remove Note object from list after it has become inactive.
     * @param       keyFinder KeyFinder; object containing all active notes.
     * @param       seconds int; length of timer.
     */
    @Deprecated
    public void startNoteTimer(KeyFinder keyFinder, int seconds) {
        // If timer task exists.
        if (_noteTimerTask != null) {
            _noteTimerTask.cancel();
        }
        _timer = new Timer("Note Timer"); //TODO see if this can be moved
        // Schedule the removal of note for EXPIRATION_TIME length
        _noteTimerTask = new NoteTimerTask(keyFinder, this);
        _timer.schedule(_noteTimerTask, seconds * 1000);
    }

    /**
     * Terminates the removal task of Note.
     * Used when a Note is played while in List.
     */
    @Deprecated
    public void cancelNoteTimer() {
        // If timer task exists.
        if (_noteTimerTask != null) {
            _noteTimerTask.cancel();
        }
    }

    /**
     * Notes are equal if they share the same ix.
     * This is to deal with enharmonic spelling ('C#' == 'Db').
     * This application only creates one object for each note;
     * Key objects contain pointers to these objects.
     * @param       other Object; Note object to compare.
     * @return      boolean; true if notes share the same note index.
     */
    @Override
    public boolean equals(Object other) {
        // Identity check.
        if (this == other) {
            return true;
        }
        // Null check.
        if (other == null) {
            return false;
        }
        // Is instance of
        if (other instanceof Note) {
            Note test = (Note) other;
            return test.getIx() == this.getIx();
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this._ix);
        return hash;
    }

    /**
     * Returns a string with name of note.
     * @return      String; name of note.
     */
    public String toString() {
        return _name + " " + getRawIx();
    }
}