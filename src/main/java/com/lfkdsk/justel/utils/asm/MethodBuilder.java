package com.lfkdsk.justel.utils.asm;

import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 *
 */
public class MethodBuilder {

    private MethodVisitor methodVisitor;

    public MethodBuilder(MethodVisitor methodVisitor) {
        this.methodVisitor = methodVisitor;
    }

    public MethodBuilder nop() {
        this.methodVisitor.visitInsn(Opcodes.NOP);
        return this;
    }

    public MethodBuilder aconst_null() {
        this.methodVisitor.visitInsn(Opcodes.ACONST_NULL);
        return this;
    }

    public MethodBuilder iconst_m1() {
        this.methodVisitor.visitInsn(Opcodes.ICONST_M1);
        return this;
    }

    public MethodBuilder iconst_0() {
        this.methodVisitor.visitInsn(Opcodes.ICONST_0);
        return this;
    }

    public MethodBuilder iconst_1() {
        this.methodVisitor.visitInsn(Opcodes.ICONST_1);
        return this;
    }

    public MethodBuilder iconst_2() {
        this.methodVisitor.visitInsn(Opcodes.ICONST_2);
        return this;
    }

    public MethodBuilder iconst_3() {
        this.methodVisitor.visitInsn(Opcodes.ICONST_3);
        return this;
    }

    public MethodBuilder iconst_4() {
        this.methodVisitor.visitInsn(Opcodes.ICONST_4);
        return this;
    }

    public MethodBuilder iconst_5() {
        this.methodVisitor.visitInsn(Opcodes.ICONST_5);
        return this;
    }

    public MethodBuilder lconst_0() {
        this.methodVisitor.visitInsn(Opcodes.LCONST_0);
        return this;
    }

    public MethodBuilder lconst_1() {
        this.methodVisitor.visitInsn(Opcodes.LCONST_1);
        return this;
    }

    public MethodBuilder fconst_0() {
        this.methodVisitor.visitInsn(Opcodes.FCONST_0);
        return this;
    }

    public MethodBuilder fconst_1() {
        this.methodVisitor.visitInsn(Opcodes.FCONST_1);
        return this;
    }

    public MethodBuilder fconst_2() {
        this.methodVisitor.visitInsn(Opcodes.FCONST_2);
        return this;
    }

    public MethodBuilder dconst_0() {
        this.methodVisitor.visitInsn(Opcodes.DCONST_0);
        return this;
    }

    public MethodBuilder dconst_1() {
        this.methodVisitor.visitInsn(Opcodes.DCONST_1);
        return this;
    }

    public MethodBuilder bipush(int operand) {
        this.methodVisitor.visitIntInsn(Opcodes.BIPUSH, operand);
        return this;
    }

    public MethodBuilder sipush(int operand) {
        this.methodVisitor.visitIntInsn(Opcodes.SIPUSH, operand);
        return this;
    }

    public MethodBuilder ldc(Object cst) {
        this.methodVisitor.visitLdcInsn(cst);
        return this;
    }

    public MethodBuilder ldc_w(Object cst) {
        this.methodVisitor.visitLdcInsn(cst);
        return this;
    }

    public MethodBuilder ldc2_w(Object cst) {
        this.methodVisitor.visitLdcInsn(cst);
        return this;
    }

    public MethodBuilder iload(int var) {
        this.methodVisitor.visitVarInsn(Opcodes.ILOAD, var);
        return this;
    }

    public MethodBuilder lload(int var) {
        this.methodVisitor.visitVarInsn(Opcodes.LLOAD, var);
        return this;
    }

    public MethodBuilder fload(int var) {
        this.methodVisitor.visitVarInsn(Opcodes.FLOAD, var);
        return this;
    }

    public MethodBuilder dload(int var) {
        this.methodVisitor.visitVarInsn(Opcodes.DLOAD, var);
        return this;
    }

    public MethodBuilder aload(int var) {
        this.methodVisitor.visitVarInsn(Opcodes.ALOAD, var);
        return this;
    }

    public MethodBuilder iload_0() {
        this.methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
        return this;
    }

