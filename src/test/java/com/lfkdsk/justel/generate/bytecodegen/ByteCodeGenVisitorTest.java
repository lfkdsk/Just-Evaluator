package com.lfkdsk.justel.generate.bytecodegen;

import com.lfkdsk.justel.JustEL;
import com.lfkdsk.justel.ast.tree.AstProgram;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.eval.Expression;
import com.lfkdsk.justel.utils.GeneratedId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.Type;

class ByteCodeGenVisitorTest {

    ByteCodeGenVisitor visitor;

    JustMapContext context;

    @BeforeEach
    void setUp() {
        context = new JustMapContext() {{
            put("lfkdsk", 1000000);
            put("f", 1000000);
            put("a", false);
            put("v", true);
            put("b", "fff");
        }};
        visitor = new ByteCodeGenVisitor(context);
    }

    static final class MyClassLoader extends ClassLoader {
        public Class defineClass(String name, byte[] b) {
            return defineClass(name, b, 0, b.length);
        }
    }

    private void compile(String expr) {

        final int generateClassId = GeneratedId.generateAtomId();

        final String className = "com/lfkdsk/justel/generatecode/JustEL" + generateClassId;
        final String classInterface = "com/lfkdsk/justel/eval/Expression";
        final String contextDesc = "com/lfkdsk/justel/context/JustContext";
        final String methodDesc = "(Lcom/lfkdsk/justel/context/JustContext;)Ljava/lang/Object;";
        final String contextGetDesc = "(Ljava/lang/String;)Ljava/lang/Object;";

        AstProgram program = JustEL.builder()
                                   .create()
                                   .parse(expr);



        ByteCodeGenVisitor visitor = new ByteCodeGenVisitor(context);
        visitor.visitAstProgram(program);

        try {
            Class<?> cls = new ByteCodeGeneratorTest.MyClassLoader().defineClass(Type.getType(className).getClassName(), visitor.toByteArray());
            Expression expression = (Expression) cls.newInstance();
            System.out.println(expression.eval(context));
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Test
    void visitAstLeaf() {
        compile("1");
    }

    @Test
    void visitAstList() {

    }

    @Test
    void visitBoolLiteral() {
        compile("true");
    }

    @Test
    void visitIDLiteral() {
        compile("lfkdsk + f");
    }

    @Test
    void visitNumberLiteral() {
        compile("1 + 1");
    }

    @Test
    void visitStringLiteral() {
    }

    @Test
    void visitExtendFunctionExpr() {
    }

    @Test
    void visitOperatorExpr() {
    }

    @Test
    void visitAmpersandOp() {
    }

    @Test
    void visitAndOp() {
    }

    @Test
    void visitArrayIndexExpr() {
    }

    @Test
    void visitCondOp() {
    }

    @Test
    void visitDivOp() {
    }

    @Test
    void visitDotExpr() {
    }

    @Test
    void visitEqualOp() {
    }

    @Test
    void visitGreaterThanEqualOp() {
    }

    @Test
    void visitGreaterThanOp() {
    }

    @Test
    void visitLessThanEqualOp() {
    }

    @Test
    void visitLessThanOp() {
    }

    @Test
    void visitMinusOp() {
    }

    @Test
    void visitModOp() {
    }

    @Test
    void visitMulOp() {
    }

    @Test
    void visitOrOp() {
    }

    @Test
    void visitPlusOp() {
    }

    @Test
    void visitUnEqualOp() {
    }

    @Test
    void visitNegativePostfix() {
    }

    @Test
    void visitNotPostfix() {
    }

    @Test
    void visitAstBinaryExpr() {
    }

    @Test
    void visitAstCondExpr() {
    }

    @Test
    void visitAstFuncArguments() {
    }

    @Test
    void visitAstFuncExpr() {
    }

    @Test
    void visitAstPrimaryExpr() {
    }

    @Test
    void visitAstProgram() {
    }

}