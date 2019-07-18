package com.example.keyfinder;


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


    public Voicing(VoicingTemplate voicingTemplate, ModeTemplate modeTemplate, AbstractKey key,
                   int lowerBoundBass, int lowerBoundChord) {
        //bass notes
        Note[] voices = new Note[voicingTemplate.getTemplateTones().length];
        int voicesIx = 0;
        // Construct Bass Notes
        int lowestBass = getLowestValue(key, modeTemplate, lowerBoundBass);

        // Todo: convert these into utility methods
        for (Tone tone : voicingTemplate.getBassTones()) {
            voices[voicesIx] = new Note(lowestBass + (12 * (tone.getDegree() / MusicTheory.DIATONIC_SCALE_SIZE))
                    + modeTemplate.getIntervals()[tone.getDegree() % 7]);
            voicesIx++;
        }
        // Construct Chord Notes
        int lowestChord = getLowestValue(key, modeTemplate, lowerBoundChord);
        for (Tone tone : voicingTemplate.getChordTones()) {
            voices[voicesIx] = new Note(lowestChord + (12 * (tone.getDegree() / MusicTheory.DIATONIC_SCALE_SIZE))
                    + modeTemplate.getIntervals()[tone.getDegree() % 7]);
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
    private int getLowestValue(AbstractKey key, ModeTemplate modeTemplate, int lowerBound) {
        int root = key.getDegree(modeTemplate.getIx()).getIx();
        int lowest = ((lowerBound / MusicTheory.TOTAL_NOTES) * MusicTheory.TOTAL_NOTES) + root;
        if (lowerBound % MusicTheory.TOTAL_NOTES > root) {
            lowest += MusicTheory.TOTAL_NOTES;
        }
        return lowest;
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
        // todo implement method
        return str;
    }
}
