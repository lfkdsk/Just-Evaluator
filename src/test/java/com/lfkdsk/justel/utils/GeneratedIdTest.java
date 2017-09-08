/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.utils;

import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Hashtable;

/**
 * Created by liufengkai on 2017/8/4.
 */
class GeneratedIdTest {

    @Test
    void testGenerateId() throws InterruptedException {
        Logger.init("gen-code");
        // need curr - table

        for (int j = 0; j < 20; j++) {
            // hash-table current-control is needed
            Hashtable<Integer, Integer> set = new Hashtable<>();

            for (int i = 0; i < 100; i++) {
                new Thread(() -> {
                    Integer integer = GeneratedId.generateAtomId();

                    if (set.contains(integer)) {
                        Logger.e(set.put(integer, set.get(integer) + 1).toString());
                        throw new RuntimeException("fuck");
                    } else {
                        set.put(integer, 1);
                    }

                    Logger.v(String.valueOf(integer));
                }).start();
            }


            // just wait thread completed
            Thread.sleep(1000);
            Assertions.assertEquals(set.size(), 100);
        }


    }
}