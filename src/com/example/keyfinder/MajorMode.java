package com.example.keyfinder;

public class MajorMode extends Mode {

    public MajorMode(int ix) {
        super(ix);
    }

    @Override
    protected void setup() {
        name = MusicTheory.MAJOR_MODE_NAMES[this.ix % MusicTheory.TOTAL_NOTES];

        intervals = new int[MusicTheory.DIATONIC_SCALE_SIZE];
        final int offset = MusicTheory.MAJOR_SCALE_SEQUENCE[ix % MusicTheory.TOTAL_NOTES];
        for (int i = 0; i < MusicTheory.DIATONIC_SCALE_SIZE; i++) {
            int curInterval = MusicTheory.MAJOR_SCALE_SEQUENCE[(i + ix) % MusicTheory.DIATONIC_SCALE_SIZE] - offset;
            if (curInterval < 0) {
                curInterval += MusicTheory.TOTAL_NOTES;
            }
            intervals[i] = curInterval;
        }
    }

}
