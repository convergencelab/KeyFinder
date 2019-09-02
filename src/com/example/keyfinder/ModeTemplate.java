package com.example.keyfinder;

@Deprecated
public class ModeTemplate {
    /**
     * Name of scale.
     */
    private String _name;

    /**
     * Intervals in scale.
     */
    private int[] _intervals;

    /**
     * Index of mode in parent scale.
     * (Ionian = 0, Dorian = 1, Phrygian = 2, ... )
     */
    private int _ix;

    /**
     * Constructor.
     * @param       name String; name of scale.
     * @param       intervals int[]; intervals in scale.
     */
    public ModeTemplate(String name, int[] intervals, int ix) {
        _name = name;
        _intervals = intervals;
        _ix = ix;
    }

    /**
     * Get name of scale.
     * @return      String; name of scale.
     */
    public String getName() {
        return _name;
    }

    /**
     * Get intervals in scale.
     * @return      int[]; intervals in scale.
     */
    public int[] getIntervals() {
        return _intervals;
    }

    public int getIx() {
        return _ix;
    }

    public String toString() {
        String str = _name + ": ";
        for (int i = 0; i < _intervals.length; i++) {
            str += _intervals[i] + " ";
        }
        return str;
    }
}
