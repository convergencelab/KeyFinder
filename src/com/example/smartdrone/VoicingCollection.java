package com.example.smartdrone;

import java.util.HashMap;
import java.util.Map;

public class VoicingCollection {
    /**
     * Collection of voicing objects. Voicing names are the keys and the voice indices are the values.
     */
    private Map<String, Voicing> voicingCatalogue;

    /**
     * Constructor.
     */
    public VoicingCollection() {
        voicingCatalogue = new HashMap<>();
    }

    /**
     * Construct a new voicing and add to voicing collection.
     * @param       name String; voicing name.
     * @param       voiceIxs int[]; voice indices.
     * @return      boolean; true if voicing with same name not already in collection.
     */
    public boolean addVoicing(String name, int[] voiceIxs) {
        // todo error check instead
        // Map already has voicing matching parameter name.
        if (voicingCatalogue.containsKey(name)) {
            return false;
        }
        // Add voicing to catalogue.
        Voicing voicing = new Voicing(name, voiceIxs);
        voicingCatalogue.put(name, voicing);
        return true;
    }

    /**
     * Get voicing from collection using voicing name as key.
     * @param       targetVoicingName String; name of target voicing.
     * @return      Voicing; voicing matching target name.
     */
    public Voicing getVoicing(String targetVoicingName) {
        // Voicing not in collection.
        if (!voicingCatalogue.containsKey(targetVoicingName)) {
            //todo error check instead
            System.out.println("Voicing not found");
            return null;
        }
        return voicingCatalogue.get(targetVoicingName);
    }
}
