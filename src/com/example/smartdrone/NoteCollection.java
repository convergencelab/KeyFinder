package com.example.smartdrone;

public class NoteCollection {
    /**
     * The chromatic scale contains all 12 tones in western music.
     * It can subsequently be viewed as All Notes.
     * This array follows the index practice used in this program.
     */
    private Note[] _allNotes;

    /**
     * Constructs array with all 12 note objects in proper order.
     */
    public NoteCollection() {
        _allNotes = new Note[12];
        // Create note object for every index corresponding to the chromatic scale.
        for (int i = 0; i < MusicTheory.TOTAL_NOTES; i++) {
            _allNotes[i] = new Note(i);
        }
    }

    /**
     * Return the specified note.
     * @param       targetIndex int; the index of the target note.
     * @return      Note; object corresponding with index.
     */
    public Note getNoteAtIndex(int targetIndex) {
        return _allNotes[targetIndex];
    }
}
