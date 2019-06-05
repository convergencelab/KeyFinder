package com.example.smartdrone;


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

    //todo make new constructor. accepts params 1) voicing template; 2) mode; 3) octave
    /**
     * Constructor.
     * @param       name; name of voicing.
     * @param       voiceIxs; voices in voicing.
     */
    public Voicing(String name, int[] voiceIxs) {
        this.name = name;

        //todo exception handling for null voices given
        if (voiceIxs.length == 0) {
            System.out.println("Error: no voices given.");
            return;
        }
        this.voiceIxs = voiceIxs;
    }

    public Voicing(int[] voiceIxs) {
        this.name = null;

        //todo exception handling for null voices given
        if (voiceIxs.length == 0) {
            System.out.println("Error: no voices given.");
            return;
        }
        this.voiceIxs = voiceIxs;
    }

    /**
     * Get voicing name.
     * @return      String; name of voicing.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get all voices.
     * @return      int[]; voices in voicing.
     */
    public int[] getVoiceIxs() {
        return this.voiceIxs;
    }
}
