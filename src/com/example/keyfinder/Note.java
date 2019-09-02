package com.example.keyfinder;

public class Note {

    private final int ix;

    private String name;

    public Note(int ix) {
        this.ix = ix;
        this.name = getNameFlat(); // Flat by default
    }

    public int getIx() {
        return ix;
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
        return MusicTheory.CHROMATIC_SCALE_FLAT[ix % MusicTheory.TOTAL_NOTES];
    }

    public String getNameSharp() {
        return MusicTheory.CHROMATIC_SCALE_SHARP[ix % MusicTheory.TOTAL_NOTES];
    }
}
