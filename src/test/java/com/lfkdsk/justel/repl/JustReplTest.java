package com.lfkdsk.justel.repl;

import org.fusesource.jansi.Ansi;
import org.junit.jupiter.api.Test;

import static org.fusesource.jansi.Ansi.ansi;

class JustReplTest {

    @Test
    void testPrintLogo() {
        String logoStr = JustRepl.logoStr.replace("█", ansi().fg(Ansi.Color.GREEN).a("█").reset().toString());
        System.out.println(logoStr);
    }


}