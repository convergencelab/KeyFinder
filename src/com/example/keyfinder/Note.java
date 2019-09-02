package com.example.keyfinder;

public class Note {

    private final int ix;

    private final String nameFlat;

    private final String nameSharp;

    private String curName;

    public Note(int ix) {
        this.ix = ix;
        this.nameSharp = MusicTheory.CHROMATIC_SCALE_SHARP[ix % MusicTheory.TOTAL_NOTES];
        this.nameFlat = MusicTheory.CHROMATIC_SCALE_FLAT[ix % MusicTheory.TOTAL_NOTES];
        this.curName = nameFlat; // Flat by default
    }

    public int getIx() {
        return ix;
    }

    public String getCurName() {
        return curName;
    }

    public String getNameFlat() {
        return nameFlat;
    }

    public String getNameSharp() {
        return nameSharp;
    }
}
