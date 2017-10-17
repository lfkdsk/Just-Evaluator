package com.lfkdsk.justel.utils;

import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.utils.FormatUtils.reformatAstPrint;

class FormatUtilsTest {

    @Test
    void testReformatString() {
        Logger.init();
        Logger.v(reformatAstPrint("(+ lfkdsk 1)"));
        Logger.v(reformatAstPrint("(- (+ lfkdsk 1) lfkdsk)"));
        Logger.v(reformatAstPrint("(== (- (+ lfkdsk 1) lfkdsk) true)"));
    }

    @Test
    void beautifulPrint() {
        System.out.println(FormatUtils.beautifulPrint("lfkdsk", "11111111111111111", "fffff"));
    }

    @Test
    void beautifulReformat() {
        System.out.println(FormatUtils.beautifulPrint("lfkdsk", "11111111111111111", reformatAstPrint("(== (- (+ lfkdsk 1) lfkdsk) true)")));
    }
}