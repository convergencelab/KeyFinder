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
     * Construct voicing.
     * @param voicingTemplate
     * @param key
     * @param mode
     * @param octave
     * @param scaleTemplateCollection
     */
    public Voicing(VoicingTemplate voicingTemplate, Key key, int mode, int octave,
                   ScaleTemplateCollection scaleTemplateCollection) {
        voiceIxs = new int[voicingTemplate.size()];
        int root =
                ((key.getIx() + mode) % MusicTheory.TOTAL_NOTES) + (MusicTheory.TOTAL_NOTES * octave); //TOTAL_NOTES = 12
        // Construct voicing
        ScaleTemplate scaleTemplate = scaleTemplateCollection.getScaleTemplateForMode(mode);
        for (int i = 0; i < voicingTemplate.size(); i++) {
            int octaveAdjustment = voicingTemplate.getchordTones()[i] / 7;
            voiceIxs[i] = root + scaleTemplate.getIntervals()[voicingTemplate.getchordTones()[i] % 7]; //todo refactor big scary line
            voiceIxs[i] += octaveAdjustment * 12;
        }
    }

    /**
     * Get all voices.
     * @return      int[]; voices in voicing.
     */
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
