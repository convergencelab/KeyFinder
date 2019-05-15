package com.example.smartdrone;

public class source {
    public static void main(String[] args) {
        //TODO Is it possible to have the note collection as a static array, so it doesn't have to be passed around?
        //TODO Exception handling

        // Run simulations: note entered will enter all notes from scale.
        KeyFinder kf = new KeyFinder();
        kf.run();

    }
}
