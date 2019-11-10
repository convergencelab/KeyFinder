package com.example.keyfinder;

import com.example.keyfinder.eartraining.IntervalTemplate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class IntervalTemplateTest {

    @Test
    // findRange() is a private method called when setting tones and called from constructor
    public void testFindRange_defaultConstructor() {
        IntervalTemplate template = new IntervalTemplate();
//        assertArrayEquals(
//                new int[]{-1, -1},
//                template.getRange());
    }

    @Test
    public void testFindRange_singleTone() {
        List<Integer> list = new ArrayList<>();
//        list.add()
        IntervalTemplate template = new IntervalTemplate();
    }

}