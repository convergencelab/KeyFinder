package com.example.keyfinder;

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
        this(LOWER_BOUND_BASS_DEFAULT, LOWER_BOUND_CHORD_DEFAULT);
    }

    /**
     * Constructor. Sets given bounds.
     * @param       lowerBoundBass int; lowest index for bass notes.
     * @param       lowerBoundChord int; lowest index for chord notes.
     */
    public HarmonyGenerator(int lowerBoundBass, int lowerBoundChord) {
        _lowerBoundBass = lowerBoundBass;
        _lowerBoundChord = lowerBoundChord;
    }

    /**
     * Uses fields to generate voicing. Good for constructing voicings without having to create mode templates and also
     * maintaining consistent lower bounds.
     * @param       voicingTemplate VoicingTemplate; template containing chord tones.
     * @param       modeTemplate ModeTemplate; template containing mode intervals.
     * @param       key key; parent key.
     * @return      Voicing; voicing.
     */
    @Deprecated
    public Voicing generateVoicing(VoicingTemplate voicingTemplate, ModeTemplate modeTemplate, AbstractKey key) {
        return new Voicing(voicingTemplate, modeTemplate, key, _lowerBoundBass, _lowerBoundChord);
    }

    public Voicing generateVoicing(VoicingTemplate voicingTemplate, ModeTemplate modeTemplate, int keyIx) {
        return new Voicing(voicingTemplate, modeTemplate, keyIx, _lowerBoundBass, _lowerBoundChord);
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

    public Note generateNote(Tone tone, ModeTemplate modeTemplate, int keyIx) {
        int lowestValue;
        if (tone.getCode() == Tone.TONE_BASS) {
            lowestValue = getLowestValue(keyIx, _lowerBoundBass);
        }
        else {
            lowestValue = getLowestValue(keyIx, _lowerBoundChord);
        }
        // Todo convert into utility method
        return new Note(lowestValue + (12 * (tone.getDegree() / MusicTheory.DIATONIC_SCALE_SIZE))
                + modeTemplate.getIntervals()[tone.getDegree() % 7]);
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

    // Todo: convert into utility method
    /**
     * Determines the lowest possible note index for the voicing (bass or chord).
     * Lowest note will be greater than or equal to lower bound.
     * @param       key AbstractKey; parent key.
     * @param       modeTemplate ModeTemplate; mode.
     * @param       lowerBound int; lowest bound.
     * @return      int; lowest note index.
     */
    private int getLowestValue(AbstractKey key, ModeTemplate modeTemplate, int lowerBound) {
        int root = key.getDegree(modeTemplate.getIx()).getIx();
        int lowest = ((lowerBound / MusicTheory.TOTAL_NOTES) * MusicTheory.TOTAL_NOTES) + root;
        if (lowerBound % MusicTheory.TOTAL_NOTES > root) {
            lowest += MusicTheory.TOTAL_NOTES;
        }
        return lowest;
    }

    private int getLowestValue(int keyIx, int lowerBound) {
        int root = keyIx % MusicTheory.TOTAL_NOTES;
        int lowest = ((lowerBound / MusicTheory.TOTAL_NOTES) * MusicTheory.TOTAL_NOTES) + root;
        if (lowerBound % MusicTheory.TOTAL_NOTES > root) {
            lowest += MusicTheory.TOTAL_NOTES;
        }
        return lowest;
    }
}
