package com.lfkdsk.justel.utils.collection;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

class ArrayMapTest {

    @Test
    void testSkipTest() {
        SkipList<String, Object> skipList = new SkipList<>();
        skipList.put("a", 3600);
        skipList.put("b", 10);
        skipList.put("c", 100);

        int sum = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_0000_0000; i++) {
            int a = (int) skipList.get("a");
            int b = (int) skipList.get("b");
            int c = (int) skipList.get("c");
            sum += a + b + c;
        }

        System.out.println(" " + (System.currentTimeMillis() - start) + " ms");
    }

    @Test
    void testHashTest() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("a", 3600);
        hashMap.put("b", 10);
        hashMap.put("c", 100);

        int sum = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_0000_0000; i++) {
            int a = (int) hashMap.get("a");
            int b = (int) hashMap.get("b");
            int c = (int) hashMap.get("c");

            sum += a + b + c;
        }

        System.out.println(" " + (System.currentTimeMillis() - start) + " ms");
    }

    @Test
    void testArrayHashTest() {
        ArrayMap<String, Object> arrayMap = new ArrayMap<>();
        arrayMap.put("a", 3600);
        arrayMap.put("b", 10);
        arrayMap.put("c", 100);

        int sum = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_0000_0000; i++) {
            int a = (int) arrayMap.get("a");
            int b = (int) arrayMap.get("b");
            int c = (int) arrayMap.get("c");

            sum += a + b + c;
        }

        System.out.println(" " + (System.currentTimeMillis() - start) + " ms");
    }
}