package com.example.keyfinder.eartraining;

import com.example.keyfinder.MajorMode;
import com.example.keyfinder.Mode;
import com.example.keyfinder.Note;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PatternGenerationTest {

    @Test
    public void testShit() {
        Assert.assertTrue(true);
    }

    @Test
    public void testPatternGeneration() {

    }

    @Test
    public void generateRandomPattern() {
        // Make pattern 1 3 4 5
        PhraseTemplate template = new PhraseTemplate();
        template.addDegree(3);
        template.addDegree(0);
        template.addDegree(6);
        template.addDegree(2);
        template.addDegree(4);
//        template.addDegree(10);


        int lowerB = 20;
        int upperB = 49;

        int space = upperB - lowerB;

        List<Mode> modeList = new ArrayList<>();
        modeList.add(new MajorMode(0));

        int result = determineRangeRequired(template, modeList);
        if (result > space) {
            System.out.println("Insufficient space");
            return;
        }

        boolean fuckedUp = false;

        Random rng = new Random();
        for (int i = 0; i < 1000; i++) {
            final int ran = rng.nextInt(space - result + 1);
            final Pattern curPattern = Pattern.generatePattern(template, modeList.get(0), lowerB + ran);
            System.out.println(curPattern);

            for (Note note : curPattern.getNotes()) {
//                System.out.print(curPattern);
                if (note.getIx() < lowerB || note.getIx() > upperB) {
                    fuckedUp = true;
//                    System.out.println(curPattern);
                }
//                else if (note.getIx() == lowerB || note.getIx() == upperB) {
//                    System.out.print("  <- Here");
//                }
//                System.out.println();
            }
        }
        Assert.assertFalse(fuckedUp);
    }

    private int determineRangeRequired(PhraseTemplate template, List<Mode> modes) {
        int range = -1;
        for (Mode curMode : modes) {
            final int result = Pattern.calculateMinSpaceRequired(template, curMode);
            if (result > range) {
                range = result;
            }
        }
        return range;
    }

}
