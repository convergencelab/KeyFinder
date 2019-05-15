package com.example.smartdrone;

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
     * The number of seconds a Note will remain in the
     * KeyFinder List until it is to be automatically removed.
     */
    private int _inactiveThreshold = 4;

    /**
     * The number of semitones offset from 'C'.
     * Used for array lookup.
     * (0 = 'C'; 1 = 'C#'; 2 = 'D'; ...)
     */
    private int _ix;

    /**
     * The letter that represents the name of the note (eg: 'C', 'Gb', 'A#').
     */
    private String _name;

    /**
     * An int representing the octave of the note.
     *   NOTE:: Currently this field is inactive, but may be used in later development.
     */
    private int _octave;

    /**
     * Timer object that keeps track of the number of seconds a
     * note object is in the List since being played.
     */
    private Timer _timer;

    /**
     * TimerTask to remove a note if it becomes inactive;
     * timer reaches the expiration time.
     */
    private NoteTimerTask _noteTimerTask;

    /**
     * Constructs a new Note based on the index; octave is set to -1.
     * @param       noteIx int; the index of the note.
     * @see #_ix
     */
    public Note(int noteIx) {
        this._ix = noteIx;
        this._name = MusicTheory.CHROMATIC_SCALE[noteIx];
        this._octave = -1;
    }

    /**
     * Constructs a new Note based on the index and octave.
     *   NOTE:: Currently this constructor is inactive, but may be used in later development.
     * @param       noteIx int; the index of the note.
     * @param       octave int; the octave of the note.
     * @see #_ix
     */
    public Note(int noteIx, int octave) {
        this._ix = noteIx;
        this._name = MusicTheory.CHROMATIC_SCALE[noteIx];
        this._octave = octave;
    }

    /**
     * Return the index of the note.
     * @return      int; the index of the note.
     */
    public int getIx() {
        return this._ix;
    }

    /**
     * Returns the name of the note.
     * @return      String; the name of the note.
     */
    public String getName() {
        return this._name;
    }

    /**
     * Returns the octave of the note.
     *   NOTE:: Currently this method is inactive, but may be used in later development.
     * @return      int; the octave of the note.
     */
    public int getOctave() {
        return this._octave;
    }

    /**
     * Returns NoteTimer object.
     * @return      Timer; timer object for note.
     */
    public Timer getTimer() {
        return this._timer;
    }

    /**
     * Returns NoteTimerTask object.
     * @return      NoteTimerTask; timertask object for note.
     */
    public NoteTimerTask getNoteTimerTask() {
        return this._noteTimerTask;
    }

    /**
     * Starts a background thread to remove Note object from list after it has become inactive.
     * @param       keyFinder KeyFinder; object that contains all active notes.
     */
    public void startNoteTimer(KeyFinder keyFinder, int seconds) {
        _timer = new Timer();
        // Schedule the removal of note for EXPIRATION_TIME length
        _noteTimerTask = new NoteTimerTask(keyFinder, this);
        _timer.schedule(_noteTimerTask, seconds * 1000);
    }

    /**
     * Terminates the removal task of Note.
     * Used when a Note is played while in List.
     * @see #restartTimer
     */
    public void cancelNoteTimer() {
        _noteTimerTask.cancel();
    }

    /**
     * Restarts the timer on the notes removal task.
     * Used to ensure an active note isn't removed from the List.
     * @param       keyFinder KeyFinder; object that contains all active notes.
     */
    public void restartTimer(KeyFinder keyFinder) {
        cancelNoteTimer();
        startNoteTimer(keyFinder, keyFinder.getNoteTimerLength());
    }

    /**
     * Notes are equal if they share the same offset from 'C'.
     * This is to deal with enharmonic spelling ('C#' == 'Db').
     * This application only creates one object for each note;
     * Key objects contain pointers to these objects.
     * @param       other Object; Note object to compare.
     * @return      true if the notes share the same note index; false otherwise.
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
     * Returns a string representing the name and the octave of the note.
     * @return      String; name of note.
     */
    public String toString() {
        return getName();
    }
}
