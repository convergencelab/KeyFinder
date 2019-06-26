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
     * Notes representing each voice in the voicing.
     */
    private Note[] _voices;

    /**
     * Key that the voicing is in.
     */
    private Key _key;

    /**
     * Mode that the voicing is in.
     */
    private ModeTemplate _mode;

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
     * @param voices
     * @param key
     * @param mode
     */
    public Voicing(Note[] voices, Key key, int mode) {

    }

    /**
     * Get all voices.
     * @return      int[]; voices in voicing.
     */
    @Deprecated
    public int[] getVoiceIxs() {
        return this.voiceIxs;
    }

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
