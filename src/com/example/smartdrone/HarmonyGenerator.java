package com.example.smartdrone;

public class HarmonyGenerator {
    /**
     * Default lower bound for bass note in chord.
     */
    private static final int LOWER_BOUND_BASS_DEFAULT = 36; // C3

    /**
     * Default lower bound for chord voices in chord.
     */
    private static final int LOWER_BOUND_CHORD_DEFAULT = 48; // C4

    /**
     * No note in bass.
     */
    public static final int[] BASS_NULL = { };

    /**
     * Play root in bass.
     * If no parameter given when generating voicing's, this will be the default.
     */
    public static final int[] BASS_ROOT = { 0 };

    /**
     * Play the fifth in bass.
     */
    public static final int[] BASS_FIFTH = { 4 };

    /**
     * Play root and fifth in bass.
     */
    public static final int[] BASS_ROOT_FIFTH = { 0 , 4 };

    /**
     * Lowest note to be played in bass.
     */
    private int _lowerBoundBass;

    /**
     * Lowest note to be played in chord.
     */
    private int _lowerBoundChord;

    /**
     * Default constructor.
     * Uses default lower bounds.
     */
    public HarmonyGenerator() {
        this(LOWER_BOUND_CHORD_DEFAULT, LOWER_BOUND_BASS_DEFAULT);
    }

    /**
     * Constructor. Sets given bounds.
     * @param       lowerBoundBass int; lowest index for bass notes.
     * @param       lowerBoundChord int; lowest index for chord notes.
     */
    public HarmonyGenerator(int lowerBoundBass, int lowerBoundChord) {
        _lowerBoundChord = lowerBoundChord;
        _lowerBoundBass = lowerBoundBass;
    }

    /**
     * Uses fields to generate voicing. Good for constructing voicings without having to create mode templates and also
     * maintaining consistent lower bounds.
     * @param       voicingTemplate VoicingTemplate; template containing chord tones.
     * @param       modeTemplate ModeTemplate; template containing mode intervals.
     * @param       key key; parent key.
     * @return      Voicing; voicing.
     */
    public Voicing generateVoicing(VoicingTemplate voicingTemplate, ModeTemplate modeTemplate, AbstractKey key) {
        return new Voicing(voicingTemplate, modeTemplate, key, _lowerBoundBass, _lowerBoundChord);
    }

    /**
     * Calls VoicingTemplate constructor and returns voicing template.
     * Just here to be consistent with generateVoicing method, even though this doesn't do anything extra.
     * @param       name String; name of voicing template.
     * @param       bassToneIxs int[]; bass tone indices.
     * @param       chordToneIxs int[]; chord tone indices.
     * @return      VoicingTemplate; voicing template.
     */
    public VoicingTemplate generateVoicingTemplate(String name, int[] bassToneIxs, int[] chordToneIxs) {
        return new VoicingTemplate(name, bassToneIxs,chordToneIxs);
    }

    /**
     * Calls VoicingTemplate constructor with null as name and returns voicing template.
     * Just here to be consistent with generateVoicing method, even though this doesn't do anything extra.
     * @param       bassToneIxs int[]; bass tone indices.
     * @param       chordToneIxs int[]; chord tone indices.
     * @return      VoicingTemplate; voicing template.
     */
    public VoicingTemplate generateVoicingTemplate(int[] bassToneIxs, int[] chordToneIxs) {
        return new VoicingTemplate(null, bassToneIxs,chordToneIxs);
    }

    /**
     * Get lower bound for bass tones.
     * @return      int; lower bound.
     */
    public int getLowerBoundBass() {
        return _lowerBoundBass;
    }

    /**
     * Set lower bound for bass tones.
     * @param       lowerBound int; lower bound.
     */
    public void setLowerBoundBass(int lowerBound) {
        this._lowerBoundBass = lowerBound;
    }

    /**
     * Get lower bound for chord tones.
     * @return      int; lower bound.
     */
    public int getLowerBoundChord() {
        return _lowerBoundChord;
    }

    /**
     * Set lower bound for chord tones.
     * @param       lowerBound int; lower bound.
     */
    public void setLowerBoundChord(int lowerBound) {
        this._lowerBoundChord = lowerBound;
    }

//    /**
//     * Makes VoicingTemplate from given indices.
//     * @param       bassToneIxs int[]; indices of bass tones.
//     * @param       chordToneIxs int[]; indices of chord tones.
//     * @return      VoicingTemplate; template made from given indices.
//     */
//    @Deprecated
//    public VoicingTemplate generateVoicingTemplate(int[] bassToneIxs, int[] chordToneIxs) {
//        Tone[] bassTones = new Tone[bassToneIxs.length];
//        Tone[] chordTones = new Tone[chordToneIxs.length];
//        // Construct Bass Tones
//        for (int i = 0; i < bassToneIxs.length; i++) {
//            bassTones[i] = new Tone(bassToneIxs[i], Tone.TONE_BASS);
//        }
//        // Construct Chord Tones
//        for (int i = 0; i < chordToneIxs.length; i++) {
//            chordTones[i] = new Tone(chordToneIxs[i], Tone.TONE_CHORD);
//        }
//
//        return new VoicingTemplate(bassTones, chordTones);
//    }
}
