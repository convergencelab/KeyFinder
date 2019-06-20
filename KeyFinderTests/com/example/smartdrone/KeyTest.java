package com.example.smartdrone;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class KeyTest {

    @Test
    public void getIxTest() {
        Key key = new Key(0);
        Assert.assertEquals(key.getIx(), 0);
    }

    @Test
    public void defConstructorWorks() {
        Key defKey = new Key(0);
        Key otherKey = new Key(0, new NoteCollection());

        Assert.assertEquals(defKey.getIx(), otherKey.getIx());
        Assert.assertEquals(defKey.getName(), otherKey.getName());
    }

    @Test
    public void spellingCode() {
        Key key = new Key(0);
        Assert.assertEquals(key.getSpellingCode(), MusicTheory.SHARP_SPELLING_CODE);

        key = new Key(1);
        Assert.assertEquals(key.getSpellingCode(), MusicTheory.FLAT_SPELLING_CODE);

        key = new Key(11);
        Assert.assertEquals(key.getSpellingCode(), MusicTheory.SHARP_SPELLING_CODE);
    }

}