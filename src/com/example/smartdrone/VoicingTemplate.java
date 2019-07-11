package com.example.smartdrone;

/**
 * Distinction between voicing templates and voicings:
 * Voicing templates contain indices that represent the scale degrees of a chord.
 * Voicing's, on the other hand, contain the actual midi keys mapped to notes (semi-tones).
 */
public class VoicingTemplate {
    //todo refactor; use underscore for private variable names

    /**
     * Name of voicing template.
     */
    private String _name;

    /**
     * Combines bass tones and chord tones into one array.
     */
    private Tone[] _templateTonesRefac;

    /**
     * Stores array of bass tones.
     */
    private Tone[] _bassTonesRefac;

    //todo refactor name when int[] chordTones is removed.
    /**
     * Stores array of chord tones.
     */
    private Tone[] _chordTonesRefac;

    /**
     * Scale degrees in voicing template.
     *
     */
    @Deprecated
    private int[] chordTones;

    //todo Complete inversions when the time comes.
    /**
     * Inversions of chord.
     * @deprecated moved to ChordGenerator class.
     */
    @Deprecated
    private int[][] inversions;

    /**
     * Container for all scale templates.
     * @deprecated moved to ChordGenerator class.
     */
    @Deprecated
    private ModeTemplateCollection modeTemplateCollection;


    public VoicingTemplate(int[] bassToneIxs, int[] chordToneIxs) {
        this(null, bassToneIxs, chordToneIxs);
    }

    //Todo constructor could use some  refactoring; (make use of private methods)
    public VoicingTemplate(String name, int[] bassToneIxs, int[] chordToneIxs) {
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

        _name = name;
        _bassTonesRefac = bassTones;
        _chordTonesRefac = chordTones;
        modeTemplateCollection = new ModeTemplateCollection(); // this is here because method generateVoicing() uses it. Won't be necessary when HarmonyGenerator is complete

        _templateTonesRefac = new Tone[bassTones.length + chordTones.length];
        int i = 0;
        // Add all bass tones.
        for (Tone curTone : bassTones) {
            _templateTonesRefac[i] = curTone;
            i++;
        }
        // Add all chord tones.
        for (Tone curTone : chordTones) {
            _templateTonesRefac[i] = curTone;
            i++;
        }

    }

    /**
     * Constructor.
     * Chord tones used zero based indexing; follows programming paradigm, but goes against music theory convention.
     * @param       bassTones Tone[]; tones for bass.
     * @param       chordTones Tone[]; tones for chord.
     */
    @Deprecated
    public VoicingTemplate(Tone[] bassTones, Tone[] chordTones) {
        this(null, bassTones, chordTones);
    }

    /**
     * Constructor.
     * Chord tones used zero based indexing; follows programming paradigm, but goes against music theory convention.
     * @param       name String; name of voicing template.
     * @param       chordTones Tone[]; scale degrees.
     */
    @Deprecated
    public VoicingTemplate(String name, Tone[] bassTones, Tone[] chordTones) {
        _name = name;
        _bassTonesRefac = bassTones;
        _chordTonesRefac = chordTones;
        modeTemplateCollection = new ModeTemplateCollection(); // this is here because method generateVoicing() uses it. Won't be necessary when HarmonyGenerator is complete

        _templateTonesRefac = new Tone[bassTones.length + chordTones.length];
        int i = 0;
        // Add all bass tones.
        for (Tone curTone : bassTones) {
            _templateTonesRefac[i] = curTone;
            i++;
        }
        // Add all chord tones.
        for (Tone curTone : chordTones) {
            _templateTonesRefac[i] = curTone;
            i++;
        }
    }

    /**
     * Constructor.
     * Chord tones used zero based indexing; follows programming paradigm, but goes against music theory convention.
     * @param       chordTones int[]; scale degrees.
     *
     * @deprecated use Tone objects instead of int[]
     */
    @Deprecated
    public VoicingTemplate(int[] chordTones) {
        _name = null;
        this.chordTones = chordTones;
        this.inversions = getInversions(chordTones);
        this.modeTemplateCollection = new ModeTemplateCollection();
    }

    /**
     * Constructor.
     * Chord tones used zero based indexing; follows programming paradigm, but goes against music theory convention.
     * @param       name String; name of voicing template.
     * @param       chordTones int[]; scale degrees.
     *
     * @deprecated use Tone objects instead of int[]
     */
    @Deprecated
    public VoicingTemplate(String name, int[] chordTones) {
        _name = name;
        this.chordTones = chordTones;
        this.inversions = getInversions(chordTones);
        this.modeTemplateCollection = new ModeTemplateCollection();
    }

    /**
     * Get name of voicing template.
     * @return      String; name of voicing template.
     */
    public String getName() {
        return _name;
    }

    /**
     * Get all template tones.
     * @return      Tone[]; template tones.
     */
    public Tone[] getTemplateTones() {
        return _templateTonesRefac;
    }

    /**
     * Get all bass tones.
     * @return      Tone[]; bass tones.
     */
    public Tone[] getBassTones() {
        return _bassTonesRefac;
    }

    //todo will have to be careful about this method. the name chordTones is deceptive, it doesn't actually include bass tones
    /**
     * Get all chord tones.
     * @return      Tone[]; chord tones.
     */
    public Tone[] getChordTones() {
        return _chordTonesRefac;
    }

    /**
     * Constructs all the inversions for voicing template.
     * @param       chordTones int[]; degrees of voicing template.
     * @return      int[][]; inversions of voicing template.
     * @deprecated moved to ChordGenerator class.
     */
    @Deprecated
    private int[][] getInversions(int[] chordTones) {
        int numDegrees = chordTones.length;
        int[][] inversions = new int[numDegrees][numDegrees];
        // Template is same as root inversion.
        inversions[0] = chordTones;
        // For each inversion.
        for (int i = 1; i < numDegrees; i++) {
            int[] curInversion = inversions[i];
            // For each note in inversion.
            for (int j = 0; j < numDegrees; j++) {
                curInversion[j] = chordTones[(j + i) % numDegrees];
                // Proper octave.
                if (j + i >= numDegrees) {
                    curInversion[j] += 7;
                }
            }
        }
        return inversions;
    }

    /**
     * Get scale degrees of voicing template.
     * @return      int[]; scale degrees of voicing template.
     */
    @Deprecated
    public int getChordTone(int ix) {
        return chordTones[ix];
    }

    /**
     * Returns inversion matching parameter.
     * @param       inversionNum int; number of inversion.
     * @return      int[];  scale degrees of inversion.
     * @deprecated moved to ChordGenerator class.
     */
    @Deprecated
    public int[] getInversion(int inversionNum) {
        return inversions[inversionNum];
    }

    /**
     * Return number of chord tones in voicing template.
     * @return      int; num chord tones.
     *
     * @deprecated use numVoices() instead
     */
    @Deprecated
    public int size() {
        return chordTones.length;
    }

    @Deprecated
    public Voicing generateVoicing(AbstractKey key, int mode, int octave, boolean bassNote) {
        return new Voicing(this, key, mode, octave, bassNote, modeTemplateCollection);
    }

    /**
     * Prints out name and chord tones.
     * @return      String; name followed by scale degrees with.
     */
    @Override
    public String toString() {
        String templateStr = "";
        for (Tone curTone : _templateTonesRefac) {
            templateStr += curTone.toString() + '\n';
        }
        return templateStr;
    }
}
