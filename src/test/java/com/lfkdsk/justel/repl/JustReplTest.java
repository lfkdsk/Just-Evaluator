package com.lfkdsk.justel.repl;

import com.lfkdsk.justel.utils.logger.Logger;
import org.fusesource.jansi.Ansi;
import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.repl.JustRepl.reformatAstPrint;
import static org.fusesource.jansi.Ansi.ansi;

class JustReplTest {

    @Test
    void testReformatString() {
        Logger.init();
        Logger.v(reformatAstPrint("(+ lfkdsk 1)"));
        Logger.v(reformatAstPrint("(- (+ lfkdsk 1) lfkdsk)"));
        Logger.v(reformatAstPrint("(== (- (+ lfkdsk 1) lfkdsk) true)"));
    }

    @Test
    void testPrintLogo() {
        String logoStr = JustRepl.logoStr.replace("█", ansi().fg(Ansi.Color.GREEN).a("█").reset().toString());
        System.out.println(logoStr);
    }


}