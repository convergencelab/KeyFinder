package com.example.keyfinder.eartraining;

import java.util.ArrayList;
import java.util.List;

public class PhraseTemplate {

    private List<Integer> tones;

    private int[] range;

    public PhraseTemplate() {
        this(new ArrayList<>());
    }

    public PhraseTemplate(List<Integer> tones) {
        this.tones = tones;
        range = new int[2];
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

    public int[] getRange() {
        return range;
    }

    private void findRange() {
        if (tones.isEmpty()) {
            range[0] = -1;
            range[1] = -1;
        }
        else {
            range[0] = range[1] = tones.get(0);
            for (int tone : tones) {
                if (tone < range[0]) {
                    range[0] = tone;
                }
                else if (tone > range[1]) {
                    range[1] = tone;
                }
            }
        }
    }

}
