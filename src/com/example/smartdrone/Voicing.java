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
    @Deprecated
    private int[] voiceIxs;

    /**
     * Notes in voicing.
     */
    private Note[] _voices;

    /**
     * Construct voicing.
     * @param voicingTemplate
     * @param key
     * @param mode
     * @param octave
     * @param hasBassNote
     * @param modeTemplateCollection
     */
    @Deprecated
    public Voicing(VoicingTemplate voicingTemplate, Key key, int mode, int octave, boolean hasBassNote,
                   ModeTemplateCollection modeTemplateCollection) {
        int root =
                ((key.getIx() + MusicTheory.MAJOR_SCALE_SEQUENCE[mode]) % MusicTheory.TOTAL_NOTES)
                        + (MusicTheory.TOTAL_NOTES * octave); //TOTAL_NOTES = 12
        if (hasBassNote) {
            voiceIxs = new int[voicingTemplate.size() + 1]; // make room for bass note
            voiceIxs[voiceIxs.length - 1] = root - 12; // put bass note at end of array
        }
        else {
            voiceIxs = new int[voicingTemplate.size()];
        }
        // Construct voicing
        ModeTemplate modeTemplate = modeTemplateCollection.getModeTemplateForMode(mode);
        for (int i = 0; i < voicingTemplate.size(); i++) {
            int octaveAdjustment = voicingTemplate.getChordTone(i) / 7;
            voiceIxs[i] = root + modeTemplate.getIntervals()[voicingTemplate.getChordTone(i) % 7]; //todo refactor big scary line
            voiceIxs[i] += octaveAdjustment * 12;
        }
    }

    /**
     * Constructor.
     * @param       voices Note[]; notes in the voicing.
     */
    public Voicing(Note[] voices) {
        _voices = voices;
    }

    /**
     * Get all voices.
     * @return      int[]; voices in voicing.
     */
    @Deprecated
    public int[] getVoiceIxs() {
        return this.voiceIxs;
    }

    //todo
    // addVoice()
    // removeVoice()

    /**
     * Return voice in chord.
     *
     * Function returns single note instead of array to be consistent with other classes in library.
     * Example usage:
     *     for (int i = 0; i < exampleVoicing.numVoices(); i++) {
     *         someFunction(exampleVoicing.getVoice(i);
     *     }
     *
     * @param       ix int; index of voice array.
     * @return      Note; note at index.
     */
    public Note getVoice(int ix) {
        return _voices[ix];
    }

    /**
     * Return the number of voices in the voicing.
     * @return      int; number of voices.
     */
    public int numVoices() {
        return _voices.length;
    }

    //todo refactor method
    /**
     * Generates string with all note names and values.
     * @return
     */
    @Override
    public String toString() {
        String str = "";
        // Add each note name and midi key to string.
        for (int i = 0; i < voiceIxs.length; i++) {
            str += MusicTheory.CHROMATIC_SCALE_SHARP[(voiceIxs[i]) % 12] + " : " + voiceIxs[i] + '\n';
        }
        return str;
    }
}
