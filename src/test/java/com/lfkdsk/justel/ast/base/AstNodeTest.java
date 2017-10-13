package com.lfkdsk.justel.ast.base;

import com.lfkdsk.justel.ast.tree.AstProgram;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.parser.JustParserImpl;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.HashMap;

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

    @Test
    void testHashTree() {
        Logger.init();
        String str = "lfkdsk == 1 || (lfkdsk == 1)";
        HashMap<Integer, Object> cache = new HashMap<>();

        JustContext context = new JustMapContext() {{
            put("lfkdsk", 1);
        }};

        AstProgram node = (AstProgram) toNode(str);
        AstNode node1 = node.getChildren().get(0);
        AstNode a1 = node1.child(0);
        AstNode a2 = node1.child(2);

        Object re1 = a1.eval(context);
        cache.put(a1.hashCode(), re1);

        Object re2 = cache.get(a2.hashCode());
        Logger.v(re2.toString());
    }
}