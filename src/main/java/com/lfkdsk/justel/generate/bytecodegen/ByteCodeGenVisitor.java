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
import com.lfkdsk.justel.generate.WrapperGenCodeVisitor;
import com.lfkdsk.justel.literal.BoolLiteral;
import com.lfkdsk.justel.literal.IDLiteral;
import com.lfkdsk.justel.literal.NumberLiteral;
import com.lfkdsk.justel.literal.StringLiteral;
import com.lfkdsk.justel.token.NumberToken;
import com.lfkdsk.justel.token.Token;
import com.lfkdsk.justel.utils.GeneratedId;
import com.lfkdsk.justel.utils.asm.ClassBuilder;
import com.lfkdsk.justel.utils.asm.MethodBuilder;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.util.Stack;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.V1_7;

public class ByteCodeGenVisitor extends WrapperGenCodeVisitor {

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

    public ByteCodeGenVisitor(JustContext env) {
        super(env);

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
    public String visitAstLeaf(AstLeaf leaf) {
        return super.visitAstLeaf(leaf);
    }

    @Override
    public String visitAstList(AstList list) {
        return super.visitAstList(list);
    }

    @Override
    public String visitBoolLiteral(BoolLiteral visitor) {
        return super.visitBoolLiteral(visitor);
    }

    @Override
    public String visitIDLiteral(IDLiteral visitor) {
        return super.visitIDLiteral(visitor);
    }

    @Override
    public String visitNumberLiteral(NumberLiteral visitor) {
        NumberToken token = visitor.numberToken();
        switch (token.getTag()) {
            case Token.INTEGER: {
                eval.sipush(token.integerValue());
                // push
                evalStack.push(token.integerValue());
                break;
            }
            case Token.DOUBLE: {
                eval.sipush(token.integerValue());
                // push
                evalStack.push(token.integerValue());
                break;
            }
        }

        return super.visitNumberLiteral(visitor);
    }

    @Override
    public String visitStringLiteral(StringLiteral visitor) {
        return super.visitStringLiteral(visitor);
    }

    @Override
    public String visitExtendFunctionExpr(ExtendFunctionExpr extendFunctionExpr) {
        return super.visitExtendFunctionExpr(extendFunctionExpr);
    }

    @Override
    public String visitOperatorExpr(OperatorExpr operatorExpr) {
        return super.visitOperatorExpr(operatorExpr);
    }

    @Override
    public String visitAmpersandOp(AmpersandOp visitor) {
        return super.visitAmpersandOp(visitor);
    }

    @Override
    public String visitAndOp(AndOp visitor) {
        return super.visitAndOp(visitor);
    }

    @Override
    public String visitArrayIndexExpr(ArrayIndexExpr visitor) {
        return super.visitArrayIndexExpr(visitor);
    }

    @Override
    public String visitCondOp(CondOp visitor) {
        return super.visitCondOp(visitor);
    }

    @Override
    public String visitDivOp(DivOp visitor) {
        return super.visitDivOp(visitor);
    }

    @Override
    public String visitDotExpr(DotExpr visitor) {
        return super.visitDotExpr(visitor);
    }

    @Override
    public String visitEqualOp(EqualOp visitor) {
        return super.visitEqualOp(visitor);
    }

    @Override
    public String visitGreaterThanEqualOp(GreaterThanEqualOp visitor) {
        return super.visitGreaterThanEqualOp(visitor);
    }

    @Override
    public String visitGreaterThanOp(GreaterThanOp visitor) {
        return super.visitGreaterThanOp(visitor);
    }

    @Override
    public String visitLessThanEqualOp(LessThanEqualOp visitor) {
        return super.visitLessThanEqualOp(visitor);
    }

    @Override
    public String visitLessThanOp(LessThanOp visitor) {
        return super.visitLessThanOp(visitor);
    }

    @Override
    public String visitMinusOp(MinusOp visitor) {
        return super.visitMinusOp(visitor);
    }

    @Override
    public String visitModOp(ModOp visitor) {
        return super.visitModOp(visitor);
    }

    @Override
    public String visitMulOp(MulOp visitor) {
        return super.visitMulOp(visitor);
    }

    @Override
    public String visitOrOp(OrOp visitor) {
        return super.visitOrOp(visitor);
    }

    @Override
    public String visitPlusOp(PlusOp visitor) {
        return super.visitPlusOp(visitor);
    }

    @Override
    public String visitUnEqualOp(UnEqualOp visitor) {
        return super.visitUnEqualOp(visitor);
    }

    @Override
    public String visitNegativePostfix(NegativePostfix visitor) {
        return super.visitNegativePostfix(visitor);
    }

    @Override
    public String visitNotPostfix(NotPostfix visitor) {
        return super.visitNotPostfix(visitor);
    }

    @Override
    public String visitAstBinaryExpr(AstBinaryExpr visitor) {
        return super.visitAstBinaryExpr(visitor);
    }

    @Override
    public String visitAstCondExpr(AstCondExpr visitor) {
        return super.visitAstCondExpr(visitor);
    }

    @Override
    public String visitAstFuncArguments(AstFuncArguments visitor) {
        return super.visitAstFuncArguments(visitor);
    }

    @Override
    public String visitAstFuncExpr(AstFuncExpr visitor) {
        return super.visitAstFuncExpr(visitor);
    }

    @Override
    public String visitAstPrimaryExpr(AstPrimaryExpr visitor) {
        return super.visitAstPrimaryExpr(visitor);
    }

    @Override
    public String visitAstProgram(AstProgram visitor) {
        return super.visitAstProgram(visitor);
    }
}
