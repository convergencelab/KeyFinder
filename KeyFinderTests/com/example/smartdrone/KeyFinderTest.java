package com.example.smartdrone;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class KeyFinderTest {

    @Test
    public void addNoteToKeyFinder() {
        KeyFinder keyFinder = new KeyFinder();
        keyFinder.addNoteToList(0);
        Assert.assertTrue(keyFinder.getActiveNotes().contains(keyFinder.getNote(0)));
    }

    @Test
    public void scheduleNoteRemoval() throws InterruptedException {
        KeyFinder keyFinder = new KeyFinder();
        Note curNote = keyFinder.getNote(0);

        // Add note to list.
        keyFinder.addNoteToList(curNote);
        // Schedule removal
        keyFinder.scheduleNoteRemoval(curNote);
        // Note is still in list.
        Assert.assertTrue(keyFinder.getActiveNotes().contains(keyFinder.getNote(0)));

        Thread.sleep(2500);

        Assert.assertFalse(keyFinder.getActiveNotes().contains(keyFinder.getNote(0)));
    }

    @Test
    public void activeThreadCount1() throws InterruptedException {
        KeyFinder keyFinder = new KeyFinder();
        Note curNote = keyFinder.getNote(0);

        // Add note to list.
        keyFinder.addNoteToList(curNote);
        // Schedule removal
        keyFinder.scheduleNoteRemoval(curNote);
        // Note is still in list.
        Assert.assertTrue(keyFinder.getActiveNotes().contains(keyFinder.getNote(0)));
        // Should be one active thread.
        Assert.assertEquals(keyFinder.getNoteTimerPool().getActiveCount(), 1);

        Thread.sleep(2500);

        // List should not contain note anymore.
        Assert.assertFalse(keyFinder.getActiveNotes().contains(keyFinder.getNote(0)));
        // Should be no active threads.
        Assert.assertEquals(keyFinder.getNoteTimerPool().getActiveCount(), 0);
    }
}