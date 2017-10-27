package com.lfkdsk.justel.generate.bytecodegen;

import com.lfkdsk.justel.ast.base.AstLeaf;
import com.lfkdsk.justel.ast.base.AstList;
import com.lfkdsk.justel.ast.function.ExtendFunctionExpr;
import com.lfkdsk.justel.ast.function.OperatorExpr;
import com.lfkdsk.justel.ast.operators.*;
import com.lfkdsk.justel.ast.postfix.NegativePostfix;
import com.lfkdsk.justel.ast.postfix.NotPostfix;
import com.lfkdsk.justel.ast.tree.*;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.literal.BoolLiteral;
import com.lfkdsk.justel.literal.IDLiteral;
import com.lfkdsk.justel.literal.NumberLiteral;
import com.lfkdsk.justel.literal.StringLiteral;
import com.lfkdsk.justel.token.NumberToken;
import com.lfkdsk.justel.token.Token;
import com.lfkdsk.justel.utils.GeneratedId;
import com.lfkdsk.justel.utils.asm.ClassBuilder;
import com.lfkdsk.justel.utils.asm.MethodBuilder;
import com.lfkdsk.justel.visitor.AstVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static com.lfkdsk.justel.utils.ReflectUtils.isPrimitiveOrWrapNumber;
import static com.lfkdsk.justel.utils.ReflectUtils.toPrimitiveClass;
import static com.lfkdsk.justel.utils.ReflectUtils.toWrapperClass;
import static com.lfkdsk.justel.utils.TypeUtils.isInteger;
import static com.lfkdsk.justel.utils.TypeUtils.isString;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.V1_7;

public class ByteCodeGenVisitor implements AstVisitor<Object> {

    private final int generateClassId = GeneratedId.generateAtomId();
    private final String className = "com/lfkdsk/justel/generatecode/JustEL" + generateClassId;
    private final String classInterface = "com/lfkdsk/justel/eval/Expression";
    private final String contextDesc = "com/lfkdsk/justel/context/JustContext";
    private final String methodDesc = "(Lcom/lfkdsk/justel/context/JustContext;)Ljava/lang/Object;";
    private final String contextGetDesc = "(Ljava/lang/String;)Ljava/lang/Object;";

    /**
     * Class Builder -- builder
     * Control Class Structure
     */
    private final ClassBuilder builder;

    /**
     * Method Constructor
     */
    private final MethodBuilder constructor;

    /**
     * Method Eval -- Expression Eval
     */
    private final MethodBuilder eval;

    public static class OperatorStack {

        public final Stack<Object> evalStack = new Stack<>();

        public final Map<String, Integer> localMap = new HashMap<>();

        private int operatorCounts = 0;

        private int maxStacks = 0;

        private int maxLocals = 1;

        public void pushOperator() {
            this.pushOperator(0);
        }

        public void popOperator() {
            this.operatorCounts--;
        }

        public void pushOperator(int extra) {
            this.operatorCounts++;
            this.operatorCounts += extra;
            this.setMaxStacks(operatorCounts);
        }

        public void setMaxLocals(int maxLocals) {
            this.maxLocals = maxLocals;
        }

        public void setMaxStacks(int maxStacks) {
            if (maxStacks > this.maxStacks) {
                this.maxStacks = maxStacks;
            }
        }

        public Object push(Object obj) {
            return evalStack.push(obj);
        }

        public Object pop() {
            return evalStack.pop();
        }

        public int getOperatorCounts() {
            return operatorCounts;
        }

        public int getLocalCounts() {
            return maxLocals;
        }

        public int addLocalCount() {
            return maxLocals++;
        }
    }

    private final OperatorStack evalStack = new OperatorStack();

    private final JustContext env;

    public ByteCodeGenVisitor(JustContext env) {
        this.env = env;

        ///////////////////////////////////////////////////////////////////////////
        // Generate Constructor && Method
        ///////////////////////////////////////////////////////////////////////////

        builder = new ClassBuilder(
                V1_7,
                ACC_PUBLIC,
                className,
                null,
                Type.getInternalName(Object.class),
                new String[]{classInterface},
                ClassWriter.COMPUTE_FRAMES, true);

        constructor = builder.newMethod(
                Opcodes.ACC_PUBLIC,
                "<init>",
                "()V",
                null,
                null,
                0,
                0);

        constructor.aload_0();
        constructor.invokespecial("java/lang/Object", "<init>", "()V");
        constructor.return_();

        eval = builder.newMethod(
                ACC_PUBLIC,
                "eval",
                methodDesc,
                null,
                null,
                0, 0
        );

        start();
    }

    private void loadEnv() {
        evalStack.pushOperator();
        eval.aload_1();
    }

    private void removeEnv() {
        evalStack.popOperator();
    }

    private void start() {
        for (String varName : env.varsKeySet()) {
            loadEnv();

            eval.ldc(varName);
            eval.invokeinterface(
                    Type.getInternalName(JustContext.class),
                    "get",
                    Type.getMethodDescriptor(
                            Type.getType(Object.class),
                            Type.getType(String.class))
            );
            eval.checkcast(Type.getInternalName(env.get(varName).getClass()));

            evalStack.addLocalCount();
            eval.astore(evalStack.getLocalCounts());
            evalStack.localMap.put(varName, evalStack.getLocalCounts());

            removeEnv();
        }
    }


    @Override
    public Object visitAstLeaf(AstLeaf leaf) {
        return leaf.eval(env);
    }

    @Override
    public Object visitAstList(AstList list) {
        return list.eval(env);
    }

