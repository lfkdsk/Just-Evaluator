package com.lfkdsk.justel.lattice.ast;

import com.lfkdsk.justel.ast.base.AstList;
import com.lfkdsk.justel.ast.base.AstNode;

import java.util.List;

public class AstFunctionName extends AstList{
    public AstFunctionName(List<AstNode> children) {
        super(children, AstNode.FUNCTION_NAME);
    }


}
