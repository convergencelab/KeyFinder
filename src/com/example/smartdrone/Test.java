package com.example.smartdrone;

import java.util.LinkedList;
import java.util.Scanner;

public class Test {
    /**
     * Print out contents of key strength array.
     * @param       keyFinder keyFinder object.
     */
    static public void printKeyStrengths(KeyFinder keyFinder) {
        // For each key.
        System.out.println("  KEY STRENGTHS: ");
        for (int i = 0; i < MusicTheory.TOTAL_NOTES; i++) {
            // Print out key center name, followed by key center strength.

            // *keyIx* *keyName* : *keyStrength*
            System.out.printf("    [%2d] %-2s : %d\n", keyFinder.getAllKeys().getMajorKeyAtIndex(i).getIx(),
                    keyFinder.getAllKeys().getMajorKeyAtIndex(i).getName(),
                    keyFinder.getAllKeys().getMajorKeyAtIndex(i).getStrength());


            // Forgotten code
            /* System.out.println(MusicTheory.CHROMATIC_SCALE[i] + "(" + i + "): " +
                    keyFinder.getAllKeys().getMajorKeys()[i].getStrength()); */

            // System.out.printf("[%d]")
        }
    }

    /**
     * Function to test the increment function from the KeyFinder class.
     * Takes user input, increments corresponding index of _keyStrength array.
     */
    static public void incrementKeysStrengthTest1() {
        // Create KeyFinder object
        KeyFinder kf = new KeyFinder();
        Scanner in = new Scanner(System.in);
        System.out.print("Enter note: ");
        int userNote = in.nextInt();
        // Take user input
        while (userNote != 99) {
            System.out.println("Testing note " + MusicTheory.CHROMATIC_SCALE[userNote] + ":");
            kf.incrementKeysWithNote(new Note(userNote));
            printKeyStrengths(kf);
            System.out.print("Enter note: ");
            userNote = in.nextInt();
        }
    }

    /**
     * Function to test the increment and decrement function from the KeyFinder class.
     * Takes user input, increments and decrements corresponding index of _keyStrength array.
     */
    static public void incrAndDecrKeysStrengthTest1() {
        // Create KeyFinder object
        KeyFinder kf = new KeyFinder();
        Scanner in = new Scanner(System.in);
        System.out.print("Enter note: ");
        int userNote = in.nextInt();
        // Take user input
        while (userNote != 99) {
            System.out.println("Incrementing note " + MusicTheory.CHROMATIC_SCALE[userNote] + ":");
            kf.incrementKeysWithNote(new Note(userNote));
            printKeyStrengths(kf);
            System.out.print("Enter note: ");
            userNote = in.nextInt();
            System.out.println("Decrementing note " + MusicTheory.CHROMATIC_SCALE[userNote] + ":");
            kf.decrementKeysWithNote(new Note(userNote));
            printKeyStrengths(kf);
            System.out.print("Enter note: ");
            userNote = in.nextInt();
        }
    }