    @Override
    public Object visitBoolLiteral(BoolLiteral visitor) {
        Boolean value = visitor.value();
        if (value) {
            eval.iconst_1();
        } else {
            eval.iconst_0();
        }

        // true or false
        evalStack.push(value);
        evalStack.pushOperator();

        return visitor.eval(env);
    }

    @Override
    public Object visitIDLiteral(IDLiteral visitor) {
        Integer integer = evalStack.localMap.get(visitor.name());

        if (integer != null) {
            eval.aload(integer);
            evalStack.push(env.get(visitor.name()));
            evalStack.pushOperator();

            return visitor.eval(env);
        }

        return null;
    }

    @Override
    public Object visitNumberLiteral(NumberLiteral visitor) {
        NumberToken token = visitor.numberToken();
        switch (token.getTag()) {
            case Token.INTEGER: {
                int value = token.integerValue();
                // -128 ~ 127
                if (value >= -128 && value <= 127) {
                    eval.bipush(value);
                }
                // -32768~32767
                else if (value >= -32768 && value <= 32768) {
                    eval.sipush(value);
                } else {
                    eval.ldc(value);
                }

                // push
                evalStack.push(token.integerValue());
                break;
            }
            case Token.DOUBLE: {
                // push double => stack
                eval.ldc2_w(token.doubleValue());
                // push
                evalStack.push(token.doubleValue());
                break;
            }
            case Token.FLOAT: {
                eval.ldc(token.floatValue());
                evalStack.push(token.floatValue());
                break;
            }
            case Token.LONG: {
                eval.ldc2_w(token.longValue());
                evalStack.push(token.longValue());
                break;
            }
        }

        evalStack.pushOperator();
        return visitor.eval(env);
    }

    @Override
    public Object visitStringLiteral(StringLiteral visitor) {
        // push string => stack
        eval.ldc(visitor.value());
        evalStack.push(visitor.value());
        evalStack.pushOperator();

        return builder;
    }

    @Override
    public Object visitExtendFunctionExpr(ExtendFunctionExpr extendFunctionExpr) {
        return null;
    }

    @Override
    public Object visitOperatorExpr(OperatorExpr operatorExpr) {
        System.out.println(operatorExpr.compile(env));
        return null;
    }

    @Override
    public Object visitAmpersandOp(AmpersandOp visitor) {
        return null;
    }

    @Override
    public Object visitAndOp(AndOp visitor) {
        return visitor.eval(env);
    }

    @Override
    public Object visitArrayIndexExpr(ArrayIndexExpr visitor) {
        return null;
    }

    @Override
    public Object visitCondOp(CondOp visitor) {
        return null;
    }

    @Override
    public Object visitDivOp(DivOp visitor) {
        return null;
    }

    @Override
    public Object visitDotExpr(DotExpr visitor) {
        return null;
    }

    @Override
    public Object visitEqualOp(EqualOp visitor) {
        return null;
    }

    @Override
    public Object visitGreaterThanEqualOp(GreaterThanEqualOp visitor) {
        return null;
    }

    @Override
    public Object visitGreaterThanOp(GreaterThanOp visitor) {
        return null;
    }

    @Override
    public Object visitLessThanEqualOp(LessThanEqualOp visitor) {
        return null;
    }

    @Override
    public Object visitLessThanOp(LessThanOp visitor) {
        return null;
    }

    @Override
    public Object visitMinusOp(MinusOp visitor) {
        return null;
    }

    @Override
    public Object visitModOp(ModOp visitor) {
        return null;
    }

    @Override
    public Object visitMulOp(MulOp visitor) {
        return null;
    }

    @Override
    public Object visitOrOp(OrOp visitor) {
        return null;
    }

    @Override
    public Object visitPlusOp(PlusOp visitor) {
        Object left = visitor.leftChild().accept(this);
        Object right = visitor.rightChild().accept(this);
        if (isString(left) && isString(right)) {
            String stringBuilderType = Type.getInternalName(StringBuilder.class);
            eval.new_(stringBuilderType);
            eval.dup();
            eval.invokespecial(stringBuilderType, "<init>", "()V");
//            eval.
        } else if (isInteger(left) && isInteger(right)){
            eval.iadd();
        }

        return visitor.eval(env);
    }

    @Override
    public Object visitUnEqualOp(UnEqualOp visitor) {
        return null;
    }

    @Override
    public Object visitNegativePostfix(NegativePostfix visitor) {
        return null;
    }

    @Override
    public Object visitNotPostfix(NotPostfix visitor) {
        return null;
    }

    @Override
    public Object visitAstBinaryExpr(AstBinaryExpr visitor) {
        return null;
    }

    @Override
    public Object visitAstCondExpr(AstCondExpr visitor) {
        return null;
    }

    @Override
    public Object visitAstFuncArguments(AstFuncArguments visitor) {
        return null;
    }

    @Override
    public Object visitAstFuncExpr(AstFuncExpr visitor) {
        return null;
    }

    @Override
    public Object visitAstPrimaryExpr(AstPrimaryExpr visitor) {
        return null;
    }

    @Override
    public Object visitAstProgram(AstProgram visitor) {
        visitor.program().accept(this);

        return builder;
    }

    public byte[] toByteArray() {
        evalStack.popOperator();
        Object obj = evalStack.pop();
        if (isPrimitiveOrWrapNumber(obj.getClass())) {
            Class<?> wrapperClass = toWrapperClass(obj.getClass());
            Class<?> primaryClass = toPrimitiveClass(obj.getClass());
            eval.invokestatic(
                    Type.getInternalName(wrapperClass),
                    "valueOf",
                    Type.getMethodDescriptor(Type.getType(wrapperClass), Type.getType(primaryClass)));
        }

//        eval.invokestatic("java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
        eval.areturn();
        return builder.toByteArray();
    }
}
