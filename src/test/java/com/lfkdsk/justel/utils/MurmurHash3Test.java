package com.lfkdsk.justel.utils;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.ast.base.AstNodeTest;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Test;

/**
 * Created by liufengkai on 2017/9/28.
 */
class MurmurHash3Test {
//
//    Test result
//            ╔════════════════════════════════════════════════════════════════════════════════════════
//            ║Thread:main
//            ╟────────────────────────────────────────────────────────────────────────────────────────
//            ║ NativeMethodAccessorImpl.invoke0(NativeMethodAccessorImpl.java:-2)
//            ║    MurmurHash3Test.testHash(MurmurHash3Test.java:21)
//            ╟────────────────────────────────────────────────────────────────────────────────────────
//            ║ -1651462948
//            ╚════════════════════════════════════════════════════════════════════════════════════════
//            ╔════════════════════════════════════════════════════════════════════════════════════════
//            ║Thread:main
//            ╟────────────────────────────────────────────────────────────────────────────────────────
//            ║ NativeMethodAccessorImpl.invoke0(NativeMethodAccessorImpl.java:-2)
//            ║    MurmurHash3Test.testHash(MurmurHash3Test.java:23)
//            ╟────────────────────────────────────────────────────────────────────────────────────────
//            ║ 18106
//            ╚════════════════════════════════════════════════════════════════════════════════════════
//            ╔════════════════════════════════════════════════════════════════════════════════════════
//            ║Thread:main
//            ╟────────────────────────────────────────────────────────────────────────────────────────
//            ║ NativeMethodAccessorImpl.invoke0(NativeMethodAccessorImpl.java:-2)
//            ║    MurmurHash3Test.testHash(MurmurHash3Test.java:30)
//            ╟────────────────────────────────────────────────────────────────────────────────────────
//            ║ -1287618673
//            ╚════════════════════════════════════════════════════════════════════════════════════════
//            ╔════════════════════════════════════════════════════════════════════════════════════════
//            ║Thread:main
//            ╟────────────────────────────────────────────────────────────────────────────────────────
//            ║ NativeMethodAccessorImpl.invoke0(NativeMethodAccessorImpl.java:-2)
//            ║    MurmurHash3Test.testHash(MurmurHash3Test.java:32)
//            ╟────────────────────────────────────────────────────────────────────────────────────────
//            ║ 5180
//            ╚════════════════════════════════════════════════════════════════════════════════════════

    @Test
    void testHash() {
        AstNode node = AstNodeTest.toNode("lfkdsk == 1");
        Logger.init();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_0000_0000; i++) {
            int v = node.toString().hashCode();
            if (i == 0)
                Logger.v(String.valueOf(v));
        }
        Logger.v(String.valueOf(System.currentTimeMillis() - start));

        start = System.currentTimeMillis();

        byte[] bytes = node.toString().getBytes();

        for (int i = 0; i < 1_0000_0000; i++) {
            int v = MurmurHash3.hash(bytes);
            if (i == 0)
                Logger.v(String.valueOf(v));
        }

        Logger.v(String.valueOf(System.currentTimeMillis() - start));
    }
}