    public MethodBuilder iload_1() {
        this.methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        return this;
    }

    public MethodBuilder iload_2() {
        this.methodVisitor.visitVarInsn(Opcodes.ILOAD, 2);
        return this;
    }

    public MethodBuilder iload_3() {
        this.methodVisitor.visitVarInsn(Opcodes.ILOAD, 3);
        return this;
    }

    public MethodBuilder lload_0() {
        this.methodVisitor.visitVarInsn(Opcodes.LLOAD, 0);
        return this;
    }

    public MethodBuilder lload_1() {
        this.methodVisitor.visitVarInsn(Opcodes.LLOAD, 1);
        return this;
    }

    public MethodBuilder lload_2() {
        this.methodVisitor.visitVarInsn(Opcodes.LLOAD, 2);
        return this;
    }

    public MethodBuilder lload_3() {
        this.methodVisitor.visitVarInsn(Opcodes.LLOAD, 3);
        return this;
    }

    public MethodBuilder fload_0() {
        this.methodVisitor.visitVarInsn(Opcodes.FLOAD, 0);
        return this;
    }

    public MethodBuilder fload_1() {
        this.methodVisitor.visitVarInsn(Opcodes.FLOAD, 1);
        return this;
    }

    public MethodBuilder fload_2() {
        this.methodVisitor.visitVarInsn(Opcodes.FLOAD, 2);
        return this;
    }

    public MethodBuilder fload_3() {
        this.methodVisitor.visitVarInsn(Opcodes.FLOAD, 3);
        return this;
    }

    public MethodBuilder dload_0() {
        this.methodVisitor.visitVarInsn(Opcodes.DLOAD, 0);
        return this;
    }

    public MethodBuilder dload_1() {
        this.methodVisitor.visitVarInsn(Opcodes.DLOAD, 1);
        return this;
    }

    public MethodBuilder dload_2() {
        this.methodVisitor.visitVarInsn(Opcodes.DLOAD, 2);
        return this;
    }

    public MethodBuilder dload_3() {
        this.methodVisitor.visitVarInsn(Opcodes.DLOAD, 3);
        return this;
    }

    public MethodBuilder aload_0() {
        this.methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        return this;
    }

    public MethodBuilder aload_1() {
        this.methodVisitor.visitVarInsn(Opcodes.ALOAD, 1);
        return this;
    }

    public MethodBuilder aload_2() {
        this.methodVisitor.visitVarInsn(Opcodes.ALOAD, 2);
        return this;
    }

    public MethodBuilder aload_3() {
        this.methodVisitor.visitVarInsn(Opcodes.ALOAD, 3);
        return this;
    }

    public MethodBuilder iaload() {
        this.methodVisitor.visitInsn(Opcodes.IALOAD);
        return this;
    }

    public MethodBuilder laload() {
        this.methodVisitor.visitInsn(Opcodes.LALOAD);
        return this;
    }

    public MethodBuilder faload() {
        this.methodVisitor.visitInsn(Opcodes.FALOAD);
        return this;
    }

    public MethodBuilder daload() {
        this.methodVisitor.visitInsn(Opcodes.DALOAD);
        return this;
    }

    public MethodBuilder aaload() {
        this.methodVisitor.visitInsn(Opcodes.AALOAD);
        return this;
    }

    public MethodBuilder baload() {
        this.methodVisitor.visitInsn(Opcodes.BALOAD);
        return this;
    }

    public MethodBuilder caload() {
        this.methodVisitor.visitInsn(Opcodes.CALOAD);
        return this;
    }

    public MethodBuilder saload() {
        this.methodVisitor.visitInsn(Opcodes.SALOAD);
        return this;
    }

    public MethodBuilder istore(int var) {
        this.methodVisitor.visitVarInsn(Opcodes.ISTORE, var);
        return this;
    }

    public MethodBuilder lstore(int var) {
        this.methodVisitor.visitVarInsn(Opcodes.LSTORE, var);
        return this;
    }

    public MethodBuilder fstore(int var) {
        this.methodVisitor.visitVarInsn(Opcodes.FSTORE, var);
        return this;
    }

