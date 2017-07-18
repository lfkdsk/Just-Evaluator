package template;

import ast.AstList;
import ast.AstNode;

import java.util.List;

/**
 * Created by liufengkai on 2017/7/18.
 */
public class Class extends AstList{

    public Class(List<AstNode> children) {
        super(children, 0);
    }
}
