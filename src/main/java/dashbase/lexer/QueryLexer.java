package dashbase.lexer;

import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.token.SepToken;

public class QueryLexer extends JustLexerImpl {
    public QueryLexer(String expr) {
        super(expr);

        SepToken left = new SepToken(-1, "{");
        SepToken right = new SepToken(-1, "}");

        insertSymbol("{", left);
        insertSymbol("}", right);
    }
}
