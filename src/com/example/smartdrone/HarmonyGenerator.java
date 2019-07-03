package com.example.smartdrone;

public class HarmonyGenerator {
    /**
     * Default lower bound for bass note in chord.
     */
    private static final int LOWER_BOUND_BASS_DEFAULT = 36;

    /**
     * Default lower bound for chord voices in chord.
     */
    private static final int LOWER_BOUND_CHORD_DEFAULT = 48;

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
     * Container of all keys.
     * Contains all 12 major keys.
     */
    private KeyCollection _keyCollection;

    /**
     * Container of all scale templates.
     * Contains templates for all 7 major modes.
     */
    private ModeTemplateCollection _modeTemplateCollection;

    /**
     * Current voicing template.
     */
    private VoicingTemplate _curVoicingTemplate;

    /**
     * Current mode.
     */
    private ModeTemplate _curMode;

    /**
     * Current key.
     */
    private Key _curKey;

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

//    //todo may be unnecessary; never needed to be stored?
//    /**
//     * Code that decides what to put in the bass of chord.
//     */
//    private int _bassCode;

    /**
     * Default constructor.
     * Uses default lower bounds.
     */
    public HarmonyGenerator() {
        this(LOWER_BOUND_CHORD_DEFAULT, LOWER_BOUND_BASS_DEFAULT);
    }

    /**
     * Constructor.
     * Takes bass and chord limits as parameters.
     * @param       lowerBoundChord int; lowest value for chord voices.
     * @param       lowerBoundBass int; lowest value for bass voices.
     */
    public HarmonyGenerator(int lowerBoundChord, int lowerBoundBass) {
        _modeTemplateCollection = new ModeTemplateCollection();
        _keyCollection = new KeyCollection();

        _lowerBoundChord = lowerBoundChord;
        _lowerBoundBass = lowerBoundBass;

//        _bassCode = BASS_ROOT;
        _curVoicingTemplate = null;
        _curMode = _modeTemplateCollection.getModeTemplateForMode(0);
        _curKey = _keyCollection.getMajorKeyAtIndex(0);
    }

    //todo method may be unnecessary, create overloaded constructor with int[] as parameters.

    /**
     * Makes VoicingTemplate from given indices.
     * @param       bassToneIxs int[]; indices of bass tones.
     * @param       chordToneIxs int[]; indices of chord tones.
     * @return      VoicingTemplate; template made from given indices.
     */
    public VoicingTemplate generateVoicingTemplate(int[] bassToneIxs, int[] chordToneIxs) {

        Tone[] bassTones = new Tone[bassToneIxs.length];
        Tone[] chordTones = new Tone[chordToneIxs.length];
        // Construct Bass Tones
        for (int i = 0; i < bassToneIxs.length; i++) {
            bassTones[i] = new Tone(bassToneIxs[i], Tone.TONE_BASS);
        }
        // Construct Chord Tones
        for (int i = 0; i < chordToneIxs.length; i++) {
            chordTones[i] = new Tone(chordToneIxs[i], Tone.TONE_CHORD);
        }

        return new VoicingTemplate(bassTones, chordTones);
    }

    /**
     * Construct and return voicing object.
     * @param voicingTemplate
     * @param modeTemplate
     * @param key
     * @return
     */
    public Voicing generateVoicing(VoicingTemplate voicingTemplate, ModeTemplate modeTemplate, Key key) {
        return null; //todo for now
    }

    public VoicingTemplate getVoicingTemplate() {
        return _curVoicingTemplate;
    }

    public void setVoicingTemplate(VoicingTemplate template) {
        this._curVoicingTemplate = template;
    }

    public ModeTemplate getMode() {
        return _curMode;
    }

    public void setMode(ModeTemplate mode) {
        this._curMode = mode;
    }

    public void setMode(int modeIx) {
        this._curMode = _modeTemplateCollection.getModeTemplateForMode(modeIx);
    }

    public Key getKey() {
        return _curKey;
    }

    public void setKey(Key key) {
        this._curKey = key;
    }

    public void setKey(int keyIx) {
        this._curKey = _keyCollection.getMajorKeyAtIndex(keyIx);
    }

    public Voicing getVoicing() {
        return _curVoicing;
    }

    public void setCurVoicing(Voicing voicing) {
        this._curVoicing = voicing;
    }

    public int getLowerBoundBass() {
        return _lowerBoundBass;
    }

    public void setLowerBoundBass(int lowerBound) {
        this._lowerBoundBass = lowerBound;
    }

    public int getLowerBoundChord() {
        return _lowerBoundChord;
    }

    public void setLowerBoundChord(int lowerBound) {
        this._lowerBoundChord = lowerBound;
    }
}
