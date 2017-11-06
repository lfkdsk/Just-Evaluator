package com.lfkdsk.justel.ast.tree;

import com.lfkdsk.justel.ast.base.AstList;
import com.lfkdsk.justel.ast.base.AstNode;

import java.util.List;

public class AstCollection extends AstList {
    public AstCollection(List<AstNode> children) {
        super(children, AstNode.COLLECTION);
    }
}
