import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.token.Token;
import com.lfkdsk.justel.utils.json.JSONException;
import com.lfkdsk.justel.utils.json.JSONObject;
import com.lfkdsk.justel.utils.logger.Logger;
import com.lfkdsk.justel.utils.printer.Log;
import dashbase.QueryGrammar;
import dashbase.lexer.QueryLexer;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import javax.management.Query;

import java.util.Queue;

import static org.junit.Assert.*;

public class QueryGrammarTest {

    @Test
    public void testEmptyObjectValue() {
        String obj = "{}";
        Lexer lexer = new QueryLexer(obj);
        Queue<Token> tokens = lexer.tokens();
        AstNode node = new QueryGrammar().getObjectLabel().parse(tokens);
        Assertions.assertEquals(node.childCount(), 3);
        Assertions.assertEquals(node.child(0).toString(), "{");
        Assertions.assertEquals(node.child(2).toString(), "}");
    }

    @Test
    public void testEmptyArrayValue() {
        String obj = "[]";
        Lexer lexer = new QueryLexer(obj);
        Queue<Token> tokens = lexer.tokens();
        AstNode node = new QueryGrammar().getArrayLabel().parse(tokens);
        Assertions.assertEquals(node.childCount(), 3);
        Assertions.assertEquals(node.child(0).toString(), "[");
        Assertions.assertEquals(node.child(2).toString(), "]");
    }

    @Test
    public void testPrimary() {
        String[] args = {"\"lfkdsk\"", "true", "100000"};
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            Lexer lexer = new QueryLexer(arg);
            Queue<Token> tokens = lexer.tokens();
            AstNode node = new QueryGrammar().getPrimary().parse(tokens);

            if (i == 0) {
                Assertions.assertEquals(node.child(0).getClass().getSimpleName(), "StringLiteral");
            } else if (i == 1) {
                Assertions.assertEquals(node.child(0).getClass().getSimpleName(), "BoolLiteral");
            } else if (i == 2) {
                Assertions.assertEquals(node.child(0).getClass().getSimpleName(), "NumberLiteral");
            }
        }
    }

    @Test
    public void testLabel() {
        String[] labels = {"{}", "[]", "\"\"", "true"};
        for (String label : labels) {
            Lexer lexer = new QueryLexer(label);
            Queue<Token> tokens = lexer.tokens();
            AstNode node = new QueryGrammar().getLabel().parse(tokens);
            Assertions.assertEquals(node.getClass().getSimpleName(), "AstLabelExpr");
        }
    }

    @Test
    public void testInnerLabel() {
        String[] innerLabels = {"\"lfkdsk\":{}", "\"lfkdsk\": []", "\"lfkdsk\": \"1111\""};
        QueryGrammar grammar = new QueryGrammar();
        for (int i = 0; i < innerLabels.length; i++) {
            String innerLabel = innerLabels[i];
            Lexer lexer = new QueryLexer(innerLabel);
            Queue<Token> tokens = lexer.tokens();
            AstNode node = grammar.getInnerLabel().parse(tokens);
            Assertions.assertEquals(node.getClass().getSimpleName(), "AstInnerLabelExpr");
            Assertions.assertEquals(node.child(0).toString(), "lfkdsk");
            Assertions.assertEquals(node.child(2).getClass().getSimpleName(), "AstLabelExpr");
        }
    }

    @Test
    public void testQuery() throws JSONException {
        JSONObject object = new JSONObject();
        object.put("lfkdsk", "1");

        String query = object.toString();
        Lexer lexer = new QueryLexer(query);
        Queue<Token> tokens = lexer.tokens();
        AstNode node = new QueryGrammar().getProgram().parse(tokens);
        Assertions.assertEquals(node.getClass().getSimpleName(), "AstQueryProgram");
    }
}