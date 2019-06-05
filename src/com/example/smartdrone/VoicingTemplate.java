package com.example.smartdrone;

/**
 * Distinction between voicing templates and voicings:
 * Voicing templates contain indices that represent the scale degrees of a chord.
 * Voicing's, on the other hand, contain the actual midi keys mapped to notes (semi-tones).
 *
 * This class also follows the music theory convention of indexing starting at one.
 * 1 -> First scale degree; 3 -> Third scale degree; 5 -> Fifth scale degree; etc ...
 *     (translated to Voicing: *assume C major*)
 * 1 -> 0 or 'C';           3 -> 4 or 'E';           5 -> 7 or 'G';           etc ...
 */
public class VoicingTemplate {
    /**
     * Name of voicing template.
     */
    private String name;

    /**
     * Scale degrees in voicing template.
     */
    private int[] scaleDegrees;

    /**
     * Inversions of chord.
     */
    private int[] inversions;

    /**
     * Constructor.
     * Gets scale degrees as array with one based indexing, but converts it to zero based indexing.
     * @param       name String; name of voicing template.
     * @param       scaleDegrees int[]; scale degrees.
     */
    public VoicingTemplate(String name, int[] scaleDegrees) {
        this.name = name;
        this.scaleDegrees = getAsZeroBasedIndexing(scaleDegrees);
    }

    /**
     * Takes array with indexing that starts with one, and returns a copy of array with indexing that starts at 0.
     * @param       toModify int[]; array with one based indexing.
     * @return      int[]; copy of array with zero based indexing.
     */
    private int[] getAsZeroBasedIndexing(int[] toModify) {
        int[] zeroBasedArr = new int[toModify.length];
        for (int i = 0; i < toModify.length; i++) {
            zeroBasedArr[i] = toModify[i] - 1;
        }
        return zeroBasedArr;
    }

//    private int[] buildInversions()

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
    public int[] getScaleDegrees() {
        return scaleDegrees;
    }

    /**
     * Prints as one based indexing. Be warned.
     * @return      String; name followed by scale degrees with.
     */
    @Override
    public String toString() {
        String str = name + " ";
        for (int degree : scaleDegrees) {
            // Convert to one based indexing
            str += degree + 1 + " ";
        }
        return str;
    }
}
