package com.example.smartdrone;

public class ChordGenerator {
    NoteCollection noteCollection;

    KeyCollection keyCollection;

    /**
     * Contains all 7 diatonic scale templates.
     * (Ionian, Dorian, Phrygian, ... , Locrian)
     */
    ScaleTemplateCollection scaleTemplateCollection;

    /**
     * Constructor.
     */
    public ChordGenerator() {
        noteCollection = new NoteCollection();
        keyCollection = new KeyCollection();
        scaleTemplateCollection = new ScaleTemplateCollection();
    }
}
