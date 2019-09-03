package com.example.keyfinder;

/**
 * ActiveNoteList.java actually does something more than hold onto data.
 *
 * This class can track note i/o, count how many of each note, and track the correlation
 * between active notes and key strength.
 *
 * When a note is added or removed, the strength of keys containing that note are
 * incremented or decremented, respectively. These keys can be known ahead of time by using an
 * iterate sequence. For example: all major keys containing the note 'C' correspond with C Phrygian
 * (C, Db, Eb, F, G, Ab, Bb) - this true for any note, not just 'C'.
 * there is no need to perform a linear search through all 12 keys in this case.
 * (For melodic minor, use Phrygian #6)
 *
 */
public class ActiveNoteList {

    private int[] noteCounts;

    private int[] keyStrengths;

    private int[] iterateSequence;

    private int numActiveNotes;

    public ActiveNoteList() {
        noteCounts = new int[MusicTheory.TOTAL_NOTES];
        keyStrengths = new int[MusicTheory.TOTAL_NOTES];
        numActiveNotes = 0;
    }

    public void setIterateSequence(int[] iterateSequence) {
        this.iterateSequence = iterateSequence;
    }

    public void addNote(int toAdd) {
        noteCounts[toAdd]++;
        numActiveNotes++;
        incrementKeyStrengths(toAdd);
    }

    public void removeNote(int toRemove) {
        if (noteCounts[toRemove] == 0) {
            // Todo: Exception?
            return;
        }
        noteCounts[toRemove]--;
        numActiveNotes--;
        decrementKeyStrengths(toRemove);
    }

    public int getNoteCount(int toCheck) {
        return noteCounts[toCheck];
    }

    public int getKeyStrength(int toCheck) {
        return keyStrengths[toCheck];
    }

    public int getMaxKeyStrength() {
        int max = -1;
        for (int strength : keyStrengths) {
            if (strength > max) {
                max = strength;
            }
        }
        return max;
    }

    public boolean containsNote(int toCheck) {
        return noteCounts[toCheck] > 0;
    }

    public int numActiveNotes() {
        return numActiveNotes;
    }

    public void clear() {
        numActiveNotes = 0;
        // Reset note counts & key strengths
        // Arrays are same numActiveNotes; 12
        for (int ix = 0; ix < noteCounts.length; ix++) {
            noteCounts[ix] = 0;
            keyStrengths[ix] = 0;
        }
    }

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
