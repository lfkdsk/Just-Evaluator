package com.lfkdsk.justel.parser;

import com.lfkdsk.justel.ast.base.AstLeaf;
import com.lfkdsk.justel.ast.base.AstList;
import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.ast.function.Operator;
import com.lfkdsk.justel.exception.ParseException;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.token.Token;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * BnfCom Engine
 *
 * @author liufengkai
 *         Created by liufengkai on 16/7/11.
 * @see AToken
 * @see Expr
 * @see Element
 * @see Tree
 */
public class BnfCom {

    protected static abstract class Element {
        /**
         * Grammar Analyze
         *
         * @param lexer Lexer
         * @param nodes Ast-List
         * @throws ParseException
         */
        protected abstract void parse(Lexer lexer, List<AstNode> nodes)
                throws ParseException;

        /**
         * Match Elements
         *
         * @param lexer Lexer
         * @return tof?
         * @throws ParseException
         */
        protected abstract boolean match(Lexer lexer) throws ParseException;
    }

    /**
     * Create Basic Tree
     */
    protected static class Tree extends Element {
        private BnfCom parser;

        Tree(BnfCom parser) {
            this.parser = parser;
        }

        @Override
        protected void parse(Lexer lexer, List<AstNode> nodes) throws ParseException {
            nodes.add(parser.parse(lexer));
        }

        @Override
        protected boolean match(Lexer lexer) throws ParseException {
            return parser.match(lexer);
        }
    }

    /**
     * Or Tree
     * [] | []
     */
    protected static class OrTree extends Element {
        private BnfCom[] parsers;

        OrTree(BnfCom[] parsers) {
            this.parsers = parsers;
        }

        @Override
        protected void parse(Lexer lexer, List<AstNode> nodes) throws ParseException {
            BnfCom parser = choose(lexer);
            if (parser == null) {
                throw new ParseException(lexer.peek(0));
            } else {
                nodes.add(parser.parse(lexer));
            }
        }

        @Override
        protected boolean match(Lexer lexer) throws ParseException {
            return choose(lexer) != null;
        }

        private BnfCom choose(Lexer lexer) throws ParseException {
            for (BnfCom parser : parsers) {
                if (parser.match(lexer)) {
                    return parser;
                }
            }
            return null;
        }

        /**
         * insert to zero node.
         *
         * @param parser BNF
         */
        private void insert(BnfCom parser) {
            BnfCom[] newParsers = new BnfCom[parsers.length + 1];
            newParsers[0] = parser;
            System.arraycopy(parsers, 0, newParsers, 1, parsers.length);
            parsers = newParsers;
        }
    }

    /**
     * Repeat Node
     */
    protected static class Repeat extends Element {
        protected BnfCom parser;

        protected boolean onlyOne;

        /**
         * @param parser  BNF
         * @param onlyOne onlyOne?
         */
        public Repeat(BnfCom parser, boolean onlyOne) {
            this.parser = parser;
            this.onlyOne = onlyOne;
        }

        @Override
        protected void parse(Lexer lexer, List<AstNode> nodes) throws ParseException {
            while (parser.match(lexer)) {

                AstNode node = parser.parse(lexer);
                // leaf or list
                if (node.getClass() != AstList.class || node.childCount() > 0) {
                    nodes.add(node);
                }

                if (onlyOne) {
                    break;
                }
            }
        }

        @Override
        protected boolean match(Lexer lexer) throws ParseException {
            return parser.match(lexer);
        }
    }

    /**
     * Token Basic
     */
    protected static abstract class AToken extends Element {

        protected Factory factory;

        public AToken(Class<? extends AstLeaf> clazz) {
            if (clazz == null) {
                clazz = AstLeaf.class;
            }

            factory = Factory.get(clazz, Token.class);
        }

        @Override
        protected boolean match(Lexer lexer) throws ParseException {
            return tokenTest(lexer.peek(0));
        }

        @Override
        protected void parse(Lexer lexer, List<AstNode> nodes) throws ParseException {
            Token token = lexer.read();

            if (tokenTest(token)) {
                AstNode leaf = factory.make(token);

                nodes.add(leaf);
            } else {
                throw new ParseException(token);
            }
        }

        /**
         * Token could pass test?
         *
         * @param token com.lfkdsk.justel.token
         * @return tof?
         */
        protected abstract boolean tokenTest(Token token);
    }

