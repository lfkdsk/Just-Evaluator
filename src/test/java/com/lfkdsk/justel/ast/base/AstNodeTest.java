package com.lfkdsk.justel.ast.base;

import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.parser.JustParserImpl;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

/**
 * Created by liufengkai on 2017/9/28.
 */
public class AstNodeTest {

    @Test
    void testHash() {
        String a = " lfkdsk  == 1";
        String b = "lfkdsk==1";

        AstNode aNode = toNode(a);
        AstNode bNode = toNode(b);

        Logger.init();
        Logger.v(aNode.hashCode() + "");
        Logger.v(bNode.hashCode() + "");
        Assertions.assertTrue(aNode.hashCode() == bNode.hashCode());
        Assertions.assertTrue(aNode.equals(bNode));

        String c = "(lfkdsk ==1)";
        AstNode cNode = toNode(c);
        Logger.v(cNode.hashCode() + "");
    }

    public static AstNode toNode(String arg) {
        JustLexerImpl lexer = new JustLexerImpl(new StringReader(arg));
        JustParserImpl parser = new JustParserImpl();

        return parser.parser(lexer);
    }
}