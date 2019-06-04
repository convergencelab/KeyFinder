package com.example.smartdrone;

/**
 * The MusicTheory class contains static variables that help with automating the
 * creation of keys and indexing note values.
 */

public class MusicTheory {
    /**
     * Unicode for sharp sign.
     */
    final static char SHARP = '\u266F';

    /**
     * Unicode for flat sign.
     */
    final static char FLAT = '\u266d';


    /**
     * Intervals that make up the major scale.
     * Each number can be viewed as the semitone offset from the root.
     */
    final static int[] MAJOR_SCALE_SEQUENCE    = { 0, 2, 4, 5, 7, 9, 11};

    /**
     * Intervals that make up the phrygian scale.
     */
    final static int[] PHRYGIAN_SCALE_SEQUENCE = { 0, 1, 3, 5, 7, 8, 10};

    /**
     * Intervals that make up a major triad.
     */
    final static int[] MAJOR_TRAID_SEQUENCE    = { 0, 4, 7 };

    /**
     * Names of all 12 tones used in western music.
     */
    final static String[] CHROMATIC_SCALE_SHARP = {
            "C",
            "C" + SHARP,
            "D",
            "D" + SHARP,
            "E",
            "F",
            "F" + SHARP,
            "G",
            "G" + SHARP,
            "A",
            "A" + SHARP,
            "B"
    };

    final static String[] CHROMATIC_SCALE_FLAT = {
            "C",
            "D" + FLAT,
            "D",
            "E" + FLAT,
            "E",
            "F",
            "G" + FLAT,
            "G",
            "A" + FLAT,
            "A",
            "B" + FLAT,
            "B"
    };

    /**
     * Total number of unique tones in western music.
     */
    final static int TOTAL_NOTES = 12;

    /**
     * Number of notes in a diatonic scale.
     */
    final static int DIATONIC_SCALE_SIZE = 7;

}
