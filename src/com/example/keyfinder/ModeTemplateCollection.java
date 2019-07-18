package com.example.keyfinder;

public class ModeTemplateCollection {
    /**
     * Array of scale templates.
     */
    @Deprecated
    private ModeTemplate[] _allModeTemplates;

    /**
     * Array of scale templates for major keys.
     */
    private ModeTemplate[] _allMajorModeTemplates;

    /**
     * Array of scale templates for melodic minor keys.
     */
    private ModeTemplate[] _allMelodicMinorModeTemplates;

    /**
     * Constructor.
     * Generate scale template for all 7 modes.
     */
    public ModeTemplateCollection() {
        // Generate major key templates.
        _allMajorModeTemplates = new ModeTemplate[MusicTheory.DIATONIC_SCALE_SIZE];
        for (int i = 0; i < MusicTheory.DIATONIC_SCALE_SIZE; i++) {
            _allMajorModeTemplates[i] = new ModeTemplate(
                    MusicTheory.MAJOR_MODE_NAMES[i], getIntervalsForMajorMode(i), i);
        }

        // Generate melodic minor key templates.
        _allMelodicMinorModeTemplates = new ModeTemplate[MusicTheory.DIATONIC_SCALE_SIZE];
        for (int i = 0; i < MusicTheory.DIATONIC_SCALE_SIZE; i++) {
            _allMelodicMinorModeTemplates[i] = new ModeTemplate(
                    MusicTheory.MELODIC_MINOR_MODE_NAMES[i], getIntervalsForMelodicMinorMode(i), i);
        }
    }

    /**
     * Generate intervals for mode pertaining to given index.
     * @param       modeIx int; index of mode (0 = Ionian; 1 = Dorian; 2 = Phrygian; etc ...).
     *                     *** EVEN THOUGH MUSIC THEORY USES 1 BASED INDEXING (1 = Ionian; 2 = Dorian; etc ...)
     * @return      int[]; intervals of mode.
     */
    @Deprecated
    private int[] getIntervalsForMode(int modeIx) {
        int offset = MusicTheory.MAJOR_SCALE_SEQUENCE[modeIx];
        int[] scaleIxs = new int[7];
        int curInterval;
        for (int i = 0; i < MusicTheory.DIATONIC_SCALE_SIZE; i++) {
            curInterval = MusicTheory.MAJOR_SCALE_SEQUENCE[(i + modeIx) % 7] - offset;
            if (curInterval < 0) {
                curInterval += 12;
            }
            scaleIxs[i] = curInterval;
        }
        return scaleIxs;
    }

    /**
     * Generate intervals for mode pertaining to given index.
     * @param       modeIx int; index of mode (0 = Ionian; 1 = Dorian; 2 = Phrygian; etc ...).
     *                     *** EVEN THOUGH MUSIC THEORY USES 1 BASED INDEXING (1 = Ionian; 2 = Dorian; etc ...)
     * @return      int[]; intervals of mode.
     */
    private int[] getIntervalsForMajorMode(int modeIx) {
        int offset = MusicTheory.MAJOR_SCALE_SEQUENCE[modeIx];
        int[] scaleIxs = new int[MusicTheory.DIATONIC_SCALE_SIZE];
        int curInterval;
        for (int i = 0; i < MusicTheory.DIATONIC_SCALE_SIZE; i++) {
            curInterval = MusicTheory.MAJOR_SCALE_SEQUENCE[(i + modeIx) % MusicTheory.DIATONIC_SCALE_SIZE] - offset;
            if (curInterval < 0) {
                curInterval += MusicTheory.TOTAL_NOTES;
            }
            scaleIxs[i] = curInterval;
        }
        return scaleIxs;
    }

    /**
     * Generate intervals for melodic minor mode pertaining to given index.
     * @param       modeIx int; index of mode (0 = Ionian; 1 = Dorian; 2 = Phrygian; etc ...).
     *                     *** EVEN THOUGH MUSIC THEORY USES 1 BASED INDEXING (1 = Ionian; 2 = Dorian; etc ...)
     * @return      int[]; intervals of mode.
     */
    private int[] getIntervalsForMelodicMinorMode(int modeIx) {
        int offset = MusicTheory.MELODIC_MINOR_SCALE_SEQUENCE[modeIx];
        int[] scaleIxs = new int[MusicTheory.DIATONIC_SCALE_SIZE];
        int curInterval;
        for (int i = 0; i < MusicTheory.DIATONIC_SCALE_SIZE; i++) {
            curInterval = MusicTheory.MELODIC_MINOR_SCALE_SEQUENCE[(i + modeIx) % MusicTheory.DIATONIC_SCALE_SIZE] - offset;
            if (curInterval < 0) {
                curInterval += MusicTheory.TOTAL_NOTES;
            }
            scaleIxs[i] = curInterval;
        }
        return scaleIxs;
    }

    /**
     * Return scale template for given mode ix.
     * @param       modeIx int; index of mode (0 = Ionian; 1 = Dorian; 2 = Phrygian; etc ...).
     *                     *** EVEN THOUGH MUSIC THEORY USES 1 BASED INDEXING (1 = Ionian; 2 = Dorian; etc ...)
     * @return      int[]; intervals of mode.
     */
    @Deprecated
    public ModeTemplate getModeTemplateForMode(int modeIx) {
        return _allMelodicMinorModeTemplates[modeIx]; //todo yes this is wrong, just a current work-around
    }

    /**
     * Return all mode templates for major scale.
     * @return      ModeTemplate[]; array of mode templates.
     */
    public ModeTemplate[] getMajorModeTemplates() {
        return _allMajorModeTemplates;
    }

    /**
     * Return all mode templates for melodic minor scale.
     * @return      ModeTemplate[]; array of mode templates.
     */
    public ModeTemplate[] getMelodicMinorModeTemplates() {
        return _allMelodicMinorModeTemplates;
    }

    @Override
    public String toString() {
        String str = "";
        for (ModeTemplate temp : _allMajorModeTemplates) {
            str += temp.toString() + '\n';
        }
        for (ModeTemplate temp : _allMelodicMinorModeTemplates) {
            str += temp.toString() + '\n';
        }
        return str;
    }
}