    /**
     * ID Token
     */
    protected static class IdToken extends AToken {
        Set<String> reserved;

        public IdToken(Class<? extends AstLeaf> clazz, Set<String> reserved) {
            super(clazz);
            this.reserved = reserved != null ? reserved : new HashSet<>();
        }

        @Override
        protected boolean tokenTest(Token token) {
            return token.isIdentifier() && !reserved.contains(token.getText());
        }
    }

    /**
     * Number Token
     */
    protected static class NumToken extends AToken {

        public NumToken(Class<? extends AstLeaf> clazz) {
            super(clazz);
        }

        @Override
        protected boolean tokenTest(Token token) {
            return token.isNumber();
        }
    }

    /**
     * String Token
     */
    protected static class StrToken extends AToken {

        public StrToken(Class<? extends AstLeaf> clazz) {
            super(clazz);
        }

        @Override
        protected boolean tokenTest(Token token) {
            return token.isString();
        }
    }

    protected static class BoolToken extends AToken {

        public BoolToken(Class<? extends AstLeaf> clazz) {
            super(clazz);
        }

        @Override
        protected boolean tokenTest(Token token) {
            return token.isBool();
        }
    }

    protected static class NullToken extends AToken {

        public NullToken(Class<? extends AstLeaf> clazz) {
            super(clazz);
        }

        @Override
        protected boolean tokenTest(Token token) {
            return token.isNull();
        }
    }

    protected static class TypeToken extends AToken {

        public TypeToken(Class<? extends AstLeaf> clazz) {
            super(clazz);
        }

        @Override
        protected boolean tokenTest(Token token) {
            return token.isType();
        }
    }

    /**
     * Leaf Node.
     */
    protected static class Leaf extends Element {
        protected String[] tokens;

        protected Leaf(String[] pat) {
            this.tokens = pat;
        }

        @Override
        protected void parse(Lexer lexer, List<AstNode> nodes) throws ParseException {
            Token token = lexer.read();

            if (token.isIdentifier()) {
                for (String t : tokens) {
                    if (t.equals(token.getText())) {
                        find(nodes, token);
                        return;
                    }
                }
            }

            if (tokens.length > 0) {
                throw new ParseException(tokens[0] + " expected. ", token);
            } else {
                throw new ParseException(token);
            }
        }

        /**
         * add final node
         *
         * @param list  list
         * @param token Token
         */
        protected void find(List<AstNode> list, Token token) {
            list.add(new AstLeaf(token));
        }

