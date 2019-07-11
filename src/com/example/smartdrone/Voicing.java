package com.example.smartdrone;


/**
 * (*Note and voice may be used interchangeably, though a voice refers to a specific note and octave
 * pertaining to a chord/voice.)
 *
 * A voicing is a specific chord voicing. Class contains note/voice indices to each note/voice in voicing.
 */
public class Voicing {
    /**
     * Notes in voicing.
     */
    private Note[] _voices;

    /**
     * Indices that represent each individual voice.
     */
    @Deprecated
    private int[] voiceIxs;

    public Voicing(VoicingTemplate voicingTemplate, ModeTemplate modeTemplate, AbstractKey key,
                   int lowerBoundBass, int lowerBoundChord) {
        //bass notes
        Note[] voices = new Note[voicingTemplate.getTemplateTones().length];
        int voicesIx = 0;
        // Construct Bass Notes
        int lowestBass = getLowestNote(key, modeTemplate, lowerBoundBass);
        for (Tone tone : voicingTemplate.getBassTones()) {
            voices[voicesIx] = new Note(lowestBass + modeTemplate.getIntervals()[tone.getDegree()]);
            voicesIx++;
        }
        // Construct Chord Notes
        int lowestChord = getLowestNote(key, modeTemplate, lowerBoundChord);
        for (Tone tone : voicingTemplate.getChordTones()) {
            voices[voicesIx] = new Note(lowestChord + modeTemplate.getIntervals()[tone.getDegree()]);
            voicesIx++;
        }
        _voices = voices;
    }

    /**
     * Determines the lowest possible note index for the voicing (bass or chord).
     * Lowest note will be greater than or equal to lower bound.
     * @param       key AbstractKey; parent key.
     * @param       modeTemplate ModeTemplate; mode.
     * @param       lowerBound int; lowest bound.
     * @return      int; lowest note index.
     */
    private int getLowestNote(AbstractKey key, ModeTemplate modeTemplate, int lowerBound) {
        int root = key.getDegree(modeTemplate.getIx()).getIx();
        int lowest = ((lowerBound / MusicTheory.TOTAL_NOTES) * MusicTheory.TOTAL_NOTES) + root;
        if (lowerBound % MusicTheory.TOTAL_NOTES > root) {
            lowest += MusicTheory.TOTAL_NOTES;
        }
        return lowest;
    }

    /**
     * Constructor.
     * @param       voices Note[]; notes in the voicing.
     */
    @Deprecated
    public Voicing(Note[] voices) {
        _voices = voices;
    }

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
    public Voicing(VoicingTemplate voicingTemplate, AbstractKey key, int mode, int octave, boolean hasBassNote,
                   ModeTemplateCollection modeTemplateCollection) {
        int root =
                ((key.getIx() + key.getParentIntervals()[mode]) % MusicTheory.TOTAL_NOTES) //todo fix this line. should be dynamic; current state 3rd mode of melodic minor is a half step transposed
                        + (MusicTheory.TOTAL_NOTES * octave); //TOTAL_NOTES = 12
        if (hasBassNote) {
            voiceIxs = new int[voicingTemplate.size() + 1]; // make room for bass note
            voiceIxs[voiceIxs.length - 1] = root - 12; // put bass note at end of array
        }
        else {
            voiceIxs = new int[voicingTemplate.size()];
        }
        // Construct voicing
        ModeTemplate modeTemplate;
        if (key.getClass() == MelodicMinorKey.class) {
            modeTemplate = modeTemplateCollection.getMelodicMinorModeTemplates()[mode];
        }
        else {
            modeTemplate = modeTemplateCollection.getMajorModeTemplates()[mode];
        }
        for (int i = 0; i < voicingTemplate.size(); i++) {
            int octaveAdjustment = voicingTemplate.getChordTone(i) / 7;
            voiceIxs[i] = root + modeTemplate.getIntervals()[voicingTemplate.getChordTone(i) % 7]; //todo refactor big scary line
            voiceIxs[i] += octaveAdjustment * 12;
        }
    }

    //todo
    // addVoice()
    // removeVoice()
    /**
     * Return voice in chord.
     * Function returns single note instead of array to be consistent with other classes in library.
     *
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

    /**
     * Get all voices.
     * @return      int[]; voices in voicing.
     */
    @Deprecated
    public int[] getVoiceIxs() {
        return this.voiceIxs;
    }
}
