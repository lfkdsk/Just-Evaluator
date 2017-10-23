package com.lfkdsk.justel.generate.bytecodegen;


import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.eval.Expression;
import com.lfkdsk.justel.utils.GeneratedId;
import com.lfkdsk.justel.utils.asm.ClassBuilder;
import com.lfkdsk.justel.utils.asm.MethodBuilder;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.objectweb.asm.Opcodes.*;


class ByteCodeGeneratorTest {

    static final class MyClassLoader extends ClassLoader {
        public Class defineClass(String name, byte[] b) {
            return defineClass(name, b, 0, b.length);
        }
    }

    @Test
    void testGenerateByteCode() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        writer.visit(Opcodes.V1_7, ACC_PUBLIC, "lfkdsk/HelloWorld", null, "java/lang/Object",
                null);
        MethodVisitor visitor = writer.visitMethod(
                ACC_PUBLIC + ACC_STATIC,
                "main",
                "([Ljava/lang/String;)V",
                null,
                null
        );
        visitor.visitFieldInsn(GETSTATIC,
                "java/lang/System",
                "out",
                "Ljava/io/PrintStream;");

        visitor.visitLdcInsn("Hello World");
        visitor.visitMethodInsn(
                INVOKEVIRTUAL,
                "java/io/PrintStream",
                "println",
                "(Ljava/lang/String;)V"
        );

        visitor.visitInsn(RETURN);
        visitor.visitMaxs(2, 2);
        visitor.visitEnd();
        writer.visitEnd();

        Class<?> cls = new MyClassLoader().defineClass("lfkdsk.HelloWorld", writer.toByteArray());
        Method m = cls.getMethod("main", String[].class);
        m.invoke(null, new Object[]{new String[0]});
    }

    @Test
    void testGenerateUtils() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ClassBuilder builder = new ClassBuilder(V1_7, ACC_PUBLIC, "lfkdsk/HelloWorld", null, "java/lang/Object",
                null, ClassWriter.COMPUTE_FRAMES);
        MethodBuilder c = builder.newMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null, 0, 0);
        c.aload_0();
        c.invokespecial("java/lang/Object", "<init>", "()V");
        c.return_();

        MethodBuilder main = builder.newMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,
                "main", "([Ljava/lang/String;)V",
                null, null, 0, 0);
        main.getstatic("java/lang/System", "out", "Ljava/io/PrintStream;");
        main.ldc("Hello World!");
        main.invokevirtual("java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        main.return_();

        Class<?> cls = new MyClassLoader().defineClass("lfkdsk.HelloWorld", builder.toByteArray());
        Method m = cls.getMethod("main", String[].class);
        m.invoke(null, new Object[]{new String[0]});
    }

    @Test
    void testGenerateExpression() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        int id  = GeneratedId.generateAtomId();
        ClassBuilder builder = new ClassBuilder(
                V1_7,
                ACC_PUBLIC,
                "com/lfkdsk/justel/generatecode/JustEL" + id,
                null,
                "java/lang/Object",
                new String[]{"com/lfkdsk/justel/eval/Expression"},
                ClassWriter.COMPUTE_FRAMES);

        MethodBuilder c = builder.newMethod(
                Opcodes.ACC_PUBLIC,
                "<init>",
                "()V",
                null,
                null,
                0,
                0);

        c.aload_0();
        c.invokespecial("java/lang/Object", "<init>", "()V");
        c.return_();

        MethodBuilder eval = builder.newMethod(
                ACC_PUBLIC,
                "eval",
                "(Lcom/lfkdsk/justel/context/JustContext;)Ljava/lang/Object;",
                null,
                null,
                0,0);

        eval.ldc("alibaba-inc");
        eval.areturn();

        Class<?> cls = new MyClassLoader().defineClass("com.lfkdsk.justel.generatecode.JustEL" + id, builder.toByteArray());
        Expression expr  = (Expression) cls.newInstance();
        System.out.println(expr.eval(new JustMapContext()));
    }
}