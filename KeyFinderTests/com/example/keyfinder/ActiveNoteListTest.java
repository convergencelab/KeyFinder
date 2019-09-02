package com.example.keyfinder;

import org.junit.Test;

import static org.junit.Assert.*;

public class ActiveNoteListTest {

    private static final int[] majorSequence = MusicTheory.PHRYGIAN_SCALE_SEQUENCE;

    private static final int[] melMinSequence = MusicTheory.DORIAN_FLAT2_SEQUENCE;

    @Test
    public void testSize() {
        ActiveNoteList list = new ActiveNoteList();
        list.setIterateSequence(majorSequence);

        assertEquals(0, list.size());

        list.addNote(0);
        assertEquals(1, list.size());

        list.addNote(0);
        assertEquals(2, list.size());

        list.removeNote(0);
        assertEquals(1, list.size());

        list.removeNote(0);
        assertEquals(0, list.size());

    }

}