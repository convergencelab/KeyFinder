package com.example.keyfinder.eartraining;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PhrasePatternGenerator {

    private List<PhraseTemplate> phraseTemplates;

    private int lowerBound;

    private int upperBonud;

    private Random rng;

    public PhrasePatternGenerator() {
        this(-1, -1);
    }

    public PhrasePatternGenerator(int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBonud = upperBound;
        phraseTemplates = new ArrayList<>();
        rng = new Random();
    }

    public void addPhraseTemplate(PhraseTemplate toAdd) {
        // Todo: check duplicates
        phraseTemplates.add(toAdd);
    }

    public void removePhraseTemplate(PhraseTemplate toRemove) {
        phraseTemplates.remove(toRemove);
    }

    public Pattern generatePattern() {
        int patternIx = rng.nextInt(phraseTemplates.size());
        PhraseTemplate curPattern = phraseTemplates.get(patternIx);

        return null;
    }


}
