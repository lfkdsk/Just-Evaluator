package com.lfkdsk.justel.lattice.ast;

import com.lfkdsk.justel.ast.base.AstList;
import com.lfkdsk.justel.ast.base.AstNode;

import java.util.List;

public class AstFunctionName extends AstList {
    public AstFunctionName(List<AstNode> children) {
        super(children, AstNode.FUNCTION_NAME);
    }

    public List<AstNode> args() {
        return children.subList(1, children.size());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(".")
               .append(child(0))
               .append("(");

        List<AstNode> args = args();
        for (int i = 0; i < args.size(); i++) {
            builder.append(args.get(i).toString());
            if (i != args.size() - 1) {
                builder.append(",");
            }
        }

        return builder.append(")").toString();
    }
}
