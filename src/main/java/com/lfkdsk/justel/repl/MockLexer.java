package com.lfkdsk.justel.repl;

import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.token.SepToken;

import java.io.Reader;

/**
 * insert symbol = operator for REPL
 *
 * @author liufengkai
 */
public class MockLexer extends JustLexerImpl {

    public MockLexer() {
        insertSymbol("=", new SepToken(2000, "="));
    }

    public MockLexer(Reader reader) {
        super(reader);
        insertSymbol("=", new SepToken(2000, "="));
    }
}
