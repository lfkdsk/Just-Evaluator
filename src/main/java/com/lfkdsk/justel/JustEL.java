package com.lfkdsk.justel;

import com.lfkdsk.justel.ast.tree.AstProgram;
import com.lfkdsk.justel.compile.compiler.JustCompiler;
import com.lfkdsk.justel.compile.compiler.JustCompilerImpl;
import com.lfkdsk.justel.compile.generate.Generator;
import com.lfkdsk.justel.compile.generate.JavaCodeGenerator;
import com.lfkdsk.justel.compile.generate.JavaSource;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.eval.ExprBinder;
import com.lfkdsk.justel.eval.Expression;
import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.parser.JustParser;
import com.lfkdsk.justel.parser.JustParserImpl;
import com.lfkdsk.justel.utils.ObjectHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Just EL Wrapper
 *
 * @author liufengkai
 * Created by liufengkai on 2017/8/22.
 */
public final class JustEL {

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
        return (AstProgram) parser.parser(lexer.scanner(expr));
    }

    /**
     * Eval Value
     *
     * @param expr Expr String
     * @param env  context
     * @return Eval Value
     */
    public Object eval(@NotNull String expr, @Nullable JustContext env) {
        ObjectHelper.requireNonNull(expr, "expr is null");

        return expr(expr)
                .eval(env);
    }

    /**
     * Ast to Expression
     *
     * @param expr string-expr
     * @return Expression Node
     */
    public Expression expr(@NotNull String expr) {
        ObjectHelper.requireNonNull(expr, "expr is null");

        return parse(expr)
                .program()
                .expr();
    }

    /**
     * Compile To Expression
     *
     * @param expr eval expr
     * @param env  context
     * @return Expression Bean
     */
    public Expression compile(@NotNull String expr, @Nullable JustContext env) {
        ObjectHelper.requireNonNull(expr, "expr is null");

        final AstProgram rootNode = ObjectHelper.requireNonNull(parse(expr), "rootNode is null => parser error");

        // const value
        if (rootNode.isProgramConst()) {
            return rootNode.getConstExpression(env);
        }

        // generate java source
        final JavaSource javaSource = generator.generate(env, rootNode);

        return compiler.compile(javaSource);
    }

    /**
     * Expr Stream
     *
     * @param expr     expression string
     * @param contexts context
     * @return expression - stream
     */
    public Stream<Expression> expr(@NotNull final String expr, @NotNull final JustContext contexts) {
        return Stream.of(expr)
                     .map(lexer::scanner)
                     .map(parser::parser)
                     .map(astNode -> generator.generate(contexts, astNode))
                     .map(compiler::compile);
    }

    /**
     * Expr Stream
     *
     * @param binder binder
     * @return expression - stream
     */
    public Stream<Expression> expr(@NotNull final ExprBinder binder) {
        return expr(binder.getExpr(), binder.getContexts());
    }

    /**
     * Expr Stream
     *
     * @param binders binder
     * @return expression - stream
     */
    public Stream<Expression> exprs(@NotNull final ExprBinder... binders) {
        return Arrays.stream(binders).flatMap(this::expr);
    }

    /**
     * run eval with default params
     *
     * @param expr expr-string
     * @param env  context
     * @return Value
     */
    public static Object runEval(@NotNull String expr, @Nullable JustContext env) {

        return runExpr(expr)
                .eval(env);
    }

    /**
     * Return expression
     *
     * @param expr expr-string
     * @return Value
     */
    public static Expression runExpr(@NotNull String expr) {
        return (defaultEL == null
                ? defaultEL = new Builder().create()
                : defaultEL)
                .expr(expr);
    }

    /**
     * run compile with default params
     *
     * @param expr expr-string
     * @param env  context
     * @return Value
     */
    public static Expression runCompile(@NotNull String expr, @Nullable JustContext env) {
        return (defaultEL == null
                ? defaultEL = new Builder().create()
                : defaultEL)
                .compile(expr, env);
    }

    /**
     * Builder
     *
     * @return new just-el builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Just EL Builder
     *
     * @see Lexer
     * @see JustParser
     * @see JustCompiler
     * @see Generator
     */
    public final static class Builder {

        /**
         * just-parse
         */
        private JustParser parser = new JustParserImpl();

        /**
         * just-lexer
         */
        private Lexer lexer = new JustLexerImpl();

        /**
         * just-compiler
         */
        private JustCompiler compiler = new JustCompilerImpl();

        /**
         * code-generator
         */
        private Generator generator = new JavaCodeGenerator();


        public Builder lexer(@NotNull Lexer lexer) {
            this.lexer = ObjectHelper.requireNonNull(lexer, "lexer is null");

            return this;
        }

        public Builder parser(@NotNull JustParser parser) {
            this.parser = ObjectHelper.requireNonNull(parser, "parser is null");

            return this;
        }

        public Builder compiler(@NotNull JustCompiler justCompiler) {
            this.compiler = ObjectHelper.requireNonNull(justCompiler, "compiler is null");

            return this;
        }

        public Builder generator(@NotNull Generator generator) {
            this.generator = ObjectHelper.requireNonNull(generator, "generator is null");

            return this;
        }

        public JustEL create() {
            return new JustEL(parser, lexer, compiler, generator);
        }
    }
}

