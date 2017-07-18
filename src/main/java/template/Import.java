package template;

import ast.AstList;
import ast.AstNode;
import compile.Compileable;
import context.JustContext;

import java.util.List;

/**
 * Created by liufengkai on 2017/7/18.
 */
public class Import extends AstList implements Compileable {

    public Import(List<AstNode> children) {
        super(children, 0);
    }

    @Override
    public StringBuilder compile(StringBuilder builder, JustContext context) {
        return null;
    }
}
