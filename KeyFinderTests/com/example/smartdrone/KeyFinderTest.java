package com.example.smartdrone;

import org.junit.Assert;
import org.junit.Test;

public class KeyFinderTest {

    @Test
    public void addNoteToKeyFinder() {
        KeyFinder keyFinder = new KeyFinder();
        keyFinder.addNoteToList(0);
        Assert.assertTrue(keyFinder.activeNotesContain(0));
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
        Assert.assertTrue(keyFinder.activeNotesContain(0));

        Thread.sleep(2500);

        Assert.assertFalse(keyFinder.activeNotesContain(0));
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
        Assert.assertTrue(keyFinder.activeNotesContain(0));
        // Should be one active thread.
        Assert.assertEquals(keyFinder.getNoteThreadCount(), 1);

        Thread.sleep(2500);

        // List should not contain note anymore.
        Assert.assertFalse(keyFinder.activeNotesContain(0));
        // Should be no active threads.
        Assert.assertEquals(keyFinder.getNoteThreadCount(), 0);
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
        Assert.assertEquals(1, kf.getNoteThreadCount());

        // Cancel note removal
        kf.cancelNoteRemoval(curNote);
        // Should not be scheduled anymore.
        Assert.assertFalse(kf.noteIsScheduled(curNote));
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Should be 0.
        Assert.assertEquals(0, kf.getNoteThreadCount());
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
        Assert.assertNull(kf.getActiveKey());

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
        Assert.assertEquals(0, kf.getActiveNoteListSize());

        // Should have 0 active threads.
        Assert.assertEquals(0, kf.getNoteThreadCount());
    }

    @Test
    public void activeThreadTest() {
        KeyFinder kf = new KeyFinder();
        for (int i = 0; i < 10; i++) {
            if (kf.noteIsScheduled(0)) {
                kf.cancelNoteRemoval(0);
            }

            // Wait 25 millis.
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Add note to list.
            kf.addNoteToList(0);

            // Schedule timer.
            kf.scheduleNoteRemoval(0);

            System.out.println(kf.getNoteThreadCount());

            // Wait 25 millis.
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(kf.getNoteThreadCount());
        }
        System.out.println(kf.getNoteThreadCount());
    }
}