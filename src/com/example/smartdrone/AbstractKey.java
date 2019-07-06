package com.example.smartdrone;

//todo refactor name later to just 'Key'
public abstract class AbstractKey {

    //todo check if it should be protected
    protected abstract void inflateKeyNotes(NoteCollection noteCollection);

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
    protected Note[] _notes;

    /**
     * Strength of key.
     * Strength gets one point for every corresponding note that is active.
     */
    private int _strength;

    /**
     * Flag for active key contender.
     */
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
    public AbstractKey(int keyCenterIx, NoteCollection noteCollection) {
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
    public AbstractKey(int keyCenterIx) {
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
     */
    public int getStrength() {
        return _strength;
    }

    /**
     * Increase key strength by 1.
     */
    public void incrementStrength() {
        this._strength++;
    }

    /**
     * Reduce key strength by 1.
     */
    public void decrementStrength() {
        this._strength--;
    }

    /**
     * Return contender flag.
     * @return      boolean; true if contender.
     */
    public boolean isContender() {
        return _isContender;
    }

    /**
     * Set contender flag.
     * @param       status boolean; true if contender.
     */
    public void setContenderStatus(boolean status) {
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
     * Returns parent intervals for key.
     * Check MelodicMinorKey or MajorKey classes for implementations.
     * @return
     */
    public abstract int[] getParentIntervals();
}
