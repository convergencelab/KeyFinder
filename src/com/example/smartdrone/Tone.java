package com.example.smartdrone;

/**
 * ScaleDegree class will map degree to an interval.
 *
 * C Ionian Explanation:
 * 0 -> 0; 1 -> 2; 2 -> 4; 3 -> 5; 4 -> 7; 5 -> 9; 6 -> 11
 * Chord tone on left; note index on right
 * so 0 maps to C; 1 maps to D; 2 maps to E; ...
 *
 * Tones are abstract; they can represent any octave.
 * 0 == 0; 0 == 12; 0 == 24; ...
 *
 * The reason for tones is to differentiate between bass tones and chord tones.
 * Bass tones can be played much lower than chord tones can be played, so it is important to differentiate between them.
 */
public class Tone {
    /**
     * Code representing tone as a bass tone.
     */
    public static final int TONE_BASS = 0;

    /**
     * Code representing tone as a chord tone.
     */
    public static final int TONE_CHORD = 1;

    private static final String[] TONE_NAMES = {
            "Bass",
            "Chord"
    };

    /**
     * Degree in scale.
     */
    private int _degree;

    /**
     * Code denoting whether tone is played in the bass or in the chord.
     */
    private int _code;

    /**
     * Constructor.
     * @param       degree int; the index of the scale/key/mode.
     * @param       code int; code denoting whether tone is a bass tone or chord tone.
     */
    public Tone(int degree, int code) {
        _degree = degree;
        _code = code;
    }

    /**
     * Get degree of tone.
     * @return      int; degree of tone.
     */
    public int getDegree() {
        return _degree;
    }

    /**
     * Get code of tone.
     * @return      int; code of tone.
     */
    public int getCode() {
        return _code;
    }

    @Override
    public String toString() {
        return TONE_NAMES[_code] + " " + Integer.toString(_degree);
    }
}
