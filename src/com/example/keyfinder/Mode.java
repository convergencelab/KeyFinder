package com.example.keyfinder;

import java.util.Arrays;
import java.util.Objects;

public abstract class Mode {

    // Implementation will setup intervals and name
    protected abstract void setup();

    protected final int ix;

    protected int[] intervals;

    protected String name;

    public Mode(int ix) {
        this.ix = ix;
        setup();
    }

    public int getIx() {
        return ix;
    }

    public int[] getIntervals() {
        return intervals;
    }

    public String getName() {
        return name;
    }

    // Method will accommodate for degrees that are greater than a 7th
    public int getInterval(int degree) {
        return (intervals[degree % MusicTheory.DIATONIC_SCALE_SIZE] % MusicTheory.TOTAL_NOTES)
                + ((degree / MusicTheory.DIATONIC_SCALE_SIZE) * MusicTheory.TOTAL_NOTES);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mode mode = (Mode) o;
        return ix == mode.ix &&
                Arrays.equals(intervals, mode.intervals);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(ix);
        result = 31 * result + Arrays.hashCode(intervals);
        return result;
    }
}
