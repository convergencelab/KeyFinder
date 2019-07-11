package com.example.smartdrone;

import org.junit.Assert;
import org.junit.Test;

public class VoicingTest {

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
    public void dPhrygian() {
        int[] shouldEqual = { 14, 21, 38, 41, 45, 48 };
        Voicing voicing = new Voicing(mockVoicingTemplate, getModeTemplate(0, 0), getKey(0, 0),
                12, 36);
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

}