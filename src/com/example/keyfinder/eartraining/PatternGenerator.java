package com.example.keyfinder.eartraining;

import com.example.keyfinder.Mode;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class PatternGenerator {

    private List<PhraseTemplate> phraseTemplates;

    private List<Mode> modes;

    public PatternGenerator() {
        phraseTemplates = new ArrayList<>();
        modes = new ArrayList<>();
    }



    public void addPhraseTemplate(PhraseTemplate toAdd) {
        phraseTemplates.add(toAdd);
    }

    public void removePhraseTemplate(PhraseTemplate toRemove) {
        phraseTemplates.remove(toRemove);
    }

    public void addMode(Mode mode) {
        modes.add(mode);
    }

    public void removeMovde(Mode mode) {
        modes.remove(mode);
    }

}
