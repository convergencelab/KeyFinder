package com.example.smartdrone;

public class ScaleTemplate {
    /**
     * Name of scale.
     */
    private String _name;

    /**
     * Intervals in scale.
     */
    private int[] _intervals;

    /**
     * Constructor.
     * @param       name String; name of scale.
     * @param       intervals int[]; intervals in scale.
     */
    public ScaleTemplate(String name, int[] intervals) {
        _name = name;
        _intervals = intervals;
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

    public String toString() {
        String str = _name + ": ";
        for (int i = 0; i < _intervals.length; i++) {
            str += _intervals[i] + " ";
        }
        return str;
    }
}
