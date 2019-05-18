package com.example.smartdrone;

/**
 * KeyCollection is a class that constructs and contains all 12 major key objects.
 * Future development will include other tonalities (melodic minor, harmonic minor, harmonic major)
 */
public class KeyCollection {

    /**
     * All 12 major key objects.
     * Each object is a set that contains all notes found in the corresponding major scale.
     * The array is ordered from 'C' through 'B' (ascending).
     */
    private Key[] _majorKeys = new Key[12];

    /**
     * All 12 note objects.
     * Ordered from 'C' through 'B' (ascending).
     */
    private NoteCollection _allNotes;

    /**
     * Constructor.
     * Constructs all 12 key objects and all 12 note objects.
     */
    public KeyCollection() {
        _allNotes = new NoteCollection();
        // For each key.
        for (int i = 0; i < MusicTheory.TOTAL_NOTES; i++) { // TOTAL_NOTES = 12
            // Construct key.
            _majorKeys[i] = new Key(i, _allNotes);
        }
    }

    /**
     * Returns key corresponding to the index given.
     * @param       targetKeyIx int; index of target key.
     * @return      Key; corresponding key.
     */
    public Key getMajorKeyAtIndex(int targetKeyIx) {
        return _majorKeys[targetKeyIx];
    }

    /**
     * Return note collection object.
     * @return      NoteCollection; note collection object.
     */
    public NoteCollection getAllNotes() {
        return this._allNotes;
    }
}
