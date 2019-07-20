package com.example.keyfinder;

/**
 * Distinction between voicing templates and voicings:
 * Voicing templates contain indices that represent the scale degrees of a chord.
 * Voicing's, on the other hand, contain the actual midi keys mapped to notes (semi-tones).
 */
public class VoicingTemplate {

    /**
     * Name of voicing template.
     */
    private String _name;

    /**
     * Combines bass tones and chord tones into one array.
     */
    private Tone[] _templateTones;

    /**
     * Stores array of bass tones.
     */
    private Tone[] _bassTones;

    /**
     * Stores array of chord tones.
     */
    private Tone[] _chordTones;


    public VoicingTemplate(int[] bassToneIxs, int[] chordToneIxs) {
        this(null, bassToneIxs, chordToneIxs);
    }

    //Todo constructor could use some  refactoring; (make use of private methods)
    public VoicingTemplate(String name, int[] bassToneIxs, int[] chordToneIxs) {
        Tone[] bassTones = new Tone[bassToneIxs.length];
        Tone[] chordTones = new Tone[chordToneIxs.length];
        // Construct Bass Tones
        for (int i = 0; i < bassToneIxs.length; i++) {
            bassTones[i] = new Tone(bassToneIxs[i], Tone.TONE_BASS);
        }
        // Construct Chord Tones
        for (int i = 0; i < chordToneIxs.length; i++) {
            chordTones[i] = new Tone(chordToneIxs[i], Tone.TONE_CHORD);
        }

        _name = name;
        _bassTones = bassTones;
        _chordTones = chordTones;

        _templateTones = new Tone[bassTones.length + chordTones.length];
        int i = 0;
        // Add all bass tones.
        for (Tone curTone : bassTones) {
            _templateTones[i] = curTone;
            i++;
        }
        // Add all chord tones.
        for (Tone curTone : chordTones) {
            _templateTones[i] = curTone;
            i++;
        }
    }

    public VoicingTemplate(Tone[] bassTones, Tone[] chordTones) {
        this(null, bassTones, chordTones);
    }

    public VoicingTemplate(String name, Tone[] bassTones, Tone[] chordTones) {
        _name = name;
        _bassTones = bassTones;
        _chordTones = chordTones;

        _templateTones = new Tone[bassTones.length + chordTones.length];
        int i = 0;
        // Add all bass tones.
        for (Tone curTone : bassTones) {
            _templateTones[i] = curTone;
            i++;
        }
        // Add all chord tones.
        for (Tone curTone : chordTones) {
            _templateTones[i] = curTone;
            i++;
        }
    }

    /**
     * Get name of voicing template.
     * @return      String; name of voicing template.
     */
    public String getName() {
        return _name;
    }

    /**
     * Get all template tones.
     * @return      Tone[]; template tones.
     */
    public Tone[] getTemplateTones() {
        return _templateTones;
    }

    /**
     * Get all bass tones.
     * @return      Tone[]; bass tones.
     */
    public Tone[] getBassTones() {
        return _bassTones;
    }

    //todo will have to be careful about this method. the name chordTones is deceptive, it doesn't actually include bass tones
    /**
     * Get all chord tones.
     * @return      Tone[]; chord tones.
     */
    public Tone[] getChordTones() {
        return _chordTones;
    }

    /**
     * Return size of voicing template.
     * @return      int; size.
     */
    public int numVoices() {
        return _templateTones.length;
    }
}
