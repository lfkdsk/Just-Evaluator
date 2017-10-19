package com.lfkdsk.justel.visitor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface VisitorBinder<T> {

    default <E extends AstVisitor<T>> Method findVisitorMethod(E visitor, Class<?> type) {
        if (type == Object.class) {
            return null;
        } else {
            try {
                return visitor.getClass().getMethod("visit" + type.getSimpleName(), type);
            } catch (NoSuchMethodException e) {
                return findVisitorMethod(visitor, type.getSuperclass());
            }
        }
    }

    @SuppressWarnings("unchecked")
    default <E extends AstVisitor> T accept(E visitor)  {
        Method method = findVisitorMethod(visitor, getClass());
        if (method != null) {
            try {
                return (T) method.invoke(visitor, this);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new UnsupportedOperationException(e);
            }
        }

        throw new UnsupportedOperationException("cannot find method : " + "visit" + getClass().getSimpleName());
    }
}
