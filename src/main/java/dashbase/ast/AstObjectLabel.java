package dashbase.ast;

import com.lfkdsk.justel.ast.base.AstList;
import com.lfkdsk.justel.ast.base.AstNode;
import dashbase.token.Tokens;

import java.util.List;

public class AstObjectLabel extends QueryAstList {
    public AstObjectLabel(List<AstNode> children) {
        super(children, Tokens.AST_OBJECT_LABEL);
    }
}
