package com.example.smartdrone;

import org.junit.Assert;
import org.junit.Test;

public class VoicingTest {
    VoicingTemplate mockVoicingTemplate = new VoicingTemplate(new int[]{0, 4}, new int[]{0, 2, 4, 6});
    ModeTemplateCollection mockModeTemplateCollection = new ModeTemplateCollection();
    KeyCollection mockKeyCollection = new KeyCollection();
    int mockLowerBass = 36;
    int mockLowerChord = 48;

    @Test
    public void voicingConstructorCmaj0() {
        Voicing voicing = new Voicing(mockVoicingTemplate, getModeTemplate(0, 0), getKey(0, 0),
                mockLowerBass, mockLowerChord);
        Assert.assertEquals(1, 1);
    }

    @Test
    public void cMaj_bass24_chord36() {
        int[] shouldEqual = { 24, 31, 36, 40, 43, 47 };
        Voicing voicing = new Voicing(mockVoicingTemplate, getModeTemplate(0, 0), getKey(0, 0),
                24, 36);
        for (int i = 0; i < voicing.numVoices(); i++) {
            Assert.assertEquals(shouldEqual[i], voicing.getVoice(i).getRawIx());
        }
    }

    @Test
    public void dPhrygian1() {
        int[] shouldEqual = { 14, 21, 38, 41, 45, 48 };
        Voicing voicing = new Voicing(mockVoicingTemplate, getModeTemplate(0, 2), getKey(0, 10),
                12, 36);
        for (int i = 0; i < voicing.numVoices(); i++) {
            Assert.assertEquals(shouldEqual[i], voicing.getVoice(i).getRawIx());
        }
    }

    @Test
    public void dPhrygianIsLowest() {
        int[] shouldEqual = { 14, 21, 38, 41, 45, 48 };
        Voicing voicing = new Voicing(mockVoicingTemplate, getModeTemplate(0, 2), getKey(0, 10),
                14, 38);
        for (int i = 0; i < voicing.numVoices(); i++) {
            Assert.assertEquals(shouldEqual[i], voicing.getVoice(i).getRawIx());
        }
    }

    @Test
    public void dPhrygianIsHighest() {
        int[] shouldEqual = { 26, 33, 50, 53, 57, 60 };
        Voicing voicing = new Voicing(mockVoicingTemplate, getModeTemplate(0, 2), getKey(0, 10),
                15, 39);
        for (int i = 0; i < voicing.numVoices(); i++) {
            Assert.assertEquals(shouldEqual[i], voicing.getVoice(i).getRawIx());
        }
    }

    @Test
    public void BbLydianFlat7IsLowest() {
        int[] shouldEqual = { 10, 17, 34, 38, 41, 44 };
        Voicing voicing = new Voicing(mockVoicingTemplate, getModeTemplate(1, 3), getKey(1, 5),
                10, 34);
        for (int i = 0; i < voicing.numVoices(); i++) {
            Assert.assertEquals(shouldEqual[i], voicing.getVoice(i).getRawIx());
        }
    }

    @Test
    public void BbLydianFlat7IsHighest() {
        int[] shouldEqual = { 22, 29, 46, 50, 53, 56 };
        Voicing voicing = new Voicing(mockVoicingTemplate, getModeTemplate(1, 3), getKey(1, 5),
                11, 35);
        for (int i = 0; i < voicing.numVoices(); i++) {
            Assert.assertEquals(shouldEqual[i], voicing.getVoice(i).getRawIx());
        }
    }

    @Test
    public void BbLydianFlat7Split() {
        int[] shouldEqual = { 22, 29, 34, 38, 41, 44 };
        Voicing voicing = new Voicing(mockVoicingTemplate, getModeTemplate(1, 3), getKey(1, 5),
                11, 34);
        for (int i = 0; i < voicing.numVoices(); i++) {
            Assert.assertEquals(shouldEqual[i], voicing.getVoice(i).getRawIx());
        }
    }

    private ModeTemplate getModeTemplate(int parentCode, int modeIx) {
        switch (parentCode) {
            case KeyFinder.CODE_MAJOR:
                return mockModeTemplateCollection.getMajorModeTemplates()[modeIx];
            case KeyFinder.CODE_MELODIC_MINOR:
                return mockModeTemplateCollection.getMelodicMinorModeTemplates()[modeIx];
        }
        return null;
    }

    private AbstractKey getKey(int parentCode, int keyIx) {
        switch (parentCode) {
            case KeyFinder.CODE_MAJOR:
                return mockKeyCollection.getMajorKeyAtIndex(keyIx);
            case KeyFinder.CODE_MELODIC_MINOR:
                return mockKeyCollection.getMelodicMinorKeyAtIndex(keyIx);
        }
        return null;
    }

        /*
        SOME TESTS I RAN TO TEST PRIVATE METHOD getLowestNote().
        MAKE METHOD PUBLIC AND EMPTY TEST CONSTRUCTOR.
        MADE THEM PRIVATE AGAIN AFTER TESTS PASSED.

    @Test
    public void getLowestNoteC24() {
        Voicing voicing = new Voicing();
        int ans = voicing.getLowestNote(new MelodicMinorKey(0), 24);
        assertEquals(24, ans);
    }

    @Test
    public void getLowestNoteB24() {
        Voicing voicing = new Voicing();
        int ans = voicing.getLowestNote(new MelodicMinorKey(11), 24);
        assertEquals(35, ans);
    }

    @Test
    public void getLowestNoteB23() {
        Voicing voicing = new Voicing();
        int ans = voicing.getLowestNote(new MelodicMinorKey(11), 23);
        assertEquals(23, ans);
    }

    @Test
    public void getLowestNoteC0() {
        Voicing voicing = new Voicing();
        int ans = voicing.getLowestNote(new MelodicMinorKey(0), 0);
        assertEquals(0, ans);
    }
    */

}