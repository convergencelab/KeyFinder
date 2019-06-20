package com.example.smartdrone;

import org.junit.Assert;
import org.junit.Test;

public class NoteTest {

    private void noteTestTemplate(Note note, int ix, int rawIx, int octave, String name, String nameSharp, String nameFlat) {
        Assert.assertEquals(ix, note.getIx());
        Assert.assertEquals(rawIx, note.getRawIx());
        Assert.assertEquals(octave, note.getOctave());
        Assert.assertEquals(name, note.getName());
        Assert.assertEquals(nameSharp, note.getNameSharp());
        Assert.assertEquals(nameFlat, note.getNameFlat());
    }

    @Test
    public void cTest1() {
        Note note = new Note(0);
        noteTestTemplate(note, 0, 0, 0, "C", "C", "C");
    }

    @Test
    public void cTest2() {
        Note note = new Note(12);
        noteTestTemplate(note, 0, 12, 1, "C", "C", "C");
    }

    @Test
    public void cTest3() {
        Note note = new Note(36);
        noteTestTemplate(note, 0, 36, 3, "C", "C", "C");
    }

    @Test
    public void cSharpTest1() {
        Note note = new Note(1);
        noteTestTemplate(note, 1, 1, 0, "C" + MusicTheory.SHARP,
                "C" + MusicTheory.SHARP, "D" + MusicTheory.FLAT);
    }

    @Test
    public void cSharpTest2() {
        Note note = new Note(25);
        noteTestTemplate(note, 1, 25, 2, "C" + MusicTheory.SHARP,
                "C" + MusicTheory.SHARP, "D" + MusicTheory.FLAT);
    }

    @Test
    public void bTest1() {
        Note note = new Note(23);
        noteTestTemplate(note, 11, 23, 1, "B", "B", "B");
    }
}