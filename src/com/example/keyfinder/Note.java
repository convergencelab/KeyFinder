package com.example.keyfinder;

public class Note {

    private final int ix;

    // Can be changed between sharp and flat
    private String curName;

    public Note(int ix) {
        this.ix = ix;
        this.curName = MusicTheory.CHROMATIC_SCALE_FLAT[ix % MusicTheory.TOTAL_NOTES]; // Flat by default
    }

    public int getIx() {
        return ix;
    }

    public String getCurName() {
        return curName;
    }

    public void setNameFlat() {
        curName = MusicTheory.CHROMATIC_SCALE_FLAT[ix % MusicTheory.TOTAL_NOTES];
    }

    public void setNameSharp() {
        curName = MusicTheory.CHROMATIC_SCALE_SHARP[ix % MusicTheory.TOTAL_NOTES];
    }

    public String getNameFlat() {
        return MusicTheory.CHROMATIC_SCALE_FLAT[ix % MusicTheory.TOTAL_NOTES];
    }

    public String getNameSharp() {
        return MusicTheory.CHROMATIC_SCALE_SHARP[ix % MusicTheory.TOTAL_NOTES];
    }
}
