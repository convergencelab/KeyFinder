package com.example.keyfinder.keypredictor;

import com.example.keyfinder.MusicTheory;
import com.example.keyfinder.melody.Note;

public class PhrasePredictor extends KeyPredictor {

    // TODO: deal with default bounds
    private int lowerBound = 48;

    private int upperBound = 59;

    private Phrase userPhrase = new Phrase();

    private Phrase targetPhrase = null;

    @Override
    public void addNote(Note toAdd) {
        if (userPhrase.getNotes().size() > targetPhrase.getNotes().size()) {
            throw new IllegalStateException("User phrase size has exceeded target phrase size.");
        }
        else if (userPhrase.getNotes().size() == targetPhrase.getNotes().size()) {
            userPhrase.removeNoteAtIx(0);
        }
        userPhrase.addNote(toAdd);
        if (userPhraseMatchesTarget() && userPhraseIsWithinBounds()) {
            // TODO: make better
            // For now, the predicted key is always the index of the first note in the phrase.
            // (this works for octave phrase predictions, but won't necessarily work with other
            // phrase implementations).
            notifyListeners(userPhrase.getNotes().get(0).getIx() % MusicTheory.TOTAL_NOTES);
        }
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

    public Phrase getTargetPhrase() {
        return targetPhrase;
    }

    public void setTargetPhrase(Phrase targetPhrase) {
        this.targetPhrase = targetPhrase;
    }

    private boolean userPhraseMatchesTarget() {
        if (targetPhrase == null) {
            throw new IllegalStateException("Target Phrase not set.");
        }
        if (targetPhrase.getNotes().size() != userPhrase.getNotes().size()) {
            return false;
        }
        final int targetBase = targetPhrase.getNotes().get(0).getIx();
        final int userBase = userPhrase.getNotes().get(0).getIx();
        for (int i = 0; i < targetPhrase.getNotes().size(); i++) {
            if (targetPhrase.getNotes().get(i).getIx() - targetBase
                    != userPhrase.getNotes().get(i).getIx() - userBase) {
                return false;
            }
        }
        return true;
    }

    private boolean userPhraseIsWithinBounds() {
        // Both upper and lower bound are INCLUSIVE
        return userPhrase.getNotes().get(0).getIx() >= lowerBound
                && userPhrase.getNotes().get(0).getIx() <= upperBound;
    }


}
