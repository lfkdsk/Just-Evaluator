package com.lfkdsk.justel.parser;

import com.lfkdsk.justel.ast.base.AstList;
import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.ast.function.Operator;
import com.lfkdsk.justel.ast.tree.*;

import static com.lfkdsk.justel.ast.tree.AstCondExpr.isAstCondExpr;
import static com.lfkdsk.justel.ast.tree.AstFuncExpr.isAstFuncExpr;

/**
 * Parser Helper
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/8/18.
 */
final class ParserHelper {

    private ParserHelper() {

    }

    /**
     * Reset AstBinaryExpr to Particular Expr
     * We use BinaryExpr to handle priority of Operator.
     * So we should change it to particular Expr to compute the name.
     *
     * @param expr      Origin AstBinaryExpr
     * @param operators Support Operators
     * @return New Particular Expr
     */
    static AstNode resetAstExpr(AstBinaryExpr expr, BnfCom.Operators operators) {

        // operator is Operator
        Operator operator = (Operator) expr.midOp();
        // get the factory of sub-node
        BnfCom.Factory factory = operators.get(operator.operator()).factory;
        // use list to make new node

        return factory.make(expr.getChildren());
    }

    /**
     * transform binary expr to spec expr
     *
     * @param parent    parent node
     * @param operators Operators
     * @see JustParserImpl
     */
    static AstNode transformAst(AstNode parent, BnfCom.Operators operators) {
        for (int i = 0; i < parent.childCount(); i++) {
            AstNode child = parent.child(i);

            // fix binary => special expr
            if (child instanceof AstBinaryExpr) {
                child = resetAstExpr((AstBinaryExpr) child, operators);
                parent.replaceChild(i, child);

            } else if (child instanceof AstPrimaryExpr) {
                // fix primary(func) => function expr
                if (isAstFuncExpr(child)) {
                    child = new AstFuncExpr(((AstPrimaryExpr) child).getChildren());
                    parent.replaceChild(i, child);
                }
            } else if (isAstCondExpr(child)) {
                // fix primary(cond) => cond expr
                child = new AstCondExpr(((AstList) child).getChildren());
                parent.replaceChild(i, child);
            }

            transformAst(child, operators);
        }

        return parent;
    }



    static AstNode generateAst(AstNode parent, BnfCom.Operators operators) {
        AstProgram root = (AstProgram) transformAst(parent, operators);

        root.checkConst();

        return root;
    }
}