    public MethodBuilder dstore(int var) {
        this.methodVisitor.visitVarInsn(Opcodes.DSTORE, var);
        return this;
    }

    public MethodBuilder astore(int var) {
        this.methodVisitor.visitVarInsn(Opcodes.ASTORE, var);
        return this;
    }

    public MethodBuilder istore_0() {
        this.methodVisitor.visitVarInsn(Opcodes.ISTORE, 0);
        return this;
    }

    public MethodBuilder istore_1() {
        this.methodVisitor.visitVarInsn(Opcodes.ISTORE, 1);
        return this;
    }

    public MethodBuilder istore_2() {
        this.methodVisitor.visitVarInsn(Opcodes.ISTORE, 2);
        return this;
    }

    public MethodBuilder istore_3() {
        this.methodVisitor.visitVarInsn(Opcodes.ISTORE, 3);
        return this;
    }

    public MethodBuilder lstore_0() {
        this.methodVisitor.visitVarInsn(Opcodes.LSTORE, 0);
        return this;
    }

    public MethodBuilder lstore_1() {
        this.methodVisitor.visitVarInsn(Opcodes.ISTORE, 1);
        return this;
    }

    public MethodBuilder lstore_2() {
        this.methodVisitor.visitVarInsn(Opcodes.ISTORE, 2);
        return this;
    }

    public MethodBuilder lstore_3() {
        this.methodVisitor.visitVarInsn(Opcodes.ISTORE, 3);
        return this;
    }

    public MethodBuilder fstore_0() {
        this.methodVisitor.visitVarInsn(Opcodes.FSTORE, 0);
        return this;
    }

    public MethodBuilder fstore_1() {
        this.methodVisitor.visitVarInsn(Opcodes.FSTORE, 1);
        return this;
    }

    public MethodBuilder fstore_2() {
        this.methodVisitor.visitVarInsn(Opcodes.FSTORE, 2);
        return this;
    }

    public MethodBuilder fstore_3() {
        this.methodVisitor.visitVarInsn(Opcodes.FSTORE, 3);
        return this;
    }

    public MethodBuilder dstore_0() {
        this.methodVisitor.visitVarInsn(Opcodes.DSTORE, 0);
        return this;
    }

    public MethodBuilder dstore_1() {
        this.methodVisitor.visitVarInsn(Opcodes.DSTORE, 1);
        return this;
    }

    public MethodBuilder dstore_2() {
        this.methodVisitor.visitVarInsn(Opcodes.DSTORE, 2);
        return this;
    }

    public MethodBuilder dstore_3() {
        this.methodVisitor.visitVarInsn(Opcodes.DSTORE, 3);
        return this;
    }

    public MethodBuilder astore_0() {
        this.methodVisitor.visitVarInsn(Opcodes.ASTORE, 0);
        return this;
    }

    public MethodBuilder astore_1() {
        this.methodVisitor.visitVarInsn(Opcodes.ASTORE, 1);
        return this;
    }

    public MethodBuilder astore_2() {
        this.methodVisitor.visitVarInsn(Opcodes.ASTORE, 2);
        return this;
    }

    public MethodBuilder astore_3() {
        this.methodVisitor.visitVarInsn(Opcodes.ASTORE, 3);
        return this;
    }

    public MethodBuilder iastore() {
        this.methodVisitor.visitInsn(Opcodes.IASTORE);
        return this;
    }

    public MethodBuilder lastore() {
        this.methodVisitor.visitInsn(Opcodes.LASTORE);
        return this;
    }

    public MethodBuilder fastore() {
        this.methodVisitor.visitInsn(Opcodes.FASTORE);
        return this;
    }

    public MethodBuilder dastore() {
        this.methodVisitor.visitInsn(Opcodes.DASTORE);
        return this;
    }

    public MethodBuilder aastore() {
        this.methodVisitor.visitInsn(Opcodes.AASTORE);
        return this;
    }

    public MethodBuilder bastore() {
        this.methodVisitor.visitInsn(Opcodes.BASTORE);
        return this;
    }

    public MethodBuilder castore() {
        this.methodVisitor.visitInsn(Opcodes.CASTORE);
        return this;
    }

