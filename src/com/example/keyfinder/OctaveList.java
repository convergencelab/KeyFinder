package com.example.keyfinder;

public class OctaveList {

    private int lowerHeardIx;

    private boolean isLowerHeard;

    public OctaveList() {
        lowerHeardIx = -1;
        isLowerHeard = false;
    }

    public int getLowerHeardIx() {
        return lowerHeardIx;
    }

    public void setLowerHeardIx(int ixHeard) {
        lowerHeardIx = ixHeard;
        // If -1 given (no octave heard),
        // set false. Otherwise true
        isLowerHeard = ixHeard != -1;
    }

    // Calculates if ix given is an octave up from the lower heard octave.
    public boolean isHigherOctave(int ix) {
        return ix == lowerHeardIx + MusicTheory.TOTAL_NOTES;
    }

    private boolean isLowerHeard() {
        return isLowerHeard;
    }

    private void setIsLowerHeard(boolean isHeard) {
        isLowerHeard = isHeard;
    }

}
