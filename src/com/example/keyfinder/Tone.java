package com.example.keyfinder;

public abstract class Tone {

    protected final int degree;

    public Tone(int degree) {
        this.degree = degree;
    }

    public int getDegree() {
        return degree;
    }

}
