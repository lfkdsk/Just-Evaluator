package com.lfkdsk.justel.utils.collection;

import com.lfkdsk.justel.compile.generate.Var;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IndexEntryTest {

    private ArrayBinder<String, Object> arrayBinder;

    @BeforeEach
    void setUp() {
        arrayBinder = new ArrayBinder<>();
        Logger.init();
    }

    @Test
    void testIndexEntryTest() {
        arrayBinder.put("lfkdsk", new Object());
        arrayBinder.put("lfkdsk1", new Object());
        arrayBinder.put("lfkdsk2", new Object());
        Logger.d(String.valueOf(arrayBinder.indexOf("lfkdsk")));
        Logger.d(String.valueOf(arrayBinder.indexOf("lfkdsk2")));
        Logger.d(String.valueOf(arrayBinder.indexOf("lfkdsk1")));
    }

    public String generateVarAssignCode(Var var) {
        StringBuilder builder = new StringBuilder();

        String typeDeclare = Var.getTypeDeclare(var.getType());

        builder.append(typeDeclare).append(" ")
               .append(var.getName()).append("=")
               .append("((").append(var.getType().getCanonicalName()).append(")")
               .append("context.getWith(").append(arrayBinder.indexOf(var.getName())).append(")").append(");");

        return builder.toString();
    }

    @Test
    void testVar() {
        Var var = new Var("lfkdsk", new Object());
        arrayBinder.put(var.getName(), var.getValue());
        Logger.v(generateVarAssignCode(var));
    }
}