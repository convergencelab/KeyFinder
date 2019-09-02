package com.example.keyfinder;

public class Key {

    private final int ix;

    private final Mode mode;

    private String name;

    public Key(int ix, Mode mode) {
        this.ix = ix;
        this.mode = mode;
        this.name = getNameFlat(); // Flat by default
    }

    public int getIx() {
        return ix;
    }

    public Mode getMode() {
        return mode;
    }

    public String getName() {
        return name;
    }

    public void setNameFlat() {
        name = getNameFlat();
    }

    public void setNameSharp() {
        name = getNameSharp();
    }

    public String getNameFlat() {
        return MusicTheory.CHROMATIC_SCALE_FLAT[ix] + " " + mode.getName();
    }

    public String getNameSharp() {
        return MusicTheory.CHROMATIC_SCALE_SHARP[ix] + " " + mode.getName();
    }

}
