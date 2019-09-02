package com.example.keyfinder;

public abstract class Mode {

    // Implementation will setup intervals and name
    protected abstract void setup();

    protected final int ix;

    protected int[] intervals;

    protected String name;

    public Mode(int ix) {
        this.ix = ix;
        setup();
    }

    public int getIx() {
        return ix;
    }

    public int[] getIntervals() {
        return intervals;
    }

    public String getName() {
        return name;
    }

}
