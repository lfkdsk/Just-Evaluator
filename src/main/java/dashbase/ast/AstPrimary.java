package dashbase.ast;

import com.lfkdsk.justel.ast.base.AstList;
import com.lfkdsk.justel.ast.base.AstNode;
import dashbase.token.Tokens;

import java.util.List;

public class AstPrimary extends QueryAstList {
    public AstPrimary(List<AstNode> children) {
        super(children, Tokens.AST_PRIMARY);
    }
}
