package com.lfkdsk.justel.ast.operators;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.ast.function.OperatorExpr;
import com.lfkdsk.justel.ast.tree.AstPostfixExpr;
import com.lfkdsk.justel.context.JustContext;

import java.util.*;
import java.util.function.Function;

public class AstSystemMethodExpr extends OperatorExpr implements AstPostfixExpr {

    private static Map<String, Function<List, Boolean>> methodHandler = new HashMap<>();

    static {
        methodHandler.put("isEmpty", (params -> {
            if (params == null) {
                return true;
            }

            if (params.size() == 1) {
                Object obj = params.get(0);
                if (obj instanceof String) {
                    return ((String) obj).isEmpty();
                } else if (obj instanceof List) {
                    return ((List) obj).isEmpty();
                }
            }

            return false;
        }));

        methodHandler.put("isNotEmpty", (params -> {
            if (params == null) {
                return false;
            }

            if (params.size() == 1) {
                Object obj = params.get(0);
                if (obj instanceof List) {
                    return !((List) obj).isEmpty();
                } else if (obj instanceof String) {
                    return !((String) obj).isEmpty();
                }
            }

            return false;
        }));

        methodHandler.put("isBlank", (params -> {
            if (params == null) {
                return true;
            }

            if (params.size() == 1) {
                Object obj = params.get(0);
                return obj instanceof String && ((String) obj).isEmpty();
            }

            return true;
        }));

        methodHandler.put("isNotBlank", (params -> {
            if (params == null) {
                return false;
            }

            if (params.size() == 1) {
                Object obj = params.get(0);
                return obj instanceof String && !((String) obj).isEmpty();
            }

            return true;
        }));

        methodHandler.put("isNull", (Objects::isNull));

        methodHandler.put("isNotNull", (Objects::nonNull));



    }

    public AstSystemMethodExpr(List<AstNode> children) {
        super(children, AstNode.SYSTEM_SUPPORT);
    }

    public AstNode method() {
        return child(0);
    }

    public List<AstNode> args() {
        return children.subList(1, children.size());
    }

    @Override
    public boolean isConstNode() {
        return false;
    }

    @Override
    protected boolean isShouldSplit() {
        return false;
    }

    @Override
    public Object eval(JustContext env, Object value) {
        String methodName = method().toString();
        Function<List, Boolean> convert = methodHandler.get(methodName);

        if (convert != null) {
            List<Object> argsList = new ArrayList<>();
            argsList.add(value);
            argsList.addAll(args());

            return convert.apply(argsList);
        }

        return null;
    }

    @Override
    public Object compile(JustContext env, Object value, StringBuilder builder) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(")
               .append(method().toString())
               .append(" ");

        for (AstNode node : args()) {
            builder.append(" ").append(node.toString());
        }

        return builder.append(")").toString();
    }
}
