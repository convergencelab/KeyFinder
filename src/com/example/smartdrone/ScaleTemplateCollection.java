package com.example.smartdrone;

public class ScaleTemplateCollection {
    /**
     * Array of scale templates.
     */
    private ScaleTemplate[] _allScaleTemplates;

    /**
     * Constructor.
     * Generate scale template for all 7 modes.
     */
    public ScaleTemplateCollection() {
        _allScaleTemplates = new ScaleTemplate[7];
        for (int i = 0; i < MusicTheory.DIATONIC_SCALE_SIZE; i++) {
            _allScaleTemplates[i] = new ScaleTemplate(
                    MusicTheory.MAJOR_MODE_NAMES[i], getIntervalsForMode(i));
        }
    }

    /**
     * Generate intervals for mode pertaining to given index.
     * @param       modeIx int; index of mode (0 = Ionian; 1 = Dorian; 2 = Phrygian; etc ...).
     *                     *** EVEN THOUGH MUSIC THEORY USES 1 BASED INDEXING (1 = Ionian; 2 = Dorian; etc ...)
     * @return      int[]; intervals of mode.
     */
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
     * Return scale template for given mode ix.
     * @param       modeIx int; index of mode (0 = Ionian; 1 = Dorian; 2 = Phrygian; etc ...).
     *                     *** EVEN THOUGH MUSIC THEORY USES 1 BASED INDEXING (1 = Ionian; 2 = Dorian; etc ...)
     * @return      int[]; intervals of mode.
     */
    public ScaleTemplate getScaleTemplateForMode(int modeIx) {
        return _allScaleTemplates[modeIx];
    }

    public String toString() {
        String str = "";
        for (ScaleTemplate st : _allScaleTemplates) {
            str += st.toString() + '\n';
        }
        return str;
    }
}
