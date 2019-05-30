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
     * @param       voiceIxs; voices in voicing.
     * @param       name; name of voicing.
     */
    public Voicing(int[] voiceIxs, String name) {
        this.voiceIxs = voiceIxs;
        this.name = name;
    }

    /**
     * Get all voices.
     * @return      int[]; voices in voicing.
     */
    public int[] getVoiceIxs() {
        return voiceIxs;
    }

    /**
     * Get voicing name.
     * @return      String; name of voicing.
     */
    public String getName() {
        return name;
    }



    // todo: keep going with voicing.
}
