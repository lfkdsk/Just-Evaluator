package exception;


import ast.AstNode;

/**
 * Runtime eval in
 *
 * @author liufengkai
 *         Created by liufengkai on 17/7/18.
 */

public class EvalException extends RuntimeException {

    public EvalException(String msg) {
        super(msg);
    }

    public EvalException(String msg, AstNode tree) {
        super(msg + " " + tree.location());
    }

}
