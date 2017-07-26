package com.lfkdsk.justel.parser;

import com.lfkdsk.justel.ast.base.AstLeaf;
import com.lfkdsk.justel.ast.base.AstList;
import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.ast.operators.Operator;
import com.lfkdsk.justel.ast.tree.AstBinaryExpr;
import com.lfkdsk.justel.exception.ParseException;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.token.Token;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * BnfCom 巴克斯范式解析引擎
 *
 * @author liufengkai Created by liufengkai on 16/7/11.
 */
public class BnfCom {

    protected static abstract class Element {
        /**
         * 语法分析
         *
         * @param lexer 语法分析器
         * @param nodes 节点
         * @throws ParseException
         */
        protected abstract void parse(Lexer lexer, List<AstNode> nodes)
                throws ParseException;

        /**
         * 匹配
         *
         * @param lexer 语法分析器
         * @return tof?
         * @throws ParseException
         */
        protected abstract boolean match(Lexer lexer) throws ParseException;
    }

    /**
     * 开一棵子树
     * Tree中并没有对处理细节的描述
     * 只是个构造基类
     */
    protected static class Tree extends Element {
        protected BnfCom parser;

        public Tree(BnfCom parser) {
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
     * BNF 产生式中的 或节点
     * [] | []
     */
    protected static class OrTree extends Element {
        protected BnfCom[] parsers;

        public OrTree(BnfCom[] parsers) {
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

        protected BnfCom choose(Lexer lexer) throws ParseException {
            for (BnfCom parser : parsers) {
                if (parser.match(lexer)) {
                    return parser;
                }
            }
            return null;
        }

        /**
         * 插入节点 插在了0
         *
         * @param parser BNF
         */
        protected void insert(BnfCom parser) {
            BnfCom[] newParsers = new BnfCom[parsers.length + 1];
            newParsers[0] = parser;
            System.arraycopy(parsers, 0, newParsers, 1, parsers.length);
            parsers = newParsers;
        }
    }

    /**
     * 重复出现的语句节点
     * 比如block中会出现多次的simple
     * 还有Option
     */
    protected static class Repeat extends Element {
        protected BnfCom parser;

        protected boolean onlyOne;

        /**
         * @param parser  BNF
         * @param onlyOne 节点出现次数
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
     * Token 基类
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
         * 判断是否符合该类Token
         * 标准的抽象方法
         *
         * @param token com.lfkdsk.justel.token
         * @return tof?
         */
        protected abstract boolean tokenTest(Token token);
    }

    /**
     * ID 类型的Token
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
     * 数字类型Token
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
     * 字符串类型Token
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
     * 叶节点
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
         * 添加终结符
         *
         * @param list  list
         * @param token 终结符对应token
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
         * 所谓Skip 不添加节点
         * 比如一些格式控制符号是不算做节点的
         *
         * @param list  list
         * @param token com.lfkdsk.justel.token
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
     * 标志符
     */
    public static class Operators extends HashMap<String, Precedence> {
        // 结合性
        public static boolean LEFT = true;

        public static boolean RIGHT = false;

        /**
         * 添加保留字
         *
         * @param name      保留字Token
         * @param pres      优先级
         * @param leftAssoc 结合性
         */
        public void add(String name, int pres,
                        boolean leftAssoc, Class<? extends AstNode> clazz) {
            put(name, new Precedence(pres, leftAssoc, clazz));
        }
    }

    /**
     * 表达式子树
     */
    protected static class Expr extends Element {
        Factory factory;

        Operators ops;

        BnfCom factor;

        public Expr(Class<? extends AstNode> clazz, BnfCom factor, Operators ops) {

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
         * 那取下一个符号
         *
         * @param lexer 词法
         * @return 符号
         * @throws ParseException
         */
        private Precedence nextOperator(Lexer lexer) throws ParseException {
            Token token = lexer.peek(0);

            if (token.isIdentifier()) {
                // 从符号表里找对应的符号
                return ops.get(token.getText());
            } else {
                return null;
            }
        }

        /**
         * 比较和右侧符号的结合性
         *
         * @param prec     优先级
         * @param nextPrec 下一个符号的优先级
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
     * 创建方法的方法名
     */
    public static final String factoryName = "create";

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
         * 直接创建一个AstList
         *
         * @param clazz 创建类
         * @return 工厂
         */

        protected static Factory getForAstList(Class<? extends AstNode> clazz) {
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

            // 这是调用了对象的create函数
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

            // 调用对象的构造
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
     * 存储全部的BNF表达式
     */
    protected List<Element> elements;

    /**
     * 构建工厂类
     */
    protected Factory factory;

    public BnfCom(Class<? extends AstNode> clazz) {
        reset(clazz);
    }

    protected BnfCom(BnfCom parser) {
        elements = parser.elements;
        factory = parser.factory;
    }

    /**
     * 分析处理
     *
     * @param lexer 词法分析
     * @return 节点
     * @throws ParseException
     */
    public AstNode parse(Lexer lexer) throws ParseException {
        ArrayList<AstNode> results = new ArrayList<>();
        for (Element e : elements) {
            e.parse(lexer, results);
        }
        return factory.make(results);
    }

    protected boolean match(Lexer lexer) throws ParseException {
        if (elements.size() == 0) {
            return true;
        } else {
            Element e = elements.get(0);
            return e.match(lexer);
        }
    }

    /**
     * 初始化 / 新定义一个一条产生式
     *
     * @return Ast
     */
    public static BnfCom rule() {
        return rule(null);
    }

    /**
     * 初始化 / 新定义一个一条产生式
     *
     * @param clazz 类
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
    // 添加识别各种Token的方法
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
     * 添加非终结符
     */
    public BnfCom token(String... pat) {
        elements.add(new Leaf(pat));
        return this;
    }

    /**
     * 插入符号
     *
     * @param pat 符号
     * @return 这种格式的符号(跳
     */
    public BnfCom sep(String... pat) {
        elements.add(new Skip(pat));
        return this;
    }

    /**
     * 插入一棵子树
     *
     * @param parser BNF
     * @return BNF
     */
    public BnfCom ast(BnfCom parser) {
        elements.add(new Tree(parser));
        return this;
    }

    /**
     * 多个对象传入or树
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

    public static AstNode resetAstExpr(AstBinaryExpr expr, Operators operators) {
        Operator operator = (Operator) expr.midOp();
        Factory factory = operators.get(operator.getText()).factory;
        return factory.make(expr.getChildren());
    }
}