    public MethodBuilder sastore() {
        this.methodVisitor.visitInsn(Opcodes.SASTORE);
        return this;
    }

    public MethodBuilder pop() {
        this.methodVisitor.visitInsn(Opcodes.POP);
        return this;
    }

    public MethodBuilder pop2() {
        this.methodVisitor.visitInsn(Opcodes.POP2);
        return this;
    }

    public MethodBuilder dup() {
        this.methodVisitor.visitInsn(Opcodes.DUP);
        return this;
    }

    public MethodBuilder dup_x1() {
        this.methodVisitor.visitInsn(Opcodes.DUP_X1);
        return this;
    }

    public MethodBuilder dup_x2() {
        this.methodVisitor.visitInsn(Opcodes.DUP_X2);
        return this;
    }

    public MethodBuilder dup2() {
        this.methodVisitor.visitInsn(Opcodes.DUP2);
        return this;
    }

    public MethodBuilder dup2_x1() {
        this.methodVisitor.visitInsn(Opcodes.DUP2_X1);
        return this;
    }

    public MethodBuilder dup2_x2() {
        this.methodVisitor.visitInsn(Opcodes.DUP2_X2);
        return this;
    }

    public MethodBuilder swap() {
        this.methodVisitor.visitInsn(Opcodes.SWAP);
        return this;
    }

    public MethodBuilder iadd() {
        this.methodVisitor.visitInsn(Opcodes.IADD);
        return this;
    }

    public MethodBuilder ladd() {
        this.methodVisitor.visitInsn(Opcodes.LADD);
        return this;
    }

    public MethodBuilder fadd() {
        this.methodVisitor.visitInsn(Opcodes.FADD);
        return this;
    }

    public MethodBuilder dadd() {
        this.methodVisitor.visitInsn(Opcodes.DADD);
        return this;
    }

    public MethodBuilder isub() {
        this.methodVisitor.visitInsn(Opcodes.ISUB);
        return this;
    }

    public MethodBuilder lsub() {
        this.methodVisitor.visitInsn(Opcodes.LSUB);
        return this;
    }

    public MethodBuilder fsub() {
        this.methodVisitor.visitInsn(Opcodes.FSUB);
        return this;
    }

    public MethodBuilder dsub() {
        this.methodVisitor.visitInsn(Opcodes.DSUB);
        return this;
    }

    public MethodBuilder imul() {
        this.methodVisitor.visitInsn(Opcodes.IMUL);
        return this;
    }

    public MethodBuilder lmul() {
        this.methodVisitor.visitInsn(Opcodes.LMUL);
        return this;
    }

    public MethodBuilder fmul() {
        this.methodVisitor.visitInsn(Opcodes.FMUL);
        return this;
    }

    public MethodBuilder dmul() {
        this.methodVisitor.visitInsn(Opcodes.DMUL);
        return this;
    }

    public MethodBuilder idiv() {
        this.methodVisitor.visitInsn(Opcodes.IDIV);
        return this;
    }

    public MethodBuilder ldiv() {
        this.methodVisitor.visitInsn(Opcodes.LDIV);
        return this;
    }

    public MethodBuilder fdiv() {
        this.methodVisitor.visitInsn(Opcodes.FDIV);
        return this;
    }

    public MethodBuilder ddiv() {
        this.methodVisitor.visitInsn(Opcodes.DDIV);
        return this;
    }

    public MethodBuilder irem() {
        this.methodVisitor.visitInsn(Opcodes.IREM);
        return this;
    }

    public MethodBuilder lrem() {
        this.methodVisitor.visitInsn(Opcodes.LREM);
        return this;
    }

    public MethodBuilder frem() {
        this.methodVisitor.visitInsn(Opcodes.FREM);
        return this;
    }

    public MethodBuilder drem() {
        this.methodVisitor.visitInsn(Opcodes.DREM);
        return this;
    }

    public MethodBuilder ineg() {
        this.methodVisitor.visitInsn(Opcodes.INEG);
        return this;
    }

    public MethodBuilder lneg() {
        this.methodVisitor.visitInsn(Opcodes.LNEG);
        return this;
    }

