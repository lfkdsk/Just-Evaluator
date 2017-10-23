package com.lfkdsk.justel.generate.bytecodegen;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.generate.Generator;
import com.lfkdsk.justel.generate.javagen.JavaSource;
import com.lfkdsk.justel.generate.javagen.Var;
import com.lfkdsk.justel.utils.GeneratedId;
import com.lfkdsk.justel.utils.asm.ClassBuilder;
import com.lfkdsk.justel.utils.asm.MethodBuilder;
import org.objectweb.asm.ClassWriter;

import java.util.Collection;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.V1_7;


public class ByteCodeGenerator extends Generator {

    private final int generateClassId = GeneratedId.generateAtomId();

    private final String className = "com/lfkdsk/justel/generatecode/JustEL" + generateClassId;
    private final String classInterface = "com/lfkdsk/justel/eval/Expression";
    private final String contextDesc = "com/lfkdsk/justel/context/JustContext";
    private final String methodDesc = "(Lcom/lfkdsk/justel/context/JustContext;)Ljava/lang/Object;";

    /**
     * com/lfkdsk/justel/context/JustContext.get:
     */
    private final String contextGetDesc = "(Ljava/lang/String;)Ljava/lang/Object;";

    public ByteCodeGenerator(JustContext context, AstNode rootNode) {
        super(context, rootNode);
    }

    @Override
    public JavaSource generate() {
        ClassBuilder builder = new ClassBuilder(
                V1_7,
                ACC_PUBLIC,
                className,
                null,
                "java/lang/Object",
                new String[]{classInterface},
                ClassWriter.COMPUTE_FRAMES);


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

        Collection<String> keySet = context.varsKeySet();
        for (String key : keySet) {
            Var var = new Var(key, context.get(key));

            String type = var.getType().getCanonicalName().replace('.', '/');
            eval.invokeinterface(contextDesc, "get", contextGetDesc);
            eval.checkcast(type);
            eval.invokevirtual(type,"","()");
        }


//        eval.invokevirtual();

        return null;
    }
}
