package com.example.keyfinder.eartraining;

import java.util.ArrayList;
import java.util.List;

/**
 * Class holds onto harmonic data.
 * Orders it's tones.
 */
public class IntervalTemplate {

    private List<Integer> tones;

    private int[] range;

    public IntervalTemplate() {
        this(new ArrayList<>());
    }

    public IntervalTemplate(List<Integer> tones) {
        this.tones = tones;
        // todo sort data
        range = new int[2];
        findRange();
    }

    public void setTones(int[] tones) {
        // todo: convert array to list
    }

    public void setTones(List<Integer> tones) {
        this.tones = tones;
        // todo: sort
        findRange();
    }

    public List<Integer> getTonesAscending() {
        return tones;
    }

    public List<Integer> getTonesDescending() {
        List<Integer> toReturn = new ArrayList<>();
        for (int i = tones.size() - 1; i > 0; i--) {
            toReturn.add(tones.get(i));
        }
        return toReturn;
    }

    public List<Integer> getTonesRandomized() {
        // Todo: shuffle list
        return null;
    }

    public int numTones() {
        return tones.size();
    }

    // Todo: implement equals
    private void findRange() {
        if (tones.isEmpty()) {
            range[0] = -1;
            range[1] = -1;
        }
        else {
            // tones list is sorted
            range[0] = tones.get(0);
            range[1] = tones.get(tones.size() - 1);
        }
    }

    public int[] getRange() {
        return range;
    }



}
