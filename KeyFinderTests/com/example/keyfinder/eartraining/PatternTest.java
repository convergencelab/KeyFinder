package com.example.keyfinder.eartraining;

import com.example.keyfinder.MajorMode;
import com.example.keyfinder.MelodicMinorMode;
import com.example.keyfinder.Note;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PatternTest {

    @Test
    public void testGeneratePattern_root() {
        PhraseTemplate template = new PhraseTemplate();

        // Add pattern degrees
        template.addDegree(0);

        // Select mode and key
        Pattern pattern = Pattern.generatePattern(template, new MajorMode(0), 48);

        // Put answer
        List<Note> ans = new ArrayList<>();
        ans.add(new Note(48));

        Assert.assertEquals(ans, pattern.getNotes());
    }

    @Test
    public void testGeneratePattern_rootSecond() {
        PhraseTemplate template = new PhraseTemplate();

        // Add pattern degrees
        template.addDegree(0);
        template.addDegree(1);

        // Select mode and key
        Pattern pattern = Pattern.generatePattern(template, new MajorMode(0), 48);

        // Put answer
        List<Note> ans = new ArrayList<>();
        ans.add(new Note(48));
        ans.add(new Note(50));

        Assert.assertEquals(ans, pattern.getNotes());
    }

    @Test
    public void testGeneratePattern_thirdFifth() {
        PhraseTemplate template = new PhraseTemplate();

        // Add pattern degrees
        template.addDegree(2);
        template.addDegree(4);

        // Select mode and key
        Pattern pattern = Pattern.generatePattern(template, new MajorMode(0), 48);

        // Put answer
        List<Note> ans = new ArrayList<>();
        ans.add(new Note(48));
        ans.add(new Note(51));

        Assert.assertEquals(ans, pattern.getNotes());
    }

    @Test
    public void testGeneratePattern_rootThirdFifth() {
        PhraseTemplate template = new PhraseTemplate();

        // Add pattern degrees
        template.addDegree(0);
        template.addDegree(2);
        template.addDegree(4);

        // Select mode and key
        Pattern pattern = Pattern.generatePattern(template, new MajorMode(0), 48);

        // Put answer
        List<Note> ans = new ArrayList<>();
        ans.add(new Note(48));
        ans.add(new Note(52));
        ans.add(new Note(55));

        Assert.assertEquals(ans, pattern.getNotes());
    }

    @Test
    public void testGeneratePattern_rootThirdFifth_differentRoot() {
        PhraseTemplate template = new PhraseTemplate();

        // Add pattern degrees
        template.addDegree(0);
        template.addDegree(2);
        template.addDegree(4);

        // Select mode and key
        Pattern pattern = Pattern.generatePattern(template, new MajorMode(0), 47);

        // Put answer
        List<Note> ans = new ArrayList<>();
        ans.add(new Note(47));
        ans.add(new Note(51));
        ans.add(new Note(54));

        Assert.assertEquals(ans, pattern.getNotes());
    }

    @Test
    public void testGeneratePattern_rootThirdFifthNinth() {
        PhraseTemplate template = new PhraseTemplate();

        // Add pattern degrees
        template.addDegree(0);
        template.addDegree(2);
        template.addDegree(4);
        template.addDegree(9);

        // Select mode and key
        Pattern pattern = Pattern.generatePattern(template, new MajorMode(0), 47);

        // Put answer
        List<Note> ans = new ArrayList<>();
        ans.add(new Note(47));
        ans.add(new Note(51));
        ans.add(new Note(54));
        ans.add(new Note(63));

        Assert.assertEquals(ans, pattern.getNotes());
    }

    @Test
    public void testGeneratePattern_rootThirdFifthNinthThird() {
        PhraseTemplate template = new PhraseTemplate();

        // Add pattern degrees
        template.addDegree(0);
        template.addDegree(2);
        template.addDegree(4);
        template.addDegree(9);
        template.addDegree(2);

        // Select mode and key
        Pattern pattern = Pattern.generatePattern(template, new MajorMode(0), 47);

        // Put answer
        List<Note> ans = new ArrayList<>();
        ans.add(new Note(47));
        ans.add(new Note(51));
        ans.add(new Note(54));
        ans.add(new Note(63));
        ans.add(new Note(51));

        Assert.assertEquals(ans, pattern.getNotes());
    }

    @Test
    public void testGeneratePattern_rootThirdFifthNinthThird_phrygian() {
        PhraseTemplate template = new PhraseTemplate();

        // Add pattern degrees
        template.addDegree(0);
        template.addDegree(2);
        template.addDegree(4);
        template.addDegree(9);
        template.addDegree(2);

        // Select mode and key
        Pattern pattern = Pattern.generatePattern(template, new MajorMode(2), 47);

        // Put answer
        List<Note> ans = new ArrayList<>();
        ans.add(new Note(47));
        ans.add(new Note(50));
        ans.add(new Note(54));
        ans.add(new Note(62));
        ans.add(new Note(50));

        Assert.assertEquals(ans, pattern.getNotes());
    }

    @Test
    public void testGeneratePattern_rootThirdFifthNinthThird_melMin() {
        PhraseTemplate template = new PhraseTemplate();

        // Add pattern degrees
        template.addDegree(0);
        template.addDegree(2);
        template.addDegree(4);
        template.addDegree(9);
        template.addDegree(2);

        // Select mode and key
        Pattern pattern = Pattern.generatePattern(template, new MelodicMinorMode(2), 47);

        // Put answer
        List<Note> ans = new ArrayList<>();
        ans.add(new Note(47));
        ans.add(new Note(51));
        ans.add(new Note(55));
        ans.add(new Note(63));
        ans.add(new Note(51));

        Assert.assertEquals(ans, pattern.getNotes());
    }

    @Test
    public void testGeneratePattern_rootThirdFifthNinthThird_melMin_addNote() {
        PhraseTemplate template = new PhraseTemplate();

        // Add pattern degrees
        template.addDegree(0);
        template.addDegree(2);
        template.addDegree(4);
        template.addDegree(9);

        // Select mode and key
        Pattern pattern = Pattern.generatePattern(template, new MelodicMinorMode(2), 47);
        pattern.addNote(2);

        // Put answer
        List<Note> ans = new ArrayList<>();
        ans.add(new Note(47));
        ans.add(new Note(51));
        ans.add(new Note(55));
        ans.add(new Note(63));
        ans.add(new Note(51));

        Assert.assertEquals(ans, pattern.getNotes());
    }

    @Test
    public void testGeneratePattern_rootThirdFifthNinthThird_melMin_addNote_defGeneratePattern() {
        // Select mode and key
        Pattern pattern = Pattern.generatePattern(0, new MelodicMinorMode(2), 47);
        pattern.addNote(2);
        pattern.addNote(4);
        pattern.addNote(9);
        pattern.addNote(2);

        // Put answer
        List<Note> ans = new ArrayList<>();
        ans.add(new Note(47));
        ans.add(new Note(51));
        ans.add(new Note(55));
        ans.add(new Note(63));
        ans.add(new Note(51));

        Assert.assertEquals(ans, pattern.getNotes());
    }

}