package utils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liufengkai on 16/7/14.
 */
public class GeneratedId {
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(20000);

    /**
     * Generate a value suitable for use in setId
     * This value will not collide with ID values generated at build time by aapt for R.id.
     *
     * @return a generated ID value
     */
    public static int generateAtomId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            int newValue = result + 1;
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }
}
