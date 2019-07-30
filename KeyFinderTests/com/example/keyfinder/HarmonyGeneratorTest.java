package com.example.keyfinder;

import org.junit.Assert;
import org.junit.Test;

public class HarmonyGeneratorTest {

    @Test
    public void cMajDropII() {
        int[] ans = { 36, 43, 48, 55, 59, 64};
        HarmonyGenerator generator = new HarmonyGenerator();
        Voicing voicing = generator.generateVoicing(dropII, kf.getModeTemplate(0), kf.getMajorKey(0));
        Assert.assertEquals(ans[0], voicing.getVoice(0).getRawIx());
        Assert.assertEquals(ans[1], voicing.getVoice(1).getRawIx());
        Assert.assertEquals(ans[2], voicing.getVoice(2).getRawIx());
        Assert.assertEquals(ans[3], voicing.getVoice(3).getRawIx());
        Assert.assertEquals(ans[4], voicing.getVoice(4).getRawIx());
        Assert.assertEquals(ans[5], voicing.getVoice(5).getRawIx());
//        for (int i = 0; i < voicing.numVoices(); i++) {
//            Assert.assertEquals(ans[i], voicing.getVoice(i).getRawIx());
//        }

    }

    @Test
    public void cMajDropIIrefac() {
        int[] ans = { 36, 43, 48, 55, 59, 64};
        HarmonyGenerator generator = new HarmonyGenerator();
        Voicing voicing = generator.generateVoicing(dropII, kf.getModeTemplate(0), 0);
        Assert.assertEquals(ans[0], voicing.getVoice(0).getRawIx());
        Assert.assertEquals(ans[1], voicing.getVoice(1).getRawIx());
        Assert.assertEquals(ans[2], voicing.getVoice(2).getRawIx());
        Assert.assertEquals(ans[3], voicing.getVoice(3).getRawIx());
        Assert.assertEquals(ans[4], voicing.getVoice(4).getRawIx());
        Assert.assertEquals(ans[5], voicing.getVoice(5).getRawIx());
    }

    @Test
    public void dMajDropII() {
        int[] ans = { 38, 45, 50, 57, 61, 66};
        HarmonyGenerator generator = new HarmonyGenerator();
        Voicing voicing = generator.generateVoicing(dropII, kf.getModeTemplate(0), 2);
        Assert.assertEquals(ans[0], voicing.getVoice(0).getRawIx());
        Assert.assertEquals(ans[1], voicing.getVoice(1).getRawIx());
        Assert.assertEquals(ans[2], voicing.getVoice(2).getRawIx());
        Assert.assertEquals(ans[3], voicing.getVoice(3).getRawIx());
        Assert.assertEquals(ans[4], voicing.getVoice(4).getRawIx());
        Assert.assertEquals(ans[5], voicing.getVoice(5).getRawIx());
    }

    @Test
    public void ebDorianDropII() {
        int[] ans = { 39, 46, 51, 58, 61, 66};
        HarmonyGenerator generator = new HarmonyGenerator();
        Voicing voicing = generator.generateVoicing(dropII, kf.getModeTemplate(1), 3);
        Assert.assertEquals(ans[0], voicing.getVoice(0).getRawIx());
        Assert.assertEquals(ans[1], voicing.getVoice(1).getRawIx());
        Assert.assertEquals(ans[2], voicing.getVoice(2).getRawIx());
        Assert.assertEquals(ans[3], voicing.getVoice(3).getRawIx());
        Assert.assertEquals(ans[4], voicing.getVoice(4).getRawIx());
        Assert.assertEquals(ans[5], voicing.getVoice(5).getRawIx());
    }

    /* Melodic Minor Tests */

    @Test
    public void abLydAugExtended() {
        int[] ans = { 45, 57, 61, 65, 68, 71, 75, 78 };
        HarmonyGenerator generator = new HarmonyGenerator();
        Voicing voicing = generator.generateVoicing(fullExt, mtc.getMelodicMinorModeTemplates()[2], 9);
        Assert.assertArrayEquals(voicingToIntArray(voicing), ans);
    }

    private int[] voicingToIntArray(Voicing toConvert) {
        int[] toReturn = new int[toConvert.numVoices()];
        for (int i = 0; i < toConvert.numVoices(); i++) {
            toReturn[i] = toConvert.getVoice(i).getRawIx();
        }
        return toReturn;
    }

    private KeyFinder kf = new KeyFinder();
    private ModeTemplateCollection mtc = new ModeTemplateCollection();
    VoicingTemplate dropII = new VoicingTemplate(new int[]{0,4}, new int[]{0,4,6,9});
    VoicingTemplate fullExt = new VoicingTemplate(new int[]{0}, new int[]{0, 2, 4, 6, 8, 10, 12});


}