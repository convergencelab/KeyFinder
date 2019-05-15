package com.example.smartdrone;

import java.util.*;

/**
 * KeyCollection is a class that constructs all 12 major scales and stores them in an array.
 * Future development will include other tonalities (melodic minor, harmonic minor, harmonic major)
 */
public class KeyCollection {

    /**
     * An array containing all 12 major key objects.
     * Each object is a set that contains all notes found in the corresponding major scale.
     * The array is ordered from 'C' through 'B' (ascending).
     */
    private Key[] _majorKeys = new Key[12];

    /**
     * Note collection is an object that contains all 12 notes;
     * contains an array that has notes ordered with proper indexes.
     */
    private NoteCollection _allNotes;

    /**
     * Constructs the _majorKeys array.
     * Creates key object for each index in array, ordered from 'C' to 'B' (ascending).
     */
    public KeyCollection() {
        _allNotes = new NoteCollection();
        for (int i = 0; i < MusicTheory.TOTAL_NOTES; i++) { // TOTAL_NOTES = 12
            _majorKeys[i] = new Key(i, _allNotes);
        }
    }

    /**
     * Returns the key corresponding to the index given.
     * @param       targetKeyIx int; index of the target key.
     * @return      Key; corresponding to index given.
     */
    public Key getMajorKeyAtIndex(int targetKeyIx) {
        return _majorKeys[targetKeyIx];
    }

    /**
     * //TODO move this so that KeyFinder creates the NoteCollection object. It has no damn business being here.
     * @return
     */
    public NoteCollection getAllNotes() {
        return this._allNotes;
    }

    /**
     * A method that iterates through a key collection and returns a list that
     * contains all keys that have max strength.
     * @param       targetStrength int; strength that the
     * @return
     */
    public List<Key> getKeysWithStrength(int targetStrength) {
        List<Key> keysWithStrength = new ArrayList<>();
        for (Key curKey : this._majorKeys) {
            if (curKey.getStrength() == targetStrength) {
                keysWithStrength.add(curKey);
            }
        }
        return keysWithStrength;
    }
}
