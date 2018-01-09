package dashbase.ast;

import com.lfkdsk.justel.ast.base.AstList;
import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.token.Token;
import dashbase.token.Tokens;

import java.util.List;

public class AstArrayLabel extends QueryAstList {
    public AstArrayLabel(List<AstNode> children) {
        super(children, Tokens.AST_ARRAY_LABEL);
    }

}
