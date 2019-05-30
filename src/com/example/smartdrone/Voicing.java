package com.example.smartdrone;


import java.util.LinkedList;

/**
 * (*Note and voice may be used interchangeably, though a voice refers to a specific note and octave
 * pertaining to a chord/voice.)
 *
 * A voicing is a specific chord voicing. Class contains note/voice indices to each note/voice in voicing.
 */
public class Voicing {
    /**
     * Indices that represent each individual voice.
     */
    private int[] voiceIxs;

    /**
     * Name of voicing.
     */
    private String name;

    /**
     * Constructor.
     * @param       name; name of voicing.
     * @param       voiceIxs; voices in voicing.
     */
    public Voicing(String name, int[] voiceIxs) {
        this.name = name;
        this.voiceIxs = voiceIxs;
    }

    /**
     * Get voicing name.
     * @return      String; name of voicing.
     */
    public String getName() {
        return name;
    }

    /**
     * Get all voices.
     * @return      int[]; voices in voicing.
     */
    public int[] getVoiceIxs() {
        return voiceIxs;
    }
}