    static public void printNoteList(LinkedList<Note> noteList) {
        if (noteList.isEmpty()) {
            throw new IllegalArgumentException("List must not be empty!");
        }
        System.out.print("Current Queue: ");
        for (Note n : noteList) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    static public void addNoteToListTest1() {
        KeyFinder kf = new KeyFinder();
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a note: ");
        int curNoteIx = in.nextInt();
        while(curNoteIx != 99) {
            kf.addNoteToList(new Note(curNoteIx));
            printNoteList(kf.getActiveNotes());
            System.out.println("Enter a note.");
            curNoteIx = in.nextInt();
        }
    }

    static public void addAndRemoveNoteFromListTest1() {
        KeyFinder kf = new KeyFinder();
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a note: ");
        int curNoteIx = in.nextInt();
        while(curNoteIx != 99) {
            // Add note.
            System.out.println("Adding Note '" + MusicTheory.CHROMATIC_SCALE[curNoteIx] +
                    "' to list.");
            kf.addNoteToList(new Note(curNoteIx));
            printNoteList(kf.getActiveNotes());
            // Remove note.
            System.out.println("Enter a note.");
            curNoteIx = in.nextInt();
            System.out.println("Removing Note '" + MusicTheory.CHROMATIC_SCALE[curNoteIx] +
                    "' from list.");
            kf.removeNoteFromList(new Note(curNoteIx));
            printNoteList(kf.getActiveNotes());
            curNoteIx = in.nextInt();
        }
    }

    /**
     * Because it's so damn hard to type every note individually.
     * @param keyIx
     * @param keyFinder
     */
    static public void addAllNotesFromKey(int keyIx, KeyFinder keyFinder) {
        Key curKey = keyFinder.getAllKeys().getMajorKeyAtIndex(keyIx);
        for (Note curNote : curKey.getNotes()) {
            keyFinder.addNoteToList(curNote);
            try
            {
                Thread.sleep(250);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
    }

    static public int promptUserForNoteIndex() {
        Scanner in = new Scanner(System.in);
        int userNoteIx;
        // Prompt user for note index.
        System.out.print("Please enter a note: ");
        userNoteIx = in.nextInt();
        return userNoteIx;
    }

    static public int promptUserForNoteEffect() {
        Scanner in = new Scanner(System.in);
        int userEffect;
        // Prompt user for note effect.
        System.out.print("Add(0) or Remove(1): ");
        userEffect = in.nextInt();
        return userEffect;
    }

    static public void printActiveKey(KeyFinder kf) {
        System.out.print("  ACTIVE KEY: ");
        if (kf.getActiveKey() == null) {
            System.out.println("null");
            return;
        }
        System.out.println(kf.getActiveKey().getName());
    }

    static public void printActiveNoteList(KeyFinder kf) {
        System.out.print("  ACTIVE NOTE LIST: ");
        System.out.println(kf.getActiveNotes());
    }

    static public void pickActiveKeyTest1() {
        // Create KeyFinder object.
        KeyFinder kf = new KeyFinder();
        Scanner in = new Scanner(System.in);
        Note curNote;
        int userNoteIx = promptUserForNoteIndex();
        int userNoteEffect = promptUserForNoteEffect();
        while (userNoteIx != -1) {
            System.out.println();
            // Construct Note.
            curNote = new Note(userNoteIx);
            // Add to list.
            if (userNoteEffect == 0) {
                kf.addNoteToList(curNote);
            }
            // Remove from list.
            else {
                kf.removeNoteFromList(curNote);
            }
            // Print out all the keys strength.
            printKeyStrengths(kf);
            printActiveKey(kf);
            printActiveNoteList(kf);
            // Prompt user again
            System.out.println();
            userNoteIx = promptUserForNoteIndex();
            userNoteEffect = promptUserForNoteEffect();
        }
    }

    static public void pickActiveKeyTest2() {
        // Create KeyFinder object.
        KeyFinder kf = new KeyFinder();
        Note curNote;
        int userNoteIx = promptUserForNoteIndex();
        while (userNoteIx != -1) {
            System.out.println();
            // Construct Note.
            curNote = kf.getAllNotes().getNoteAtIndex(userNoteIx);
            // Add to list.
            kf.addNoteToList(curNote);
            // Print out all the keys strength.
                pickActiveKeyTestPrintInfo(kf);
            // Prompt user again
            System.out.println();
            userNoteIx = promptUserForNoteIndex();
        }
    }

    static public void pickActiveKeyTest3() {
        // Create KeyFinder object.
        KeyFinder kf = new KeyFinder();
        Note curKey;
        int userKeyIx = promptUserForNoteIndex();
        while (userKeyIx != -1) {
            System.out.println();
            // Construct Note.
            curKey = kf.getAllNotes().getNoteAtIndex(userKeyIx);
            // Add to list.
            addAllNotesFromKey(userKeyIx, kf);
            // Print out all the keys strength.
            pickActiveKeyTestPrintInfo(kf);
            // Prompt user again
            System.out.println();
            userKeyIx = promptUserForNoteIndex();
        }
    }

    static public void pickActiveKeyTestPrintInfo(KeyFinder kf) {
        printKeyStrengths(kf);
        printActiveKey(kf);
        printActiveNoteList(kf);
    }
}