        @Override
        protected boolean match(Lexer lexer) throws ParseException {
            Token token = lexer.peek(0);

            if (token.isIdentifier()) {
                for (String t : tokens) {
                    if (t.equals(token.getText())) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    protected static class Skip extends Leaf {

        protected Skip(String[] pat) {
            super(pat);
        }

        /**
         * Skip Node
         *
         * @param list  list
         * @param token Token
         */
        @Override
        protected void find(List<AstNode> list, Token token) {

        }
    }

    protected static class Precedence {
        int value;
        boolean leftAssoc;
        Class<? extends AstNode> clazz;
        Factory factory;

        public Precedence(int value, boolean leftAssoc,
                          Class<? extends AstNode> clazz) {
            this.value = value;
            this.leftAssoc = leftAssoc;
            this.clazz = clazz;
            this.factory = Factory.getForAstList(clazz);
        }
    }

    /**
     * Operator Node
     */
    public static class Operators extends HashMap<String, Precedence> {
        // 结合性
        public static boolean LEFT = true;

        public static boolean RIGHT = false;

        /**
         * add Operators
         *
         * @param name      Token String
         * @param pres      Priority
         * @param leftAssoc left or right
         * @param clazz     create node class file
         */
        public void add(String name, int pres,
                        boolean leftAssoc, Class<? extends AstNode> clazz) {
            put(name, new Precedence(pres, leftAssoc, clazz));
        }
    }

    /**
     * Expr Tree
     */
    protected static class Expr extends Element {
        Factory factory;

        Operators ops;

        BnfCom factor;

        Expr(Class<? extends AstNode> clazz, BnfCom factor, Operators ops) {

            this.factory = Factory.getForAstList(clazz);
            this.factor = factor;
            this.ops = ops;
        }

        @Override
        protected void parse(Lexer lexer, List<AstNode> nodes) throws ParseException {
            AstNode right = factor.parse(lexer);

            Precedence prec;

            while ((prec = nextOperator(lexer)) != null) {
                right = doShift(lexer, right, prec.value);
            }

            nodes.add(right);
        }

        private AstNode doShift(Lexer lexer, AstNode left, int prec) throws ParseException {
            ArrayList<AstNode> list = new ArrayList<>();

            list.add(left);
            // 读取一个符号
            list.add(new Operator(lexer.read()));
//            Token operatorToken = lexer.read();
            // 返回节点放在右子树
            AstNode right = factor.parse(lexer);
//            AstNode right = factor.parse(lexer);

//            Precedence operatorPrecedence = ops.get(operatorToken.getText());
//
//            if (operatorPrecedence != null) {
//
//                // operatorExpr local operator list
//                //      |
//                //    / | \
//                // left op right
//                List<AstNode> operatorList = new ArrayList<>();
//                operatorList.add(left);
//                operatorList.add(new AstLeaf(operatorToken));
//                operatorList.add(right);
//
//                // make operatorExpr node
//                right = operatorPrecedence.factory.make(operatorList);
//            } else {
//                throw new ParseException("UnSupport Operators : " + operatorToken.getText());
//            }

            Precedence next;
            // 子树向右拓展
            while ((next = nextOperator(lexer)) != null && rightIsExpr(prec, next)) {
                right = doShift(lexer, right, next.value);
            }

            list.add(right);

            return factory.make(list);
        }

        /**
         * get next operator
         *
         * @param lexer Lexer
         * @return Symbol Operator
         * @throws ParseException
         */
        private Precedence nextOperator(Lexer lexer) throws ParseException {
            Token token = lexer.peek(0);

            if (token.isIdentifier()) {
                // get symbol
                return ops.get(token.getText());
            } else {
                return null;
            }
        }

        /**
         * compare left's priority and right's priority
         *
         * @param prec     priority
         * @param nextPrec next symbol
         * @return tof?
         */
        private static boolean rightIsExpr(int prec, Precedence nextPrec) {
            if (nextPrec.leftAssoc) {
                return prec > nextPrec.value;
            } else {
                return prec >= nextPrec.value;
            }
        }

        @Override
        protected boolean match(Lexer lexer) throws ParseException {
            return factor.match(lexer);
        }
    }

    /**
     * default factory function name
     */
    private static final String factoryName = "create";

    protected abstract static class Factory {

        protected abstract AstNode make0(Object arg) throws Exception;

        protected AstNode make(Object arg) {
            try {
                return make0(arg);
            } catch (IllegalArgumentException e1) {
                throw e1;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * create ast list
         *
         * @param clazz class file
         * @return reflect factory
         */

        private static Factory getForAstList(Class<? extends AstNode> clazz) {
            Factory f = get(clazz, List.class);

            if (f == null) {
                f = new Factory() {
                    @Override
                    protected AstNode make0(Object arg) throws Exception {
                        List<AstNode> results = (List<AstNode>) arg;
                        // 节点折叠
                        if (results.size() == 1) {
                            return results.get(0);
                        } else {
                            return new AstList(results, Token.LIST);
                        }
                    }
                };
            }

            return f;
        }

        /**
         * 静态构建工厂类
         *
         * @param clazz   类
         * @param argType 参数 也是一个类
         * @return 工厂
         */
        protected static Factory get(Class<? extends AstNode> clazz,
                                     Class<?> argType) {
            if (clazz == null) {
                return null;
            }

            // call create function
            try {
                final Method m = clazz.getMethod(factoryName, new Class<?>[]{argType});

                return new Factory() {
                    @Override
                    protected AstNode make0(Object arg) throws Exception {
                        return (AstNode) m.invoke(null, arg);
                    }
                };
            } catch (NoSuchMethodException e) {

            }

            // call constructor
            try {
                final Constructor<? extends AstNode> c = clazz.getConstructor(argType);

                return new Factory() {
                    @Override
                    protected AstNode make0(Object arg) throws Exception {
                        return c.newInstance(arg);
                    }
                };
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * elements set
     */
    private List<Element> elements;

    /**
     * reflect factory
     */
    private Factory factory;

    private BnfCom(Class<? extends AstNode> clazz) {
        reset(clazz);
    }

    private BnfCom(BnfCom parser) {
        elements = parser.elements;
        factory = parser.factory;
    }

    /**
     * parser
     *
     * @param lexer lexer => node
     * @return AstNode
     * @throws ParseException
     */
    public AstNode parse(Lexer lexer) throws ParseException {
        ArrayList<AstNode> results = new ArrayList<>();
        for (Element e : elements) {
            e.parse(lexer, results);
        }
        return factory.make(results);
    }

    private boolean match(Lexer lexer) throws ParseException {
        if (elements.size() == 0) {
            return true;
        } else {
            Element e = elements.get(0);
            return e.match(lexer);
        }
    }

    /**
     * reset => expr
     *
     * @return Ast
     */
    public static BnfCom rule() {
        return rule(null);
    }

    /**
     * reset => expr
     *
     * @param clazz class file
     * @return Ast
     */
    public static BnfCom rule(Class<? extends AstNode> clazz) {
        return new BnfCom(clazz);
    }

    public BnfCom reset() {
        elements = new ArrayList<>();
        return this;
    }

    public BnfCom reset(Class<? extends AstNode> clazz) {
        elements = new ArrayList<>();
        factory = Factory.getForAstList(clazz);
        return this;
    }

    ///////////////////////////////////////////////////////////////////////////
    // add token combinators
    ///////////////////////////////////////////////////////////////////////////

    public BnfCom number() {
        return number(null);
    }

    public BnfCom number(Class<? extends AstLeaf> clazz) {
        elements.add(new NumToken(clazz));
        return this;
    }

    public BnfCom identifier(Set<String> reserved) {
        return identifier(null, reserved);
    }

    public BnfCom identifier(Class<? extends AstLeaf> clazz,
                             Set<String> reserved) {
        elements.add(new IdToken(clazz, reserved));
        return this;
    }

    public BnfCom string() {
        return string(null);
    }

    public BnfCom string(Class<? extends AstLeaf> clazz) {
        elements.add(new StrToken(clazz));
        return this;
    }

    public BnfCom bool() {
        return bool(null);
    }

    public BnfCom bool(Class<? extends AstLeaf> clazz) {
        elements.add(new BoolToken(clazz));
        return this;
    }

    public BnfCom Null() {
        return Null(null);
    }

    public BnfCom Null(Class<? extends AstLeaf> clazz) {
        elements.add(new NullToken(clazz));
        return this;
    }

    public BnfCom type() {
        return type(null);
    }

    public BnfCom type(Class<? extends AstLeaf> clazz) {
        elements.add(new TypeToken(clazz));
        return this;
    }

    /**
     * add final token
     */
    public BnfCom token(String... pat) {
        elements.add(new Leaf(pat));
        return this;
    }

    /**
     * insert skip symbol
     *
     * @param pat str
     * @return bnf
     */
    public BnfCom sep(String... pat) {
        elements.add(new Skip(pat));
        return this;
    }

    /**
     * insert sub tree
     *
     * @param parser BNF
     * @return BNF
     */
    public BnfCom ast(BnfCom parser) {
        elements.add(new Tree(parser));
        return this;
    }

    /**
     * insert sub-multi tree
     *
     * @param parsers BNF
     * @return BNF
     */
    public BnfCom or(BnfCom... parsers) {
        elements.add(new OrTree(parsers));
        return this;
    }

    public BnfCom maybe(BnfCom parser) {
        BnfCom parser1 = new BnfCom(parser);

        parser1.reset();

        elements.add(new OrTree(new BnfCom[]{parser, parser1}));
        return this;
    }

    /**
     * onlyOne 只重复一次
     *
     * @param parser BNF
     * @return BNF
     */
    public BnfCom option(BnfCom parser) {
        elements.add(new Repeat(parser, true));
        return this;
    }

    /**
     * 重复多次的节点
     *
     * @param parser BNF
     * @return BNF
     */
    public BnfCom repeat(BnfCom parser) {
        elements.add(new Repeat(parser, false));
        return this;
    }

    public BnfCom expression(BnfCom subExp, Operators operators) {
        elements.add(new Expr(null, subExp, operators));
        return this;
    }

    public BnfCom expression(Class<? extends AstNode> clazz, BnfCom subExp,
                             Operators operators) {
        elements.add(new Expr(clazz, subExp, operators));
        return this;
    }

    public BnfCom insertChoice(BnfCom parser) {
        Element e = elements.get(0);
        if (e instanceof OrTree) {
            ((OrTree) e).insert(parser);
        } else {
            BnfCom otherWise = new BnfCom(this);
            reset(null);
            or(parser, otherWise);
        }
        return this;
    }
}
