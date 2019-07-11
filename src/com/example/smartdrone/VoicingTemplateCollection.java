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
    
    /**
     * Add voicing to template collection.
     * @param       toAdd VoicingTemplate; voicing template to add.
     * @return      boolean; true if added successfully.
     */
    public boolean addVoicingTemplate(VoicingTemplate toAdd) {
        // todo error check instead
        if (toAdd.getName() == null) {
            return false;
        }
        // Map already has voicing matching parameter name.
        if (templateCatalogue.containsKey(toAdd.getName())) {
            return false;
        }
        // Add voicing to catalogue.
        templateCatalogue.put(toAdd.getName(), toAdd);
        return true;
    }

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
