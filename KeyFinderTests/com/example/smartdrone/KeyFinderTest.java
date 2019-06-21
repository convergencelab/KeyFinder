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

    @Test
    public void noteIsScheduled() {
        KeyFinder kf = new KeyFinder();
        Note curNote = kf.getNote(0);

        // Add note to list
        kf.addNoteToList(curNote);
        // Schedule removal
        kf.scheduleNoteRemoval(curNote);
        // Should be scheduled
        Assert.assertTrue(kf.noteIsScheduled(curNote));

        // Cancel note removal
        kf.cancelNoteRemoval(curNote);
        // Should not be scheduled anymore.
        Assert.assertFalse(kf.noteIsScheduled(curNote));
    }

    @Test
    public void noteIsScheduledThreadCount() {
        KeyFinder kf = new KeyFinder();
        Note curNote = kf.getNote(0);

        // Add note to list
        kf.addNoteToList(curNote);
        // Schedule removal
        kf.scheduleNoteRemoval(curNote);
        // Should be scheduled
        Assert.assertTrue(kf.noteIsScheduled(curNote));
        // Should be one.
        Assert.assertEquals(1, kf.getNoteTimerPool().getActiveCount());

        // Cancel note removal
        kf.cancelNoteRemoval(curNote);
        // Should not be scheduled anymore.
        Assert.assertFalse(kf.noteIsScheduled(curNote));
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Should be 0.
        Assert.assertEquals(0, kf.getNoteTimerPool().getActiveCount());
    }

    @Test
    public void cancelNoteTimer() {
        KeyFinder kf = new KeyFinder();
        Note curNote = kf.getNote(0);

        // Add note to list
        kf.addNoteToList(curNote);
        // Schedule removal
        kf.scheduleNoteRemoval(curNote);
        if (kf.noteIsScheduled(curNote)) {
            kf.cancelNoteRemoval(curNote);
        }
    }

    @Test
    public void keyFinderNoteIo() {
        KeyFinder kf = new KeyFinder();

        // Add all notes to make C major.
        kf.addNoteToList(0);  // C
        kf.addNoteToList(4);  // E
        kf.addNoteToList(5);  // F
        kf.addNoteToList(11); // B

        // Active key should be null.
        Assert.assertEquals(null, kf.getActiveKey());

        // Wait for active key delay.
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Active key should be C major.
        Assert.assertEquals(kf.getMajorKey(0), kf.getActiveKey());

        // Removal all notes from key finder.
        kf.scheduleNoteRemoval(0);  // C
        kf.scheduleNoteRemoval(4);  // E
        kf.scheduleNoteRemoval(5);  // F
        kf.scheduleNoteRemoval(11); // B

        // Wait for removals to carry out.
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Active notes should be empty.
        Assert.assertEquals(0, kf.getActiveNotes().size());

        // Should have 0 active threads.
        Assert.assertEquals(0, kf.getNoteTimerPool().getActiveCount());
    }
}