    public MethodBuilder fneg() {
        this.methodVisitor.visitInsn(Opcodes.FNEG);
        return this;
    }

    public MethodBuilder dneg() {
        this.methodVisitor.visitInsn(Opcodes.DNEG);
        return this;
    }

    public MethodBuilder ishl() {
        this.methodVisitor.visitInsn(Opcodes.ISHL);
        return this;
    }

    public MethodBuilder lshl() {
        this.methodVisitor.visitInsn(Opcodes.LSHL);
        return this;
    }

    public MethodBuilder ishr() {
        this.methodVisitor.visitInsn(Opcodes.ISHR);
        return this;
    }

    public MethodBuilder lshr() {
        this.methodVisitor.visitInsn(Opcodes.LSHR);
        return this;
    }

    public MethodBuilder iushr() {
        this.methodVisitor.visitInsn(Opcodes.IUSHR);
        return this;
    }

    public MethodBuilder lushr() {
        this.methodVisitor.visitInsn(Opcodes.LUSHR);
        return this;
    }

    public MethodBuilder iand() {
        this.methodVisitor.visitInsn(Opcodes.IAND);
        return this;
    }

    public MethodBuilder land() {
        this.methodVisitor.visitInsn(Opcodes.LAND);
        return this;
    }

    public MethodBuilder ior() {
        this.methodVisitor.visitInsn(Opcodes.IOR);
        return this;
    }

    public MethodBuilder lor() {
        this.methodVisitor.visitInsn(Opcodes.LOR);
        return this;
    }

    public MethodBuilder ixor() {
        this.methodVisitor.visitInsn(Opcodes.IXOR);
        return this;
    }

    public MethodBuilder lxor() {
        this.methodVisitor.visitInsn(Opcodes.LXOR);
        return this;
    }

    public MethodBuilder iinc(int var, int increment) {
        this.methodVisitor.visitIincInsn(var, increment);
        return this;
    }

    public MethodBuilder i2l() {
        this.methodVisitor.visitInsn(Opcodes.I2L);
        return this;
    }

    public MethodBuilder i2f() {
        this.methodVisitor.visitInsn(Opcodes.I2F);
        return this;
    }

    public MethodBuilder i2d() {
        this.methodVisitor.visitInsn(Opcodes.I2D);
        return this;
    }

    public MethodBuilder l2i() {
        this.methodVisitor.visitInsn(Opcodes.L2I);
        return this;
    }

    public MethodBuilder l2f() {
        this.methodVisitor.visitInsn(Opcodes.L2F);
        return this;
    }

    public MethodBuilder l2d() {
        this.methodVisitor.visitInsn(Opcodes.L2D);
        return this;
    }

    public MethodBuilder f2i() {
        this.methodVisitor.visitInsn(Opcodes.F2I);
        return this;
    }

    public MethodBuilder f2l() {
        this.methodVisitor.visitInsn(Opcodes.F2L);
        return this;
    }

    public MethodBuilder f2d() {
        this.methodVisitor.visitInsn(Opcodes.F2D);
        return this;
    }

    public MethodBuilder d2i() {
        this.methodVisitor.visitInsn(Opcodes.D2I);
        return this;
    }

    public MethodBuilder d2l() {
        this.methodVisitor.visitInsn(Opcodes.D2L);
        return this;
    }

    public MethodBuilder d2f() {
        this.methodVisitor.visitInsn(Opcodes.D2F);
        return this;
    }

    public MethodBuilder i2b() {
        this.methodVisitor.visitInsn(Opcodes.I2B);
        return this;
    }

    public MethodBuilder i2c() {
        this.methodVisitor.visitInsn(Opcodes.I2C);
        return this;
    }

    public MethodBuilder i2s() {
        this.methodVisitor.visitInsn(Opcodes.I2S);
        return this;
    }

    public MethodBuilder lcmp() {
        this.methodVisitor.visitInsn(Opcodes.LCMP);
        return this;
    }

    public MethodBuilder fcmpl() {
        this.methodVisitor.visitInsn(Opcodes.FCMPL);
        return this;
    }

