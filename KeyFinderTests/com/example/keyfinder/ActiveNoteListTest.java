package com.example.keyfinder;

import org.junit.Test;

import static org.junit.Assert.*;

public class ActiveNoteListTest {

    private static final int[] majorSequence = MusicTheory.PHRYGIAN_SCALE_SEQUENCE;

    private static final int[] melMinSequence = MusicTheory.DORIAN_FLAT2_SEQUENCE;

    @Test
    public void testSize() {
        ActiveNoteList list = makeTestListMajor();

        assertEquals(0, list.numActiveNotes());

        list.addNote(0);
        assertEquals(1, list.numActiveNotes());

        list.addNote(0);
        assertEquals(2, list.numActiveNotes());

        list.removeNote(0);
        assertEquals(1, list.numActiveNotes());

        list.removeNote(0);
        assertEquals(0, list.numActiveNotes());
    }

    @Test
    public void testNumActiveNotes() {
        /* Could use any notes for testing */

        ActiveNoteList list = makeTestListMajor();
        assertEquals(0, list.numActiveNotes());

        // Add 'C'
        list.addNote(0);
        assertEquals(1, list.numActiveNotes());

        // Add 'C'
        list.addNote(0);
        assertEquals(2, list.numActiveNotes());

        // Add 'C#' / 'Db'
        list.addNote(1);
        assertEquals(3, list.numActiveNotes());

        // Remove 'C#' / 'Db'
        list.removeNote(1);
        assertEquals(2, list.numActiveNotes());

        // Remove 'C'
        list.removeNote(0);
        assertEquals(1, list.numActiveNotes());

        // Remove 'C'
        list.removeNote(0);
        assertEquals(0, list.numActiveNotes());
    }

    @Test
    public void testAddNote() {
        ActiveNoteList list = makeTestListMajor();

        int[] expectedKeyStrengths = new int[MusicTheory.TOTAL_NOTES];
        int[] expectedNoteCounts   = new int[MusicTheory.TOTAL_NOTES];

        // Test note count
        for (int i = 0; i < expectedNoteCounts.length; i++) {
            assertEquals(expectedNoteCounts[i], list.getNoteCount(i));
        }

        // Test key strength
        for(int i = 0; i < expectedKeyStrengths.length; i++) {
            assertEquals(expectedKeyStrengths[i], list.getNoteCount(i));
        }

        // Add C
        list.addNote(0);
        expectedKeyStrengths = new int[]{ 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0 };
        expectedNoteCounts   = new int[]{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

        for (int i = 0; i < expectedNoteCounts.length; i++) {
            assertEquals(expectedNoteCounts[i], list.getNoteCount(i));
        }

        for(int i = 0; i < expectedKeyStrengths.length; i++) {
            assertEquals(expectedKeyStrengths[i], list.getKeyStrength(i));
        }
    }

    // Just to encapsulate the setup
    private ActiveNoteList makeTestListMajor() {
        ActiveNoteList list = new ActiveNoteList();
        list.setIterateSequence(majorSequence);
        return list;
    }

}