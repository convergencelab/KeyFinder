package com.example.keyfinder;

public class ActiveNoteList {

    private int[] noteCounts;

    private int[] keyStrengths;

    private int[] iterateSequence;

    private int size;

    public ActiveNoteList() {
        noteCounts = new int[MusicTheory.TOTAL_NOTES];
        keyStrengths = new int[MusicTheory.TOTAL_NOTES];
        size = 0;
    }

    public void addNote(int toAdd) {
        noteCounts[toAdd]++;
        size++;
        incrementKeyStrengths(toAdd);
    }

    public void removeNote(int toRemove) {
        noteCounts[toRemove]--;
        size--;
        decrementKeyStrengths(toRemove);
    }

    public int getNoteCount(int toCheck) {
        return noteCounts[toCheck];
    }

    public int getKeyStrength(int toCheck) {
        return keyStrengths[toCheck];
    }

    public boolean containsNote(int toCheck) {
        return noteCounts[toCheck] > 0;
    }

    public void clear() {
        size = 0;
        // Reset note counts & key strengths
        // Arrays are same size; 12
        for (int ix = 0; ix < noteCounts.length; ix++) {
            noteCounts[ix] = 0;
            keyStrengths[ix] = 0;
        }
    }

    /*
     * An iterate sequence is used so the algorithm will look at and modify ONLY the
     * keys containing the added / removed notes (exactly 7 keys for diatonic scales),
     * instead of performing a linear search through all 12 keys.
     */

    private void incrementKeyStrengths(int toAdd) {
        for (int interval : iterateSequence) {
            keyStrengths[(interval + toAdd) % MusicTheory.TOTAL_NOTES]++;
        }
    }

    private void decrementKeyStrengths(int toRemove) {
        for (int interval : iterateSequence) {
            keyStrengths[(interval + toRemove) % MusicTheory.TOTAL_NOTES]--;
        }
    }




}
