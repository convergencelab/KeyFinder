package com.example.keyfinder.eartraining;

import java.util.ArrayList;
import java.util.List;

public class PhraseTemplate {

    private List<Integer> tones;

    private int lowestDegree;

    private int highestDegree;

    public PhraseTemplate() {
        this(new ArrayList<>());
    }

    public PhraseTemplate(List<Integer> tones) {
        this.tones = tones;
        lowestDegree = 0;
        highestDegree = 0;
        findRange();
    }

    public List<Integer> getTones() {
        return tones;
    }

    public void setTones(List<Integer> tones) {
        this.tones = tones;
        findRange();
    }

    public int numTones() {
        return tones.size();
    }

    public int getLowestDegree() {
        return lowestDegree;
    }

    public int getHighestDegree() {
        return highestDegree;
    }

    public void addDegree(int degree) {
        tones.add(degree);

        // Edge case, going from 0 to 1 nodes
        if (tones.size() == 1) {
            lowestDegree = highestDegree = tones.get(0);
        }

        else if (degree < lowestDegree) {
            lowestDegree = degree;
        }
        else if (degree > highestDegree) {
            highestDegree = degree;
        }
    }

    private void findRange() {
        if (tones.isEmpty()) {
            lowestDegree = -1;
            highestDegree = -1;
        }
        else {
            lowestDegree = highestDegree = tones.get(0);
            for (int tone : tones) {
                if (tone < lowestDegree) {
                    lowestDegree = tone;
                }
                else if (tone > highestDegree) {
                    highestDegree = tone;
                }
            }
        }
    }
}
