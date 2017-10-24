package com.lfkdsk.justel.generate.bytecodegen;


import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.eval.Expression;
import com.lfkdsk.justel.generate.javagen.Var;
import com.lfkdsk.justel.utils.GeneratedId;
import com.lfkdsk.justel.utils.ReflectUtils;
import com.lfkdsk.justel.utils.asm.ClassBuilder;
import com.lfkdsk.justel.utils.asm.MethodBuilder;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

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
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(writer, new PrintWriter(System.out));
        traceClassVisitor.visit(Opcodes.V1_7, ACC_PUBLIC, "lfkdsk/HelloWorld", null, "java/lang/Object",
                null);
        MethodVisitor visitor = traceClassVisitor.visitMethod(
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
        traceClassVisitor.visitEnd();

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
        int id = GeneratedId.generateAtomId();
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
                0, 0);

        eval.ldc("alibaba-inc");
        eval.areturn();

        Class<?> cls = new MyClassLoader().defineClass("com.lfkdsk.justel.generatecode.JustEL" + id, builder.toByteArray());
        Expression expr = (Expression) cls.newInstance();
        System.out.println(expr.eval(new JustMapContext()));
    }

    @Test
    void testTypeConvert() throws IllegalAccessException, InstantiationException {
        Logger.init();

        final int generateClassId = GeneratedId.generateAtomId();

        final String className = "com/lfkdsk/justel/generatecode/JustEL" + generateClassId;
        final String classInterface = "com/lfkdsk/justel/eval/Expression";
        final String contextDesc = "com/lfkdsk/justel/context/JustContext";
        final String methodDesc = "(Lcom/lfkdsk/justel/context/JustContext;)Ljava/lang/Object;";
        final String contextGetDesc = "(Ljava/lang/String;)Ljava/lang/Object;";

        ClassBuilder builder = new ClassBuilder(
                V1_7,
                ACC_PUBLIC,
                className,
                null,
                Type.getInternalName(Object.class),
                new String[]{classInterface},
                ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS, true);

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
                methodDesc,
                null,
                null,
                0, 0
        );

        // load JustContext
        eval.aload_1();

        JustContext context = new JustMapContext();
        context.put("lfkdsk", 1111111);

        Collection<String> keySet = context.varsKeySet();
        for (String key : keySet) {
            Var var = new Var(key, context.get(key));
            String type = Type.getInternalName(var.getType());

            // ldc [key : value-name]
            eval.ldc(key);
            // [type] [name] = (check-cast) context.get("[key]");
            eval.invokeinterface(contextDesc, "get", contextGetDesc);
            // (check-cast)
            eval.checkcast(type);
            // just for primary type
            boolean isPrimaryType = ReflectUtils.isPrimitiveOrWrapNumber(var.getType());
            if (isPrimaryType) {
                // check to primary type
                // Integer/Double/Long/Float . #Value() => int/double/long/float
                eval.invokevirtual(type,
                        ReflectUtils.toPrimitiveClass(var.getType()).getSimpleName() + "Value",
                        Type.getMethodDescriptor(Type.getType(int.class)));
            }
            eval.istore_2();
        }

        eval.iload_2();
        eval.invokestatic("java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        eval.areturn();
//        eval.ldc("lfkdsk");
//        eval.areturn();

        Class<?> cls = new MyClassLoader().defineClass(Type.getType(className).getClassName(), builder.toByteArray());
        Expression expr = (Expression) cls.newInstance();
        System.out.println(expr.eval(context));
    }
}