package com.example.smartdrone;

public class source {
    public static void main(String[] args) {
//        // Testing note array construction.
//        KeyFinder kf = new KeyFinder();
//        for (int i = 0; i < MusicTheory.TOTAL_NOTES; i++) {
//            Key curKey = kf.getAllKeys().getMajorKeyAtIndex(i);
//            System.out.println("\n" + curKey.getName() + ": ");
//            for (Note n : curKey.getNotes()) {
//                System.out.print(n + " ");
//            }
//        }
//        int[] x = { 1, 5, 7, 10};
//        VoicingTemplate vt = new VoicingTemplate("bob", x);
//        for (int i = 0; i < vt.getchordTones().length; i++) {
//            int[] curInv = vt.getInversion(i);
//            for (int j : curInv) {
//                System.out.print(j + " ");
//            }
//            System.out.println();
//        }

        KeyFinder kf = new KeyFinder();

        VoicingTemplateCollection vtc = new VoicingTemplateCollection();
        int[] x = { 1, 3, 4, 7};
        vtc.addVoicingTemplate("bob", x);
        VoicingTemplate vt = vtc.getVoicingTemplate("bob");
        Voicing v = vt.generateVoicing(kf.getAllKeys().getMajorKeyAtIndex(2), 2, 1);
        System.out.println(v);
    }
}
