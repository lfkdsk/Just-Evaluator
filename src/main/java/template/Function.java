package template;

import ast.AstList;
import ast.AstNode;

import java.util.List;

/**
 * Created by liufengkai on 2017/7/18.
 */
public class Function extends AstList{
    public Function(List<AstNode> children) {
        super(children, 0);
    }
}
