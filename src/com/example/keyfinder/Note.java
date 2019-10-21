package com.example.keyfinder;

import java.util.Objects;

public class Note {

    private final int ix;

    // Same notes of a different octave will have the same note value
    private final int noteValue;

    private String name;

    public Note(int ix) {
        this.ix = ix;
        this.noteValue = ix % MusicTheory.TOTAL_NOTES;
        this.name = getNameFlat(); // Flat by default
    }

    public int getIx() {
        return ix;
    }

    public int getNoteValue() {
        return noteValue;
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
        return MusicTheory.CHROMATIC_SCALE_FLAT[noteValue];
    }

    public String getNameSharp() {
        return MusicTheory.CHROMATIC_SCALE_SHARP[noteValue];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return ix == note.ix;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ix);
    }
}
