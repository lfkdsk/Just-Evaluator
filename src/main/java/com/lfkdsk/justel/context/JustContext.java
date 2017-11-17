package com.lfkdsk.justel.context;

import com.lfkdsk.justel.ast.function.ExtendFunctionExpr;
import com.lfkdsk.justel.compile.generate.Var;

import java.util.Collection;
import java.util.List;

/**
 * Context => { key : name }
 *
 * @author liufengkai
 * Created by liufengkai on 2017/7/18.
 */
public interface JustContext {

    boolean contain(String name);

    Object get(String objName);

    Object put(String key, Object val);

    Object getCache(Integer astHash);

    Object putCache(Integer key, Object val);

    ExtendFunctionExpr putExtendFunc(ExtendFunctionExpr expr);

    ExtendFunctionExpr getExtendFunc(String name);

    /**
     * Warning:!!!
     * Add Code to Java Source Code Straightly
     *
     * @param command command line
     * @return value
     */
    Object command(String command);

    Collection<String> varsKeySet();

    List<String> commandList();

    boolean clearVars();

    default int indexOf(String key) {
        throw new UnsupportedOperationException("Unsupported method indexOf ");
    }

    default Object getWith(int index) {
        throw new UnsupportedOperationException("Unsupported method getWith ");
    }

    default String generateVarAssignCode(Var var) {
        StringBuilder builder = new StringBuilder();

        String typeDeclare = Var.getTypeDeclare(var.getType());

        builder.append(typeDeclare).append(" ")
               .append(var.getName()).append("=")
               .append("((").append(var.getType().getCanonicalName()).append(")")
               .append("context.get(\"").append(var.getName()).append("\")").append(");");

        return builder.toString();
    }
}
