package com.lfkdsk.justel.utils.asm;

import org.objectweb.asm.*;
import org.objectweb.asm.util.CheckClassAdapter;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ClassBuilder {

    private ClassWriter clsWriter;

    private TraceClassVisitor traceClassVisitor;

    private ClassVisitor checkClassVisitor;

    private List<MethodDefine> methodDefines;

    private List<FieldVisitor> fieldVisitors;

    /**
     * Start a new class file building process...
     *
     * @param version
     * @param access
     * @param name
     * @param signature
     * @param superName
     * @param interfaces
     * @param flags
     */
    public ClassBuilder(final int version,
                        final int access,
                        final String name,
                        final String signature,
                        final String superName,
                        final String[] interfaces,
                        final int flags) {
        this(version, access, name, signature, superName, interfaces, flags, false);
    }

    public ClassBuilder(final int version,
                        final int access,
                        final String name,
                        final String signature,
                        final String superName,
                        final String[] interfaces,
                        final int flags, boolean trace) {
        this.clsWriter = new ClassWriter(flags);
        if (trace) {
            this.traceClassVisitor = new TraceClassVisitor(clsWriter, new PrintWriter(System.out));
            this.checkClassVisitor = new CheckClassAdapter(traceClassVisitor, false);
        } else {
            this.checkClassVisitor = new CheckClassAdapter(clsWriter, false);
        }
        this.methodDefines = new ArrayList<>();
        this.fieldVisitors = new ArrayList<>();
        // 'acc_super' added by default
        this.checkClassVisitor.visit(version, access | Opcodes.ACC_SUPER, name, signature, superName, interfaces);
    }


    public FieldBuilder newField(final int access, final String name, final String desc, final String signature, final Object value) {
        FieldVisitor fv = this.checkClassVisitor.visitField(access, name, desc, signature, value);
        this.fieldVisitors.add(fv);
        return new FieldBuilder(fv);
    }

    public MethodBuilder newMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions,
                                   int maxStack, int maxLocals) {
        MethodVisitor mv = this.checkClassVisitor.visitMethod(access, name, desc, signature, exceptions);
        mv.visitCode();
        this.methodDefines.add(new MethodDefine(mv, maxStack, maxLocals));
        return new MethodBuilder(mv);
    }

    public byte[] toByteArray() {
        for (FieldVisitor fv : this.fieldVisitors)
            fv.visitEnd();

        for (MethodDefine md : this.methodDefines) {
            MethodVisitor mv = md.getMethodVisitor();
            // visit maxs should be at last, so the maxs could be computed
            // correctly
            mv.visitMaxs(md.getMaxStack(), md.getMaxLocals());
            mv.visitEnd();
        }

        this.checkClassVisitor.visitEnd();
        return this.clsWriter.toByteArray();
    }
}
