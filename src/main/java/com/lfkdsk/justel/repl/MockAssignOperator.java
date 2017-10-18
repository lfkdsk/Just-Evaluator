package com.lfkdsk.justel.repl;

import com.lfkdsk.justel.ast.base.AstLeaf;
import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.ast.function.OperatorExpr;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.exception.EvalException;
import com.lfkdsk.justel.literal.IDLiteral;
import com.lfkdsk.justel.token.Token;

import java.util.List;

/**
 * = operator for REPL
 *
 * @author liufengkai
 */
public class MockAssignOperator extends OperatorExpr {

    public MockAssignOperator(List<AstNode> children) {
        super(children, 2000);
    }

    @Override
    public String funcName() {
        return "=";
    }

    @Override
    public String compile(JustContext env) {
        StringBuilder builder = new StringBuilder();

        // add value to env
        eval(env);

        builder.append(leftChild().toString())
               .append("=")
               .append(rightChild().compile(env));

        return builder.toString();
    }

    @Override
    public Object eval(JustContext env) {
        AstLeaf node = (AstLeaf) leftChild();
        Object value = rightChild().eval(env);
        int tag = node.token().getTag();
        if (tag == Token.ID) {
            // 重设值
            env.put(((IDLiteral) node).name(), value);
            return value;
        } else {
            throw new EvalException("bad assign ", node);
        }
    }
}
