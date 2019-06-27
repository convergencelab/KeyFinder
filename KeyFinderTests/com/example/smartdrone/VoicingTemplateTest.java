package com.example.smartdrone;

import org.junit.Test;

import static org.junit.Assert.*;

public class VoicingTemplateTest {

    @Test
    public void getTemplateTones() {
        HarmonyGenerator hg = new HarmonyGenerator();
        VoicingTemplate vt = hg.generateVoicingTemplate(HarmonyGenerator.BASS_NULL, new int[] {0, 2, 4});
    }

    @Test
    public void toStringTest() {
        HarmonyGenerator hg = new HarmonyGenerator();

        VoicingTemplate vt = hg.generateVoicingTemplate(HarmonyGenerator.BASS_NULL, new int[] {0, 2, 4});
        assertEquals("Chord 0\nChord 2\nChord 4\n", vt.toString());

        vt = hg.generateVoicingTemplate(HarmonyGenerator.BASS_FIFTH, new int[] {0, 3, 4, 9});
        System.out.println(vt);

        vt = hg.generateVoicingTemplate(HarmonyGenerator.BASS_ROOT_FIFTH, new int[] {2, 3, 4, 9});
        System.out.println(vt);
//        assertEquals("Chord 0\nChord 2\nChord 4\n", vt.toString());
    }

}