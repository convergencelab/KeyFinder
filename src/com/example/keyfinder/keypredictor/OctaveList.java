package com.example.keyfinder.keypredictor;

import com.example.keyfinder.MusicTheory;

/**
 * TODO: documentation
 * Listens for octave phrase
 */
public class OctaveList {

    private int lowerHeardIx = -1;

    private int lowerBound = 48;

    private int upperBound = 59;

    private boolean isLowerHeard = false;

    public OctaveList() {
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

    public int getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
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
