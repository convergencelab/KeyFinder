package com.example.keyfinder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NoteTest {

    // C
    @Test
    public void testNote0() {
        int testIx = 0;
        String nameFlat = "C";
        String nameSharp = "C";

        Note note = new Note(testIx);

        assertEquals(nameFlat, note.getNameFlat());
        assertEquals(nameSharp, note.getNameSharp());

        assertEquals(nameFlat, note.getName());
        note.setNameSharp();
        assertEquals(nameSharp, note.getName());
        note.setNameFlat();
        assertEquals(nameFlat, note.getName());
    }

    // C# / Db
    @Test
    public void testNote1() {
        int testIx = 1;
        String nameFlat = "D" + MusicTheory.FLAT;
        String nameSharp = "C" + MusicTheory.SHARP;

        Note note = new Note(testIx);

        assertEquals(nameFlat, note.getNameFlat());
        assertEquals(nameSharp, note.getNameSharp());

        assertEquals(nameFlat, note.getName());
        note.setNameSharp();
        assertEquals(nameSharp, note.getName());
        note.setNameFlat();
        assertEquals(nameFlat, note.getName());
    }

    // D
    @Test
    public void testNote2() {
        int testIx = 2;
        String nameFlat = "D";
        String nameSharp = "D";

        Note note = new Note(testIx);

        assertEquals(nameFlat, note.getNameFlat());
        assertEquals(nameSharp, note.getNameSharp());

        assertEquals(nameFlat, note.getName());
        note.setNameSharp();
        assertEquals(nameSharp, note.getName());
        note.setNameFlat();
        assertEquals(nameFlat, note.getName());
    }

    // D# / Eb
    @Test
    public void testNote3() {
        int testIx = 3;
        String nameFlat = "E" + MusicTheory.FLAT;
        String nameSharp = "D" + MusicTheory.SHARP;

        Note note = new Note(testIx);

        assertEquals(nameFlat, note.getNameFlat());
        assertEquals(nameSharp, note.getNameSharp());

        assertEquals(nameFlat, note.getName());
        note.setNameSharp();
        assertEquals(nameSharp, note.getName());
        note.setNameFlat();
        assertEquals(nameFlat, note.getName());
    }

    // C1
    @Test
    public void testNote12() {
        int testIx = 12;
        String nameFlat = "C";
        String nameSharp = "C";

        Note note = new Note(testIx);

        assertEquals(nameFlat, note.getNameFlat());
        assertEquals(nameSharp, note.getNameSharp());

        assertEquals(nameFlat, note.getName());
        note.setNameSharp();
        assertEquals(nameSharp, note.getName());
        note.setNameFlat();
        assertEquals(nameFlat, note.getName());
    }

    // C2
    @Test
    public void testNote24() {
        int testIx = 24;
        String nameFlat = "C";
        String nameSharp = "C";

        Note note = new Note(testIx);

        assertEquals(nameFlat, note.getNameFlat());
        assertEquals(nameSharp, note.getNameSharp());

        assertEquals(nameFlat, note.getName());
        note.setNameSharp();
        assertEquals(nameSharp, note.getName());
        note.setNameFlat();
        assertEquals(nameFlat, note.getName());
    }

    // Eb1
    @Test
    public void testNote15() {
        int testIx = 15;
        String nameFlat = "E" + MusicTheory.FLAT;
        String nameSharp = "D" + MusicTheory.SHARP;

        Note note = new Note(testIx);

        assertEquals(nameFlat, note.getNameFlat());
        assertEquals(nameSharp, note.getNameSharp());

        assertEquals(nameFlat, note.getName());
        note.setNameSharp();
        assertEquals(nameSharp, note.getName());
        note.setNameFlat();
        assertEquals(nameFlat, note.getName());
    }

}