package com.lfkdsk.justel.generate.bytegen;

import com.lfkdsk.justel.ast.tree.AstProgram;
import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.parser.JustParser;
import com.lfkdsk.justel.parser.JustParserImpl;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

@Deprecated
class ELByteCodeGenVisitorTest {

    @Test
    void testELByteCodeGen() {
        genByteCode("f+1000+100.0*99-(600-3*15)%(((68-9)-3)*2-100)+10000%7*71+f");
    }

    @Test
    void testComplexByteCodeGen() {
        genByteCode("lfkdsk.lfkdsk(1111,2222,\"LFKDSK\") == true");
    }

    @Test
    void testBoolean() {
        genByteCode("(true && true && true) ? 100 : -100");
    }

    @Test
    void testNegative() {
        genByteCode("-100");
    }

    @Test
    void testNotValue() {
        genByteCode("!true && !false");
    }

    @Test
    void testFunArguments() {
        genByteCode("function(\"lfkdsk\")");
    }

    public static void genByteCode(String expr) {
        Lexer lexer = new JustLexerImpl(new StringReader(expr));
        JustParser parser = new JustParserImpl();
        Logger.init("test parser");
        lexer.hasMore();
        AstProgram node = (AstProgram) parser.parser(lexer);

        ELByteCodeGenVisitor visitor = new ELByteCodeGenVisitor();
        visitor.visit(node);

        for (ELCommand elCommand : visitor.getCommands()) {
            Logger.v(elCommand.toString());
        }
    }
}