    public MethodBuilder fcmpg() {
        this.methodVisitor.visitInsn(Opcodes.FCMPG);
        return this;
    }

    public MethodBuilder dcmpl() {
        this.methodVisitor.visitInsn(Opcodes.DCMPL);
        return this;
    }

    public MethodBuilder dcmpg() {
        this.methodVisitor.visitInsn(Opcodes.DCMPG);
        return this;
    }

    public MethodBuilder ifeq(Label label) {
        this.methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
        return this;
    }

    public MethodBuilder ifne(Label label) {
        this.methodVisitor.visitJumpInsn(Opcodes.IFNE, label);
        return this;
    }

    public MethodBuilder iflt(Label label) {
        this.methodVisitor.visitJumpInsn(Opcodes.IFLT, label);
        return this;
    }

    public MethodBuilder ifge(Label label) {
        this.methodVisitor.visitJumpInsn(Opcodes.IFGE, label);
        return this;
    }

    public MethodBuilder ifgt(Label label) {
        this.methodVisitor.visitJumpInsn(Opcodes.IFGT, label);
        return this;
    }

    public MethodBuilder ifle(Label label) {
        this.methodVisitor.visitJumpInsn(Opcodes.IFLE, label);
        return this;
    }

    public MethodBuilder if_icmpeq(Label label) {
        this.methodVisitor.visitJumpInsn(Opcodes.IF_ICMPEQ, label);
        return this;
    }

    public MethodBuilder if_icmpne(Label label) {
        this.methodVisitor.visitJumpInsn(Opcodes.IF_ICMPNE, label);
        return this;
    }

    public MethodBuilder if_icmplt(Label label) {
        this.methodVisitor.visitJumpInsn(Opcodes.IF_ICMPLT, label);
        return this;
    }

