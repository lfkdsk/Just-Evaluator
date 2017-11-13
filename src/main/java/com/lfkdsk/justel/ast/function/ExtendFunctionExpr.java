/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.function;

import com.lfkdsk.justel.ast.tree.AstFuncArguments;
import com.lfkdsk.justel.ast.tree.AstFuncExpr;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.eval.Evaluable;
import com.lfkdsk.justel.exception.EvalException;
import org.jetbrains.annotations.NotNull;

/**
 * ExtendFunction Basic Expr
 *
 * @author liufengkai
 * Created by liufengkai on 2017/8/3.
 */
public abstract class ExtendFunctionExpr implements Function, Evaluable {

    /**
     * FuncExpr Node
     */
    private AstFuncExpr astFuncNode;

    /**
     * bind this to AstFuncNode
     *
     * @param astFuncNode FuncExpr Node
     */
    public void bindToAstFunc(AstFuncExpr astFuncNode) {
        this.astFuncNode = astFuncNode;
    }

    @Override
    public Object eval(JustContext env) {
        if (astFuncNode != null) {
            AstFuncArguments args = astFuncNode.funcArgs();
            int count = args.childCount();
            // compute new args
            Object[] newArgs = new Object[count];
            for (int i = 0; i < count; i++) {
                newArgs[i] = args.child(i).eval(env);
            }

            if (!paramsCheck(newArgs)) {
                throw new EvalException("didn't pass params check.");
            }

            return call(newArgs);
        }

        throw new EvalException("Undefined eval func");
    }

    /**
     * call the function value
     *
     * @param params params
     * @return is valid?
     */
    public abstract Object call(Object... params);

    /**
     * check params value and type
     *
     * @param params paramsx
     * @return is valid?
     */
    protected boolean paramsCheck(Object[] params) {
        return true;
    }

    public static ExtendFunctionExpr of(@NotNull final String funcName,
                                        @NotNull final java.util.function.Function<Object[], Object> call) {
        return new ExtendFunctionExpr() {
            @Override
            public Object call(Object... params) {
                return call.apply(params);
            }

            @Override
            public String funcName() {
                return funcName;
            }
        };
    }
}
