package com.example.smartdrone;

public class MajorKey extends AbstractKey {
    /**
     * Constructor with note collection given.
     * @param       keyCenterIx int; index of key center.
     * @param       noteCollection NoteCollection; note collection.
     */
    public MajorKey(int keyCenterIx, NoteCollection noteCollection) {
        super(keyCenterIx, noteCollection);
    }

    /**
     * Constructor with no note collection given.
     * @param       keyCenterIx int; index of key center.
     */
    public MajorKey(int keyCenterIx) {
        super(keyCenterIx);
    }

    /**
     * Method that fills note list with corresponding notes in key.
     * @param       noteCollection NoteCollection; note collection.
     *                             If null is given method will construct only notes needed.
     */
    @Override
    protected void inflateKeyNotes(NoteCollection noteCollection) {
        int offset;
        int curNoteIx;
        // Construct from note collection.
        if (noteCollection != null) {
            for (int i = 0; i < MusicTheory.DIATONIC_SCALE_SIZE; i++) {
                offset = MusicTheory.MAJOR_SCALE_SEQUENCE[i];
                curNoteIx = (getIx() + offset) % MusicTheory.TOTAL_NOTES; // TOTAL_NOTES = 12
                _notes[i] = noteCollection.getNoteAtIndex(curNoteIx);
            }
        }
        // Construct only notes needed.
        else {
            for (int i = 0; i < MusicTheory.DIATONIC_SCALE_SIZE; i++) {
                offset = MusicTheory.MAJOR_SCALE_SEQUENCE[i];
                curNoteIx = (getIx() + offset) % MusicTheory.TOTAL_NOTES; // TOTAL_NOTES = 12
                _notes[i] = new Note(curNoteIx);
            }
        }
    }
}
