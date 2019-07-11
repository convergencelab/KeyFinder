package com.example.keyfinder;

import org.junit.Test;

public class MelodicMinorKeyTest {

    @Test
    public void test1() {
        MelodicMinorKey key = new MelodicMinorKey(0);
        System.out.println(key);

        key = new MelodicMinorKey(3);
        System.out.println(key);
    }

}