package com.example.keyfinder;

import org.junit.Test;

public class MajorKeyTest {

    @Test
    public void test1() {
        MajorKey key = new MajorKey(0);
        System.out.println(key);

        key = new MajorKey(3);
        System.out.println(key);
    }

}