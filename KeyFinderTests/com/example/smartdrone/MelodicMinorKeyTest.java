package com.example.smartdrone;

import org.junit.Test;

import static org.junit.Assert.*;

public class MelodicMinorKeyTest {

    @Test
    public void test1() {
        MelodicMinorKey key = new MelodicMinorKey(0);
        System.out.println(key);

        key = new MelodicMinorKey(3);
        System.out.println(key);
    }

}