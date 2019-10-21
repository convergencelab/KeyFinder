package com.example.keyfinder.eartraining;

import com.example.keyfinder.Mode;
import com.example.keyfinder.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pattern {

    private List<Note> notes;

    private int offset;

    private Mode mode;

    public Pattern() {
        this(new ArrayList<>());
    }

    public Pattern(List<Note> notes) {
        this.notes = notes;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void addNote(int degree) {
        getNotes().add(new Note(mode.getInterval(degree) + offset));
    }


    static public Pattern generatePattern(PhraseTemplate template, Mode mode, int rootIx) {
        Pattern toReturn = new Pattern();
        toReturn.offset = rootIx - mode.getInterval(template.getTones().get(0));
        toReturn.mode = mode;

        for (int degree : template.getTones()) {
            toReturn.getNotes().add(new Note(mode.getInterval(degree) + toReturn.offset));
        }

        return toReturn;
    }

    static public Pattern generatePattern(int degree, Mode mode, int rootIx) {
        Pattern toReturn = new Pattern();
        toReturn.offset = rootIx - mode.getInterval(degree);
        toReturn.mode = mode;

        toReturn.getNotes().add(new Note(mode.getInterval(degree) + toReturn.offset));

        return toReturn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pattern pattern = (Pattern) o;
        return Objects.equals(notes, pattern.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notes);
    }
}
