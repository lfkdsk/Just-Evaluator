/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.parser;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.ast.operators.Operator;
import com.lfkdsk.justel.ast.tree.AstBinaryExpr;
import com.lfkdsk.justel.ast.tree.AstFuncArguments;
import com.lfkdsk.justel.ast.tree.AstFuncExpr;
import com.lfkdsk.justel.ast.tree.AstPrimaryExpr;
import com.lfkdsk.justel.exception.ParseException;
import com.lfkdsk.justel.lexer.Lexer;

/**
 * Just Parser Interface
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/26.
 */
public interface JustParser {

    /**
     * Parse with Lexer(Provide Tokens)
     *
     * @param lexer Tokens Lexer
     * @return Root Node of AST
     * @throws ParseException Parse
     */
    AstNode parser(Lexer lexer) throws ParseException;

    /**
     * Reset AstBinaryExpr to Particular Expr
     * We use BinaryExpr to handle priority of Operator.
     * So we should change it to particular Expr to compute the name.
     *
     * @param expr      Origin AstBinaryExpr
     * @param operators Support Operators
     * @return New Particular Expr
     */
    default AstNode resetAstExpr(AstBinaryExpr expr, BnfCom.Operators operators) {
        // operator is Operator
        Operator operator = (Operator) expr.midOp();
        // get the factory of sub-node
        BnfCom.Factory factory = operators.get(operator.operator()).factory;
        // use list to make new node

        return factory.make(expr.getChildren());
    }

    /**
     * reset Function Expr
     *
     * @param expr Primary Expr
     * @return change primary expr => FuncExpr
     */
    default AstNode resetFuncExpr(AstPrimaryExpr expr) {
        return new AstFuncExpr(expr.getChildren());
    }

    /**
     * transform binary expr to spec expr
     *
     * @param parent    parent node
     * @param operators Operators
     * @see JustParserImpl
     */
    default AstNode transformBinaryExpr(AstNode parent, BnfCom.Operators operators) {
        for (int i = 0; i < parent.childCount(); i++) {
            AstNode child = parent.child(i);

            // fix binary => special expr
            if (child instanceof AstBinaryExpr) {
                child = resetAstExpr((AstBinaryExpr) child, operators);
                parent.replaceChild(i, child);

            } else if (child instanceof AstPrimaryExpr) {
                // fix primary => function expr
                if (child.childCount() >= 2 &&
                        child.child(1) instanceof AstFuncArguments) {
                    child = resetFuncExpr((AstPrimaryExpr) child);
                    parent.replaceChild(i, child);
                }
            }

            transformBinaryExpr(child, operators);
        }

        return parent;
    }
}
