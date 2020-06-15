package com.example.keyfinder;

import org.junit.Assert;
import org.junit.Test;

public class ActiveNoteListTest {

    private static final int[] majorSequence = MusicTheory.PHRYGIAN_SCALE_SEQUENCE;

    private static final int[] melMinSequence = MusicTheory.DORIAN_FLAT2_SEQUENCE;

    @Test
    public void addNoteC_activeNoteList() {
        ActiveNoteList anl = new ActiveNoteList();
        anl.setIterateSequence(majorSequence);
        anl.addNote(0);
        boolean[] expected = {
                true, false, false, false, false, false,
                false, false, false, false, false, false
        };
        Assert.assertArrayEquals(expected, anl.getNoteActiveList());
    }

    @Test
    public void addNoteC_noteWeightList() {
        ActiveNoteList anl = new ActiveNoteList();
        anl.setIterateSequence(majorSequence);
        anl.addNote(0);
        int[] expected = {
                1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        };
        Assert.assertArrayEquals(expected, anl.getNoteWeightList());
    }

    @Test
    public void addNoteC_keyStrengthList() {
        ActiveNoteList anl = new ActiveNoteList();
        anl.setIterateSequence(majorSequence);
        anl.addNote(0);
        int[] expected = {
                1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0
        };
        Assert.assertArrayEquals(expected, anl.getKeyStrengths());
    }

    @Test
    public void addNoteC_keyWeightList() {
        ActiveNoteList anl = new ActiveNoteList();
        anl.setIterateSequence(majorSequence);
        anl.addNote(0);
        int[] expected = {
                1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0
        };
        Assert.assertArrayEquals(expected, anl.getKeyWeights());
    }

    @Test
    public void addNoteC_maxKeyStrength() {
        ActiveNoteList anl = new ActiveNoteList();
        anl.setIterateSequence(majorSequence);
        anl.addNote(0);
        Assert.assertEquals(anl.getMaxKeyStrength(), 1);
    }

    @Test
    public void addNoteC_maxKeyWeight() {
        ActiveNoteList anl = new ActiveNoteList();
        anl.setIterateSequence(majorSequence);
        anl.addNote(0);
        Assert.assertEquals(anl.getMaxKeyWeight(), 1);
    }

    @Test
    public void addNoteCTwice_activeNoteList() {
        ActiveNoteList anl = new ActiveNoteList();
        anl.setIterateSequence(majorSequence);
        anl.addNote(0);
        anl.addNote(0);
        boolean[] expected = {
                true, false, false, false, false, false,
                false, false, false, false, false, false
        };
        Assert.assertArrayEquals(expected, anl.getNoteActiveList());
    }

    @Test
    public void addNoteCTwice_noteWeightList() {
        ActiveNoteList anl = new ActiveNoteList();
        anl.setIterateSequence(majorSequence);
        anl.addNote(0);
        anl.addNote(0);
        int[] expected = {
                2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        };
        Assert.assertArrayEquals(expected, anl.getNoteWeightList());
    }

    @Test
    public void addNoteCTwice_keyStrengthList() {
        ActiveNoteList anl = new ActiveNoteList();
        anl.setIterateSequence(majorSequence);
        anl.addNote(0);
        anl.addNote(0);
        int[] expected = {
                1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0
        };
        Assert.assertArrayEquals(expected, anl.getKeyStrengths());
    }

    @Test
    public void addNoteCTwice_keyWeightList() {
        ActiveNoteList anl = new ActiveNoteList();
        anl.setIterateSequence(majorSequence);
        anl.addNote(0);
        anl.addNote(0);
        int[] expected = {
                2, 2, 0, 2, 0, 2, 0, 2, 2, 0, 2, 0
        };
        Assert.assertArrayEquals(expected, anl.getKeyWeights());
    }

    @Test
    public void addNoteCTwice_maxKeyStrength() {
        ActiveNoteList anl = new ActiveNoteList();
        anl.setIterateSequence(majorSequence);
        anl.addNote(0);
        anl.addNote(0);
        Assert.assertEquals(anl.getMaxKeyStrength(), 1);
    }

    @Test
    public void addNoteCTwice_maxKeyWeight() {
        ActiveNoteList anl = new ActiveNoteList();
        anl.setIterateSequence(majorSequence);
        anl.addNote(0);
        anl.addNote(0);
        Assert.assertEquals(anl.getMaxKeyWeight(), 2);
    }

    @Test
    public void addMultipleNotesThenRemove() {
        ActiveNoteList anl = new ActiveNoteList();
        anl.setIterateSequence(majorSequence);
        anl.addNote(0);
        anl.addNote(0);
        anl.addNote(0);
        anl.addNote(0);
        anl.addNote(0);
        anl.addNote(0);
        anl.removeNote(0);
        int[] expected = {
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        };
        Assert.assertArrayEquals(expected, anl.getKeyWeights());
    }

}