    public MethodBuilder if_icmpge(Label label) {
        this.methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGE, label);
        return this;
    }

    public MethodBuilder if_icmpgt(Label label) {
        this.methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGT, label);
        return this;
    }

    public MethodBuilder if_icmple(Label label) {
        this.methodVisitor.visitJumpInsn(Opcodes.IF_ICMPLE, label);
        return this;
    }

    public MethodBuilder if_acmpeq(Label label) {
        this.methodVisitor.visitJumpInsn(Opcodes.IF_ACMPEQ, label);
        return this;
    }

    public MethodBuilder if_acmpne(Label label) {
        this.methodVisitor.visitJumpInsn(Opcodes.IF_ACMPNE, label);
        return this;
    }

    public MethodBuilder goto_(Label label) {
        this.methodVisitor.visitJumpInsn(Opcodes.GOTO, label);
        return this;
    }

    public MethodBuilder jsr(Label label) {
        this.methodVisitor.visitJumpInsn(Opcodes.JSR, label);
        return this;
    }

    public MethodBuilder ret(int var) {
        this.methodVisitor.visitVarInsn(Opcodes.RET, var);
        return this;
    }

    public MethodBuilder tableswitch(int min, int max, Label defaultLabel, Label otherLabel) {
        this.methodVisitor.visitTableSwitchInsn(min, max, defaultLabel, otherLabel);
        return this;
    }

    public MethodBuilder lookupswitch(Label defaultLabel, int[] keys, Label[] otherLabels) {
        this.methodVisitor.visitLookupSwitchInsn(defaultLabel, keys, otherLabels);
        return this;
    }

    public MethodBuilder ireturn() {
        this.methodVisitor.visitInsn(Opcodes.IRETURN);
        return this;
    }

    public MethodBuilder lreturn() {
        this.methodVisitor.visitInsn(Opcodes.LRETURN);
        return this;
    }

    public MethodBuilder freturn() {
        this.methodVisitor.visitInsn(Opcodes.FRETURN);
        return this;
    }

    public MethodBuilder dreturn() {
        this.methodVisitor.visitInsn(Opcodes.DRETURN);
        return this;
    }

    public MethodBuilder areturn() {
        this.methodVisitor.visitInsn(Opcodes.ARETURN);
        return this;
    }

    public MethodBuilder return_() {
        this.methodVisitor.visitInsn(Opcodes.RETURN);
        return this;
    }

    public MethodBuilder getstatic(String fieldOwnerCls, String fieldName, String fieldDesc) {
        this.methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, fieldOwnerCls, fieldName, fieldDesc);
        return this;
    }

    public MethodBuilder putstatic(String owner, String name, String desc) {
        this.methodVisitor.visitFieldInsn(Opcodes.PUTSTATIC, owner, name, desc);
        return this;
    }

    public MethodBuilder getfield(String owner, String name, String desc) {
        this.methodVisitor.visitFieldInsn(Opcodes.GETFIELD, owner, name, desc);
        return this;
    }

    public MethodBuilder putfield(String owner, String name, String desc) {
        this.methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, owner, name, desc);
        return this;
    }

    public MethodBuilder invokevirtual(String owner, String name, String desc) {
        this.methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, owner, name, desc);
        return this;
    }

    public MethodBuilder invokespecial(String methodOwnerClsName, String methodName, String methodDesc) {
        this.methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, methodOwnerClsName, methodName, methodDesc);
        return this;
    }

    public MethodBuilder invokestatic(String owner, String name, String desc) {
        this.methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, owner, name, desc);
        return this;
    }

    public MethodBuilder invokeinterface(String owner, String name, String desc) {
        this.methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, owner, name, desc);
        return this;
    }

    public MethodBuilder invokedynamic(String name, String desc, Handle bsm, Object... bsmArgs) {
        this.methodVisitor.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
        return this;
    }

    public MethodBuilder new_(String type) {
        this.methodVisitor.visitTypeInsn(Opcodes.NEW, type);
        return this;
    }

    public MethodBuilder newarray(int operand) {
        this.methodVisitor.visitIntInsn(Opcodes.NEWARRAY, operand);
        return this;
    }

    public MethodBuilder anewarray(String type) {
        this.methodVisitor.visitTypeInsn(Opcodes.ANEWARRAY, type);
        return this;
    }

    public MethodBuilder arraylength() {
        this.methodVisitor.visitInsn(Opcodes.ARRAYLENGTH);
        return this;
    }

    public MethodBuilder athrow() {
        this.methodVisitor.visitInsn(Opcodes.ATHROW);
        return this;
    }

    public MethodBuilder checkcast(String type) {
        this.methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, type);
        return this;
    }

    public MethodBuilder instanceof_(String type) {
        this.methodVisitor.visitTypeInsn(Opcodes.INSTANCEOF, type);
        return this;
    }

    public MethodBuilder monitorenter() {
        this.methodVisitor.visitInsn(Opcodes.MONITORENTER);
        return this;
    }

    public MethodBuilder monitorexit() {
        this.methodVisitor.visitInsn(Opcodes.MONITOREXIT);
        return this;
    }

    // WIDE = 196; // NOT VISITED

    public MethodBuilder multianewarray(String desc, int dims) {
        this.methodVisitor.visitMultiANewArrayInsn(desc, dims);
        return this;
    }

    public MethodBuilder ifnull(Label label) {
        this.methodVisitor.visitJumpInsn(Opcodes.IFNULL, label);
        return this;
    }

    public MethodBuilder ifnonnull(Label label) {
        this.methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label);
        return this;
    }

    public MethodBuilder goto_w(Label label) {
        this.methodVisitor.visitJumpInsn(Opcodes.GOTO, label);
        return this;
    }

    public MethodBuilder jsr_w(Label label) {
        this.methodVisitor.visitJumpInsn(Opcodes.JSR, label);
        return this;
    }

    public MethodBuilder label(Label l) {
        this.methodVisitor.visitLabel(l);
        return this;
    }

    public MethodBuilder tryCatchBlock(Label startLabel, Label endLabel, Label handlerLabel, String exType) {
        this.methodVisitor.visitTryCatchBlock(startLabel, endLabel, handlerLabel, exType);
        return this;
    }

    public Label newLabel() {
        Label ret = new Label();
        this.methodVisitor.visitLabel(ret);
        return ret;
    }
}
