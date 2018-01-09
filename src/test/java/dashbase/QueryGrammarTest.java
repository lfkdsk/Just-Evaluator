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
    }

    @Test
    public void testEmptyArrayValue() {
        String obj = "[]";
        Lexer lexer = new QueryLexer(obj);
        Queue<Token> tokens = lexer.tokens();

        AstNode node = new QueryGrammar().getObjectLabel().parse(tokens);
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
        String innerLabel = "\"lfkdsk\" : \"lfkdsk\"";
        Lexer lexer = new JustLexerImpl(innerLabel);
        AstNode node = new QueryGrammar().getInnerLabel().parse(lexer.tokens());
    }



    @Test
    public void testQuery() throws JSONException {
        JSONObject object = new JSONObject();
        object.put("lfkdsk", "1");

        String query = object.toString();
        Lexer lexer = new JustLexerImpl(query);
        AstNode node = new QueryGrammar().getProgram().parse(lexer.tokens());
    }
}