package dashbase.ast;

import com.lfkdsk.justel.ast.base.AstNode;
import dashbase.token.Tokens;

import java.util.List;

public class AstLabelList extends QueryAstList {
    public AstLabelList(List<AstNode> children) {
        super(children, Tokens.OBJECT_LIST);
    }
}
