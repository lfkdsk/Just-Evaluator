package template;

import ast.AstList;
import ast.AstNode;

import java.util.List;

/**
 * Created by liufengkai on 2017/7/18.
 */
public class Block extends AstList {

    public Block(List<AstNode> children) {
        super(children, 0);
    }
}
