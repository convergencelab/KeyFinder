package com.example.smartdrone;

/**
 * Distinction between voicing templates and voicings:
 * Voicing templates contain indices that represent the scale degrees of a chord.
 * Voicing's, on the other hand, contain the actual midi keys mapped to notes (semi-tones).
 */
public class VoicingTemplate {
    /**
     * Name of voicing template.
     */
    private String name;

    /**
     * Scale degrees in voicing template.
     */
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

    /**
     * Constructor.
     * Chord tones used zero based indexing; follows programming paradigm, but goes against music theory convention.
     * @param       name String; name of voicing template.
     * @param       chordTones int[]; scale degrees.
     */
    public VoicingTemplate(String name, int[] chordTones) {
        this.name = name;
        this.chordTones = chordTones;
        this.inversions = getInversions(chordTones);
        this.modeTemplateCollection = new ModeTemplateCollection();
    }

    //todo fix: doesn't invert properly
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
     * Get name of voicing template.
     * @return      String; name of voicing template.
     */
    public String getName() {
        return name;
    }

    /**
     * Get scale degrees of voicing template.
     * @return      int[]; scale degrees of voicing template.
     */
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
     */
    public int size() {
        return chordTones.length;
    }

    public Voicing generateVoicing(Key key, int mode, int octave, boolean bassNote) {
        return new Voicing(this, key, mode, octave, bassNote, modeTemplateCollection);
    }

    /**
     * Prints out name and chord tones.
     * @return      String; name followed by scale degrees with.
     */
    @Override
    public String toString() {
        String str = name + " ";
        for (int degree : chordTones) {
            // Convert to one based indexing
            str += degree + " ";
        }
        return str;
    }
}
