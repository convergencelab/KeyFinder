package com.example.smartdrone;

public class ChordGenerator {
    /**
     * Default lower bound for bass note in chord.
     */
    private static final int LOWER_BOUND_BASS_DEFAULT = 24; //todo check smartdrone code if right

    /**
     * Default lower bound for chord voices in chord.
     */
    private static final int LOWER_BOUND_CHORD_DEFAULT = 36; //todo check smartdrone code if right

    /**
     * No note in bass.
     */
    static final int BASS_NULL = 0;

    /**
     * Play root in bass.
     */
    static final int BASS_ROOT = 1;

    /**
     * Play the fifth in bass.
     */
    static final int BASS_FIFTH = 2;

    /**
     * Play root and fifth in bass.
     */
    static final int BASS_ROOT_FIFTH = 3;

    /**
     * Container of all keys.
     * Contains all 12 major keys.
     */
    private KeyCollection _keyCollection;

    /**
     * Container of all scale templates.
     * Contains templates for all 7 major modes.
     */
    private ScaleTemplateCollection _scaleTemplateCollection;

    /**
     * Current voicing template.
     */
    private VoicingTemplate _curVoicingTemplate;

    /**
     * Current voicing.
     */
    private Voicing _curVoicing;

    /**
     * Lowest note to be played in bass.
     */
    private int _lowerBoundBass;

    /**
     * Lowest note to be played in chord.
     */
    private int _lowerBoundChord;

    /**
     * Code that decides what to put in the bass of chord.
     */
    private int _bassCode;

    /**
     * Default constructor.
     * Uses default bounds.
     */
    public ChordGenerator() {
        this(LOWER_BOUND_CHORD_DEFAULT, LOWER_BOUND_BASS_DEFAULT);
        //todo comeback here plz
    }

    /**
     * Constructor.
     * Takes bass and chord limits as parameters.
     * @param       lowerBoundChord int; lowest value for chord voice.
     * @param       lowerBoundBass int; lowest value for bass note.
     */
    public ChordGenerator(int lowerBoundChord, int lowerBoundBass) {
        _scaleTemplateCollection = new ScaleTemplateCollection();
        _keyCollection = new KeyCollection();
        _bassCode = BASS_ROOT;

        _lowerBoundChord = lowerBoundChord;
        _lowerBoundBass = lowerBoundBass;
    }
}
