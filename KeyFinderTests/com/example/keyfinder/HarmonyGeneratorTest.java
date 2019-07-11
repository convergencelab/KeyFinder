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

    private KeyFinder kf = new KeyFinder();
    VoicingTemplate dropII = new VoicingTemplate(new int[]{0,4}, new int[]{0,4,6,9});


}