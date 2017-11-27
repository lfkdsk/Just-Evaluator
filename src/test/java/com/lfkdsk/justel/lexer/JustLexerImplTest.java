package com.lfkdsk.justel.lexer;

import com.lfkdsk.justel.context.JustArrayContext;
import com.lfkdsk.justel.parser.JustParserImpl;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liufengkai on 2017/7/24.
 */
class JustLexerImplTest {

    @Test
    void testLexer() {
        for (int j = 0; j < 20; j++) {
            String lfkdsk = String.valueOf(" \"" + RandomUtils.RandomString(10) + "\""
                    + " " + RandomUtils.RandomFloat(2, 20) + " " +
                    RandomUtils.RandomInt(10, 200) + " " + "|| && *");

            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                JustLexerImpl lexer = new JustLexerImpl(new StringReader(lfkdsk));
                lexer.hasMore();
            }
            System.out.println(System.currentTimeMillis() - startTime);
        }
    }

    @Test
    void testNewSymbol() {
        JustLexerImpl lexer = new JustLexerImpl(new StringReader("== !== #= != "));
        lexer.hasMore();
    }

    @Test
    void testPoint() {
        JustLexerImpl lexer = new JustLexerImpl(new StringReader("a.c"));
        lexer.hasMore();
        System.out.println("complete");
    }

    @Test
    void testString() {
        JustLexerImpl lexer = new JustLexerImpl();
        Logger.init();
//        Logger.v(lexer.scanner("\"\" + \"\"").toString());
//        Logger.v(lexer.scanner("\"fffff\" + \"\"").toString());
//        Logger.v(lexer.scanner("\"fffff\" + \"ffffff\"").toString());
//        Logger.v(lexer.scanner("\"fffff\" + \\ffffff\\").toString());
        Logger.v(lexer.scanner("\" \"lfkdsk\" + \"lfkdsk\" \" + \\ffffff\\ \"").toString());
    }

    @Test
    void testPattern() {
        Pattern pattern = Pattern.compile("(\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")");
        Matcher matcher = pattern.matcher("\"\\\n\"");
        Logger.init();
        Logger.v(matcher.toString());
        Logger.v(String.valueOf(matcher.matches()));
        if (matcher.matches()) {
            Logger.v(matcher.group());
        }
    }

    @Test
    void testAString() {
        Lexer lexer = new JustLexerImpl();
        Logger.init();
        Logger.v(lexer.scanner("\"lfkdsk\" + lfkdsk + 10000 + 100.0").toString());
        Logger.v(new JustParserImpl().parser(lexer.tokens()).eval(new JustArrayContext() {{
            put("lfkdsk", 1);
        }}).toString());
    }
}