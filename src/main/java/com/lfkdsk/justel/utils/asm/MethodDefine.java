package com.lfkdsk.justel.utils.asm;

import org.objectweb.asm.MethodVisitor;

/**
 *
 */
public class MethodDefine {
    private MethodVisitor methodVisitor;
    private int maxStack;
    private int maxLocals;

    public MethodDefine(MethodVisitor mv, int maxStack, int maxLocals) {
        this.methodVisitor = mv;
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
    }

    public MethodVisitor getMethodVisitor() {
        return this.methodVisitor;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

}
