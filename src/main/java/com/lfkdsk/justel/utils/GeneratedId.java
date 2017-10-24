package com.lfkdsk.justel.utils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Generate spec id : use atomic integer => id
 *
 * @author liufengkai
 *         Created by liufengkai on 16/7/14.
 */
public final class GeneratedId {

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(100);

    private GeneratedId() {}

    /**
     * Generate a value suitable for use
     *
     * @return a generated ID value
     */
    public static int generateAtomId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            final int newValue = result + 1;
            if (sNextGeneratedId.compareAndSet(result, newValue)) {

                return result;
            }
        }
    }
}
