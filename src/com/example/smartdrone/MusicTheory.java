package com.example.smartdrone;

/**
 * The MusicTheory class contains static variables that help with automating the
 * creation of keys and indexing note values.
 */

public class MusicTheory {

    /**
     * An array that contains the numeric representation of intervals in the major scale.
     * Each index represents the scale degree from the major scale.
     * Each number represents the amount of semitones the interval is offset from the root.
     * (index 0 is 0 semitones away (M1); index 1 is 2 semitones away (M2); index 2 is 4 semitones away (M3); ...)
     */
    final static int[] MAJOR_SCALE_SEQUENCE = {0, 2, 4, 5, 7, 9, 11};

    /**
     * All keys that contain the target note, relative to the target.
     * This sequence happens to correspond perfectly with the phrygian mode.
     * For now it will be named after the phrygian mode, in order to increment
     * the strength of keys without doing a linear search.
     */
    final static int[] PHRYGIAN_SCALE_SEQUENCE = { 0, 1, 3, 5, 7, 8, 10};

    /**
     * Intervals that make up a major triad.
     */
    final static int[] MAJOR_TRAID_SEQUENCE = { 0, 4, 7 };

    /**
     * An array of all 12 tones used in western music.
     * Ordered from 'C' to 'B' (ascending).
     */
    final static String[] CHROMATIC_SCALE = { "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" };

    /**
     * An int that represents the total number of notes in western music.
     */
    final static int TOTAL_NOTES = 12;

    /**
     * An int representing the size of diatonic scales.
     * This includes: major, melodic minor, harmonic minor, harmonic major.
     */
    final static int DIATONIC_SCALE_SIZE = 7;

}
