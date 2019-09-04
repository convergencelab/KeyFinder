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
    public void testAddNote() {
        ActiveNoteList list = makeTestListMajor();

        int[] expectedKeyStrengths = new int[MusicTheory.TOTAL_NOTES];
        int[] expectedNoteCounts = new int[MusicTheory.TOTAL_NOTES];
        for(int i = 0; i < expectedKeyStrengths.length; i++) {
            assertEquals(expectedKeyStrengths[i], list.getNoteCount(i));
        }

        // Add C
        list.addNote(0);
        expectedKeyStrengths = new int[]{ 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0 };

        // Test note count
        for (int i = 0; i < expectedNoteCounts.length; i++) {
//            assertEquals(expectedNoteCounts[i], /* here */);
        }

        // Test key strengths
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

//    private boolean compareKeyStrength(int[] exp)

}