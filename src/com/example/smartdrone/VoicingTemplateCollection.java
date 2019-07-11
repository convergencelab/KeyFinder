package com.example.smartdrone;

import java.util.HashMap;
import java.util.Map;

public class VoicingTemplateCollection {
    /**
     * Collection of voicing templates.
     * Names are keys; indices are values.
     */
    private Map<String, VoicingTemplate> templateCatalogue;

    /**
     * Constructor.
     */
    public VoicingTemplateCollection() {
        templateCatalogue = new HashMap<>();
    }

//    /**
//     * Construct a new voicing template and add to voicing collection.
//     * @param       name String; voicing template name.
//     * @param       chordTones int[]; voice template indices.
//     * @return      boolean; true name not in catalogue.
//     */
//    public boolean addVoicingTemplate(String name, int[] chordTones) {
//        // todo error check instead
//        if (name == null) {
//            return false;
//        }
//        // Map already has voicing matching parameter name.
//        if (templateCatalogue.containsKey(name)) {
//            return false;
//        }
//        // Add voicing to catalogue.
//        VoicingTemplate voicingTemplate = new VoicingTemplate(name, chordTones);
//        templateCatalogue.put(name, voicingTemplate);
//        return true;
//    }

    /**
     * Remove voicing template from voicing catalogue.
     * @param       name String; name of voicing template.
     * @return      boolean; true if voicing template was removed.
     */
    public boolean removeVoicingTemplate(String name) {
        if (templateCatalogue.containsKey(name)) {
            templateCatalogue.remove(name);
            return true;
        }
        return false;
    }

    /**
     * Get voicing from collection using voicing name as key.
     * @param       targetVoicingName String; name of target voicing.
     * @return      Voicing; voicing matching target name.
     */
    public VoicingTemplate getVoicingTemplate(String targetVoicingName) {
        // Voicing not in collection.
        if (!templateCatalogue.containsKey(targetVoicingName)) {
            //todo error check instead
            System.out.println("Voicing Template not found");
            return null;
        }
        return templateCatalogue.get(targetVoicingName);
    }
}
