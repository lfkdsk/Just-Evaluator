package com.lfkdsk.justel;

import com.lfkdsk.justel.ast.tree.AstProgram;
import com.lfkdsk.justel.compile.compiler.JustCompiler;
import com.lfkdsk.justel.compile.compiler.JustCompilerImpl;
import com.lfkdsk.justel.compile.generate.Generator;
import com.lfkdsk.justel.compile.generate.JavaCodeGenerator;
import com.lfkdsk.justel.compile.generate.JavaSource;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.eval.Expression;
import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.parser.JustParser;
import com.lfkdsk.justel.parser.JustParserImpl;

/**
 * Just EL Wrapper
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/8/22.
 */
public class JustEL {

    /**
     * just-parse
     */
    private final JustParser parser;

    /**
     * just-lexer
     */
    private final Lexer lexer;

    /**
     * just-compiler
     */
    private final JustCompiler compiler;

    /**
     * code-generator
     */
    private final Generator generator;

    /**
     * default EL
     */
    private static JustEL defaultEL;

    private JustEL(JustParser parser, Lexer lexer, JustCompiler compiler, Generator generator) {
        this.parser = parser;
        this.lexer = lexer;
        this.compiler = compiler;
        this.generator = generator;
    }

    private AstProgram parse(String expr) {
        // reset lexer
        lexer.reset(expr);
        // read line
        lexer.hasMore();
        // read root node

        return (AstProgram) parser.parser(lexer);
    }

    /**
     * Eval Value
     *
     * @param expr Expr String
     * @param env  context
     * @return Eval Value
     */
    public Object eval(String expr, JustContext env) {

        return parse(expr)
                .program()
                .eval(env);
    }

    /**
     * Compile To Expression
     *
     * @param expr eval expr
     * @param env  context
     * @return Expression Bean
     */
    public Expression compile(String expr, JustContext env) {
        AstProgram rootNode = parse(expr);

        // const value
        if (rootNode.isProgramConst()) {
            return rootNode.getConstExpression();
        }

        // generate java source
        JavaSource javaSource = generator.reset(env, rootNode).generate();

        return compiler.compile(javaSource);
    }

    /**
     * run eval with default params
     *
     * @param expr expr-string
     * @param env  context
     * @return Value
     */
    public static Object runEval(String expr, JustContext env) {
        return (defaultEL == null
                ? new JustELBuilder().create()
                : defaultEL)
                .eval(expr, env);
    }

    /**
     * run compile with default params
     *
     * @param expr expr-string
     * @param env  context
     * @return Value
     */
    public static Expression runCompile(String expr, JustContext env) {
        return (defaultEL == null
                ? new JustELBuilder().create()
                : defaultEL)
                .compile(expr, env);
    }

    /**
     * Just EL Builder
     *
     * @see Lexer
     * @see JustParser
     * @see JustCompiler
     * @see Generator
     */
    public static class JustELBuilder {
        /**
         * just-parse
         */
        JustParser parser = new JustParserImpl();

        /**
         * just-lexer
         */
        Lexer lexer = new JustLexerImpl();

        /**
         * just-compiler
         */
        JustCompiler compiler = new JustCompilerImpl();

        /**
         * code-generator
         */
        Generator generator = new JavaCodeGenerator();

        public JustELBuilder lexer(Lexer lexer) {
            this.lexer = lexer;

            return this;
        }

        public JustELBuilder parser(JustParser parser) {
            this.parser = parser;

            return this;
        }

        public JustELBuilder compiler(JustCompiler justCompiler) {
            this.compiler = justCompiler;

            return this;
        }

        public JustELBuilder generator(Generator generator) {
            this.generator = generator;

            return this;
        }

        public JustEL create() {
            return new JustEL(parser, lexer, compiler, generator);
        }
    }
}

