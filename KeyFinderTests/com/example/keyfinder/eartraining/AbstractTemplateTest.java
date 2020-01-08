package com.example.keyfinder.eartraining;

import org.junit.Assert;
import org.junit.Test;

public class AbstractTemplateTest {

    @Test
    public void testRange() {
        AbstractTemplate template = new AbstractTemplate();

        Assert.assertEquals(-1, template.getLowestDegree());
        Assert.assertEquals(-1, template.getHighestDegree());
    }

    @Test
    public void testRange_triad_inorder() {
        AbstractTemplate template = new AbstractTemplate();
        template.addDegree(0);
        template.addDegree(2);
        template.addDegree(4);

        Assert.assertEquals(0, template.getLowestDegree());
        Assert.assertEquals(4, template.getHighestDegree());
    }

    @Test
    public void testRange_triad_notInorder() {
        AbstractTemplate template = new AbstractTemplate();
        template.addDegree(2);
        template.addDegree(0);
        template.addDegree(4);

        Assert.assertEquals(0, template.getLowestDegree());
        Assert.assertEquals(4, template.getHighestDegree());
    }


    @Test
    public void testRange_triad_whyThefuckdoesntitwork() {
        AbstractTemplate template = new AbstractTemplate();
        template.addDegree(5);
        template.addDegree(0);
        template.addDegree(2);
        template.addDegree(3);
        template.addDegree(10);

        Assert.assertEquals(0, template.getLowestDegree());
        Assert.assertEquals(10, template.getHighestDegree());
    }


}