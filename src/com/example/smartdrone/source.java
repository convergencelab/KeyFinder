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
        int[] x = { 1, 3, 5};
        VoicingTemplate vt = new VoicingTemplate("bob", x);
        System.out.println(vt);
    }
}
