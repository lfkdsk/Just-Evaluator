package com.lfkdsk.justel.ast.function;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.compile.compiler.JustCompiler;
import com.lfkdsk.justel.compile.compiler.JustCompilerImpl;
import com.lfkdsk.justel.compile.generate.Generator;
import com.lfkdsk.justel.compile.generate.JavaCodeGenerator;
import com.lfkdsk.justel.compile.generate.JavaSource;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.eval.Expression;
import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.parser.JustParserImpl;
import com.lfkdsk.justel.utils.NumberUtils;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

import static com.lfkdsk.justel.compile.compiler.CornerTest.compiler;
import static com.lfkdsk.justel.parser.BnfCom.Operators.LEFT;
import static com.lfkdsk.justel.utils.TypeUtils.isNumber;

/**
 * Created by liufengkai on 2017/8/11.
 */
class OperatorExprTest {

    public static class NewOperator extends OperatorExpr {

        public NewOperator(List<AstNode> children) {
            super(children, 1000);
        }


        @Override
        public Object eval(JustContext env) {
            Object left = leftChild().eval(env);
            Object right = rightChild().eval(env);

            if (isNumber(left) && isNumber(right)) {
                return NumberUtils.computePlusValue(
                        (Number) NumberUtils.computePlusValue((Number) left, ((Number) right)),
                        (Number) right);
            }

            return super.eval(env);
        }

        @Override
        public String compile(JustContext env) {
            StringBuilder builder = new StringBuilder();
            String left = leftChild().compile(env);
            String right = rightChild().compile(env);
            builder.append("(")
                   .append(left)
                   .append("+")
                   .append(right)
                   .append("+")
                   .append(right)
                   .append(")");

            return builder.toString();
        }

        @Override
        public String funcName() {
            return "**";
        }
    }

    private String insertRunExpr(String expr, JustContext context) {
        JustParserImpl parser = new JustParserImpl();
        parser.insertOperators("**", 2, LEFT, NewOperator.class);

        Logger.init("test parser");
        JustLexerImpl lexer = new JustLexerImpl(new StringReader(expr));
        lexer.insertSymbol("**");
        String returnString = "";
        while (lexer.hasMore()) {
            AstNode node = parser.parser(lexer);

            Logger.v(" => " + node.toString() + "  ");
            returnString = node.eval(context).toString();
            Logger.v(" => " + returnString + "  ");
        }

        return returnString;
    }

    private String insertCompiler(String exprStr, JustContext context) {
        Logger.init("gen-code");
        JustLexerImpl lexer = new JustLexerImpl(new StringReader(exprStr));
        lexer.insertSymbol("**");
        JustParserImpl parser = new JustParserImpl();
        parser.insertOperators("**", 2, LEFT, NewOperator.class);

        AstNode rootNode = null;
        while (lexer.hasMore()) {
            rootNode = parser.parser(lexer);
        }
        Generator generator = new JavaCodeGenerator(context, rootNode);
        JustCompiler compiler = new JustCompilerImpl();
        JavaSource javaSource = generator.generate();
        Expression expr = compiler.compile(javaSource);

        String result = expr.eval(context).toString();
        Logger.v(result);

        return result;
    }

    @Test
    void insertOperatorEval() {
        insertRunExpr("1111 ** 121", null);
    }

    @Test
    void insertCompiler() {
        insertCompiler("1111 ** 121", null);
    }

    @Test
    void insertCompiler1() {
        JustContext context = new JustMapContext();
        context.put("f", 1111);
        compiler("1111 * 22222.0 + f", context);
    }

    @Test
    void testChar() {
        Logger.init();
        Logger.i(Character.isLetterOrDigit('=') + " ");
    }

    @Test
    void testToString() {
        Logger.init("toString");
        String[] args = {
                "123 + 222",
                "123 * 222",
                "123 - 111",
                "123 / 222",
                "lfkdsk[lfk]",
                "!lfkdsk",
                "lfkdsk ? 1 : 0",
                "lfkdsk == 1",
                "lfkdsk || 1"
        };

        for (String arg : args) {
            JustLexerImpl lexer = new JustLexerImpl(new StringReader(arg));
            JustParserImpl parser = new JustParserImpl();
            Logger.v(parser.parser(lexer).toString());
        }
    }



}