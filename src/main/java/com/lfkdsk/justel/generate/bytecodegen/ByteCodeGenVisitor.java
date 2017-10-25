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

import java.util.Stack;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.V1_7;

public class ByteCodeGenVisitor implements AstVisitor<ClassBuilder> {

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

    private final Stack<Object> evalStack = new Stack<>();

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
                ClassWriter.COMPUTE_FRAMES);

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
    }


    @Override
    public ClassBuilder visitAstLeaf(AstLeaf leaf) {
        return null;
    }

    @Override
    public ClassBuilder visitAstList(AstList list) {
        return null;
    }

    @Override
    public ClassBuilder visitBoolLiteral(BoolLiteral visitor) {
        Boolean value = visitor.value();
        if (value) {
            eval.iconst_1();
        } else {
            eval.iconst_0();
        }

        evalStack.push(value);
        return builder;
    }

    @Override
    public ClassBuilder visitIDLiteral(IDLiteral visitor) {
        return builder;
    }

    @Override
    public ClassBuilder visitNumberLiteral(NumberLiteral visitor) {
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

        return builder;
    }

    @Override
    public ClassBuilder visitStringLiteral(StringLiteral visitor) {
        // push string => stack
        eval.ldc(visitor.value());
        evalStack.push(visitor.value());

        return builder;
    }

    @Override
    public ClassBuilder visitExtendFunctionExpr(ExtendFunctionExpr extendFunctionExpr) {
        return null;
    }

    @Override
    public ClassBuilder visitOperatorExpr(OperatorExpr operatorExpr) {
        return null;
    }

    @Override
    public ClassBuilder visitAmpersandOp(AmpersandOp visitor) {
        return null;
    }

    @Override
    public ClassBuilder visitAndOp(AndOp visitor) {
        return null;
    }

    @Override
    public ClassBuilder visitArrayIndexExpr(ArrayIndexExpr visitor) {
        return null;
    }

    @Override
    public ClassBuilder visitCondOp(CondOp visitor) {
        return null;
    }

    @Override
    public ClassBuilder visitDivOp(DivOp visitor) {
        return null;
    }

    @Override
    public ClassBuilder visitDotExpr(DotExpr visitor) {
        return null;
    }

    @Override
    public ClassBuilder visitEqualOp(EqualOp visitor) {
        return null;
    }

    @Override
    public ClassBuilder visitGreaterThanEqualOp(GreaterThanEqualOp visitor) {
        return null;
    }

    @Override
    public ClassBuilder visitGreaterThanOp(GreaterThanOp visitor) {
        return null;
    }

    @Override
    public ClassBuilder visitLessThanEqualOp(LessThanEqualOp visitor) {
        return null;
    }

    @Override
    public ClassBuilder visitLessThanOp(LessThanOp visitor) {
        return null;
    }

    @Override
    public ClassBuilder visitMinusOp(MinusOp visitor) {
        return null;
    }

    @Override
    public ClassBuilder visitModOp(ModOp visitor) {
        return null;
    }

    @Override
    public ClassBuilder visitMulOp(MulOp visitor) {
        return null;
    }

    @Override
    public ClassBuilder visitOrOp(OrOp visitor) {
        return null;
    }

    @Override
    public ClassBuilder visitPlusOp(PlusOp visitor) {
        return null;
    }

    @Override
    public ClassBuilder visitUnEqualOp(UnEqualOp visitor) {
        return null;
    }

    @Override
    public ClassBuilder visitNegativePostfix(NegativePostfix visitor) {
        return null;
    }

    @Override
    public ClassBuilder visitNotPostfix(NotPostfix visitor) {
        return null;
    }

    @Override
    public ClassBuilder visitAstBinaryExpr(AstBinaryExpr visitor) {
        return null;
    }

    @Override
    public ClassBuilder visitAstCondExpr(AstCondExpr visitor) {
        return null;
    }

    @Override
    public ClassBuilder visitAstFuncArguments(AstFuncArguments visitor) {
        return null;
    }

    @Override
    public ClassBuilder visitAstFuncExpr(AstFuncExpr visitor) {
        return null;
    }

    @Override
    public ClassBuilder visitAstPrimaryExpr(AstPrimaryExpr visitor) {
        return null;
    }

    @Override
    public ClassBuilder visitAstProgram(AstProgram visitor) {
        return null;
    }

}
