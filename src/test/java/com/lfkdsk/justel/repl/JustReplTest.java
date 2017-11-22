package com.lfkdsk.justel.repl;

import com.lfkdsk.justel.JustEL;
import com.lfkdsk.justel.context.JustArrayContext;
import org.fusesource.jansi.Ansi;
import org.junit.jupiter.api.Test;

import static org.fusesource.jansi.Ansi.ansi;

class JustReplTest {

    @Test
    void testPrintLogo() {
        String logoStr = JustRepl.logoStr.replace("█", ansi().fg(Ansi.Color.GREEN).a("█").reset().toString());
        System.out.println(logoStr);
    }

    @Test
    void testAssign() {
        JustEL justEL = JustEL.builder()
                              .lexer(new MockLexer())
                              .parser(new MockParser())
                              .generator(new MockGenerator())
                              .create();

        justEL.compile("lfkdsk = \"lfkdsk\" + \"lfk\"", new JustArrayContext() {{
        }});
    }

    @Test
    void testStringAdder() {
        JustEL justEL = JustEL.builder().create();
        justEL.compile("\"fffff\" + \"fffff\"", new JustArrayContext());
    }
}