package com.example.keyfinder.eartraining;

import com.example.keyfinder.Note;

public class Pattern {

    private final Note[] notes;

    public Pattern(Note[] notes) {
        this.notes = notes;
    }

    public Note[] getNotes() {
        return notes;
    }

}
