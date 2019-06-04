package com.example.smartdrone;

public class ScaleTemplateCollection {
    /**
     * Array of scale templates.
     */
    private ScaleTemplate[] _allScaleTemplates;

    /**
     * Constructor
     */
    public ScaleTemplateCollection() {
        _allScaleTemplates = new ScaleTemplate[7];
        for (int i = 0; i < MusicTheory.DIATONIC_SCALE_SIZE; i++) {
            _allScaleTemplates[i] = new ScaleTemplate(
                    MusicTheory.MAJOR_MODE_NAMES[i], getIntervalsForMode(i));
        }
    }

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

    public String toString() {
        String str = "";
        for (ScaleTemplate st : _allScaleTemplates) {
            str += st.toString() + '\n';
        }
        return str;
    }
}
