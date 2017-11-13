/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.parser;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.exception.ParseException;
import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.Map;

/**
 * Just Parser Impl Test
 *
 * @author liufengkai
 * Created by liufengkai on 2017/7/26.
 * @see JustParserImpl
 */
public class JustParserImplTest {

    static String allTests = "" +
            "        (item.hasTag())\n" +
            "        (item.hasTag())\n" +
            "        (item.hasTag()) && (category.hasFeatureValue())\n" +
            "        (item.hasTag()) && !(seller.isBSeller())\n" +
            "        (seller.isBSeller()) && (item.hasTag())\n" +
            "        (item.hasTag()) && !(seller.isBSeller())\n" +
            "        (item.hasTag()) && (seller.isBSeller())\n" +
            "        (seller.isBSeller()) && (item.hasTag())\n" +
            "        (item.hasTag())\n" +
            "        (buyer.hasTagOn())\n" +
            "        !(seller.isBSeller()) && category.rootCategoryId == (\"50026316\") || category.rootCategoryId == (\"50008141\") || category.rootCategoryId == (\"50016422\") || category.rootCategoryId == (\"50002766\") || category.rootCategoryId == (\"50050359\") || category.rootCategoryId == (\"50020275\") || category.rootCategoryId == (\"124458005\")\n" +
            "        !(seller.isBSeller()) && category.rootCategoryId == (\"29\") || category.rootCategoryId == (\"50017300\") || category.rootCategoryId == (\"50007216\") || category.rootCategoryId == (\"124484008\") || category.rootCategoryId == (\"124354002\") || category.rootCategoryId == (\"50510002\") || category.rootCategoryId == (\"50011699\") || category.rootCategoryId == (\"50012029\") || category.rootCategoryId == (\"50013886\") || category.rootCategoryId == (\"50010728\") || category.rootCategoryId == (\"122684003\")\n" +
            "        !(seller.isBSeller()) && category.rootCategoryId == (\"50016891\") || category.rootCategoryId == (\"50011665\") || category.rootCategoryId == (\"40\") || category.rootCategoryId == (\"99\") || category.rootCategoryId == (\"50008907\") || category.rootCategoryId == (\"50004958\")\n" +
            "        !(seller.isBSeller()) && category.rootCategoryId == (\"50454031\") || category.rootCategoryId == (\"50025707\") || category.rootCategoryId == (\"50011949\")\n" +
            "        !(seller.isBSeller()) && category.rootCategoryId == (\"1801\") || category.rootCategoryId == (\"50010788\") || category.rootCategoryId == (\"50023282\") || category.rootCategoryId == (\"50025705\")\n" +
            "        !(seller.isBSeller()) && category.rootCategoryId == (\"23\") || category.rootCategoryId == (\"33\") || category.rootCategoryId == (\"34\") || category.rootCategoryId == (\"50026800\")\n" +
            "        !(seller.isBSeller()) && category.rootCategoryId == (\"122952001\") || category.rootCategoryId == (\"50016349\") || category.rootCategoryId == (\"50016348\") || category.rootCategoryId == (\"122950001\") || category.rootCategoryId == (\"21\") || category.rootCategoryId == (\"122928002\")\n" +
            "        !(seller.isBSeller()) && category.rootCategoryId == (\"11\") || category.rootCategoryId == (\"14\") || category.rootCategoryId == (\"20\") || category.rootCategoryId == (\"1201\") || category.rootCategoryId == (\"1101\") || category.rootCategoryId == (\"50018222\") || category.rootCategoryId == (\"50008090\") || category.rootCategoryId == (\"50012164\") || category.rootCategoryId == (\"50023904\") || category.rootCategoryId == (\"50019780\") || category.rootCategoryId == (\"50012082\") || category.rootCategoryId == (\"50002768\") || category.rootCategoryId == (\"50012100\") || category.rootCategoryId == (\"50011972\") || category.rootCategoryId == (\"124242008\") || category.rootCategoryId == (\"124044001\")\n" +
            "        !(seller.isBSeller()) && category.rootCategoryId == (\"50025618\") || category.rootCategoryId == (\"50024186\") || category.rootCategoryId == (\"50025111\") || category.rootCategoryId == (\"50488001\") || category.rootCategoryId == (\"50050471\") || category.rootCategoryId == (\"50026523\") || category.rootCategoryId == (\"50025110\") || category.rootCategoryId == (\"50019095\") || category.rootCategoryId == (\"50014442\") || category.rootCategoryId == (\"50026555\") || category.rootCategoryId == (\"50008075\") || category.rootCategoryId == (\"50158001\")\n" +
            "        !(seller.isBSeller()) && category.rootCategoryId == (\"25\") || category.rootCategoryId == (\"28\") || category.rootCategoryId == (\"35\") || category.rootCategoryId == (\"50022517\") || category.rootCategoryId == (\"50013864\") || category.rootCategoryId == (\"50468001\") || category.rootCategoryId == (\"50011397\") || category.rootCategoryId == (\"50005700\") || category.rootCategoryId == (\"50008165\") || category.rootCategoryId == (\"122650005\") || category.rootCategoryId == (\"50014812\")\n" +
            "        !(seller.isBSeller()) && category.rootCategoryId == (\"16\") || category.rootCategoryId == (\"30\") || category.rootCategoryId == (\"50010404\") || category.rootCategoryId == (\"50011740\") || category.rootCategoryId == (\"50006842\") || category.rootCategoryId == (\"1625\") || category.rootCategoryId == (\"50006843\")\n" +
            "        !(seller.isBSeller()) && category.rootCategoryId == (\"50014927\")\n" +
            "        !(seller.isBSeller()) && category.rootCategoryId == (\"1512\")\n" +
            "        !(seller.isBSeller()) && category.rootCategoryId == (\"50023575\")\n" +
            "        !(seller.isBSeller()) && category.rootCategoryId == (\"27\") || category.rootCategoryId == (\"50020579\") || category.rootCategoryId == (\"50020332\") || category.rootCategoryId == (\"50023804\") || category.rootCategoryId == (\"50008163\") || category.rootCategoryId == (\"50020808\") || category.rootCategoryId == (\"50020857\") || category.rootCategoryId == (\"50008164\") || category.rootCategoryId == (\"124568010\") || category.rootCategoryId == (\"122852001\") || category.rootCategoryId == (\"124050001\")\n" +
            "        !(seller.isBSeller()) && category.rootCategoryId == (\"50022703\") || category.rootCategoryId == (\"50074001\") || category.rootCategoryId == (\"26\") || category.rootCategoryId == (\"50024971\") || category.rootCategoryId == (\"124470006\")\n" +
            "        !(seller.isBSeller()) && category.rootCategoryId == (\"124470001\") || category.rootCategoryId == (\"124468001\") || category.rootCategoryId == (\"124466001\") || category.rootCategoryId == (\"50025004\") || category.rootCategoryId == (\"50020611\") || category.rootCategoryId == (\"50020485\") || category.rootCategoryId == (\"50007218\") || category.rootCategoryId == (\"50018004\") || category.rootCategoryId == (\"50018264\")\n" +
            "        !(seller.isBSeller()) && category.rootCategoryId == (\"2813\") || category.rootCategoryId == (\"122718004\")\n" +
            "        !(seller.isBSeller()) && (item.hasTag()) && (category.hasFeatureValue())\n" +
            "        !(seller.isBSeller()) && category.rootCategoryId == (\"52034\")\n" +
            "        (item.hasTag())\n" +
            "        !(seller.isBSeller())\n" +
            "        !(seller.isBSeller()) && (item.hasTag())\n" +
            "        (category.hasFeatureValue()) && !(seller.isBSeller())\n" +
            "        (category.hasFeatureValue()) && (category.hasFeatureValue()) && !(seller.isBSeller())\n" +
            "        (item.hasTag()) || (item.hasTag()) || (item.hasTag())\n" +
            "        (item.hasTag())\n" +
            "        (item.hasOption())\n" +
            "        (item.hasTag())\n" +
            "        (item.hasFeature())\n" +
            "        (item.hasTag())\n" +
            "        (item.matchAuctionType())\n" +
            "        (category.hasFeatureValue())\n" +
            "        (item.matchAuctionType())\n" +
            "        (buyer.hasTagOn()) && (seller.isBSeller())\n" +
            "        (buyer.hasTagOn())\n" +
            "        (buyer.hasTagOn())\n" +
            "        (buyer.hasTagOn())\n" +
            "        (buyer.hasTagOn())\n" +
            "        (buyer.hasTagOn()) && (seller.isBSeller()) || (seller.hasTagOn()) || (item.hasPropertyWithValue()) && (item.hasOption()) || category.rootCategoryId > (\"111\") || (category.hasFeatureValue())\n" +
            "        (category.hasFeatureValue())\n" +
            "        (item.hasTag())\n" +
            "        (item.hasTag())\n" +
            "        (item.hasFeature())\n" +
            "        (seller.hasTagOn())\n" +
            "        (seller.hasTagOn())\n" +
            "        (category.hasFeatureValue())\n" +
            "        (item.hasTag())\n" +
            "        (item.hasTag()) || (item.hasTag())\n" +
            "        (item.hasTag()) || (seller.isBSeller()) && category.categoryId == (\"1234\")\n" +
            "        (seller.isBSeller()) && category.categoryId == (\"1234\")\n" +
            "        !(seller.isBSeller())\n" +
            "        (seller.asas.isNotBlank()) && (seller.hasTagOn())\n" +
            "        (item.hasTag()) && (item.hasPropertyWithValue()) && (item.hasFeature())\n" +
            "        (item.hasTag())\n" +
            "        (item.matchAuctionType())\n" +
            "        (item.hasOption())\n" +
            "        category.rootCategoryId == (\"50050359\")\n" +
            "        (category.hasFeatureValue())\n" +
            "        category.categoryId != (\"111\")\n" +
            "        (item.hasTag()) && category.categoryId == (\"162104111\")\n" +
            "        (seller.asas.isNull()) && (seller.isBSeller())\n" +
            "        (seller.hasTagOn()) && (item.hasTag())\n" +
            "        category.categoryId == (\"1000\")\n" +
            "        (buyer.hasTagOn())\n" +
            "        category.categoryId == (\"100\") || category.categoryId != (\"99\")\n" +
            "        (seller.hasTagOn())\n" +
            "        (seller.isBSeller())\n" +
            "        (seller.isBSeller())\n" +
            "        (item.hasTag())\n" +
            "        category.categoryId == (\"2000\")\n" +
            "        seller.hasTagOn == (\"usertTag\") && item.hasTag == (\"45132\")\n" +
            "        seller.hasTagOn == (\"123\") || item.hasTag == (\"456\")\n" +
            "        category.categoryId == (\"50003313\")\n" +
            "        category.categoryId != (\"1\")\n" +
            "        ((\"order\").hasAttributeValue())\n" +
            "        (seller.isBSeller())\n" +
            "        (seller.isBSeller())\n" +
            "        (seller.isBSeller())\n" +
            "        (seller.isBSeller())\n" +
            "        (seller.isBSeller())\n" +
            "        (seller.isBSeller())\n" +
            "        category.categoryId != (\"12\")\n" +
            "        category.categoryId != (\"123\")\n" +
            "        category.categoryId != (\"12\")\n" +
            "        category.categoryId != (\"123\")\n" +
            "        (seller.isBSeller())\n" +
            "        item.hasTag == (\"1991\")\n" +
            "        (item.hasTag())\n" +
            "        (item.hasTag()) && (item.hasTag())\n" +
            "        (category.hasFeatureValue())\n" +
            "        (category.hasFeatureValue())\n" +
            "        (item.hasTag()) && (item.hasFeature())\n" +
            "        (item.hasTag()) && (item.hasFeature())\n" +
            "        (item.hasTag()) && (item.hasFeature())\n" +
            "        (item.hasTag()) && (item.hasFeature())\n" +
            "        (item.hasTag()) && (item.hasTag()) && (item.hasTag()) && (item.hasFeature())\n" +
            "        (item.hasTag()) && (item.hasTag()) && (item.hasTag()) && (item.hasFeature())\n" +
            "        (item.hasTag()) && (item.hasTag()) && (item.hasTag()) && (item.hasFeature())\n" +
            "        (item.hasTag()) && (item.hasTag()) && (item.hasTag()) && (item.hasFeature())\n" +
            "        (item.hasTag()) && (item.hasTag()) && (item.hasTag()) && (item.hasFeature())\n" +
            "        (item.hasTag()) && (item.hasTag()) && (item.hasTag()) && (item.hasFeature())\n" +
            "        (item.hasTag()) && (item.hasTag()) && (item.hasTag()) && (item.hasFeature())\n" +
            "        (item.hasTag()) && (item.hasTag()) && (item.hasTag()) && (item.hasFeature())\n" +
            "        (item.hasTag())\n" +
            "        (item.hasTag())\n" +
            "        (item.hasOption())\n" +
            "        (seller.isBSeller()) && (item.hasTag())\n" +
            "        category.categoryId == (\"100\") && (buyer.hasTagOn())\n" +
            "        (category.hasFeatureValue()) && (seller.isBSeller())\n" +
            "        (item.hasTag())\n" +
            "        (item.hasTag()) && (seller.isBSeller())\n" +
            "        category.categoryId != (\"11111111\") || category.categoryId > (\"1111111111\")\n" +
            "        (item.hasTag())\n" +
            "        (item.hasTag())\n" +
            "        (item.hasTag())\n" +
            "        (item.hasTag())\n" +
            "        (item.hasTag())\n" +
            "        (item.hasTag())\n" +
            "        (item.hasTag())\n" +
            "        (item.hasTag())\n" +
            "        (seller.isBSeller())\n" +
            "        (item.hasTag())\n" +
            "        (item.hasTag())\n" +
            "        category.categoryId == (\"122012002\")\n" +
            "        (item.hasTag())\n" +
            "        category.rootCategoryId == (\"26\")\n" +
            "        category.categoryId == (\"50050566\")\n" +
            "        (item.hasTag())\n" +
            "        (item.hasTag())\n" +
            "        (item.hasTag())\n" +
            "        (item.hasTag())";

    @Test
    void parser() throws ParseException {
        String lfkdsk = "lfkdsk.LFKDSK[11111 + 12222](1111,2222,\"LFKDSK\") == true";
        String lfkdsk0 = "lfkdsk() == false && lfkdsk";
        String lfkdsk1 = "(lfkdsk() ? lfkdsk : lfkdsk) ? true : false";
//        runExpr(lfkdsk, false, null);
//        runExpr(lfkdsk0, false, null);
        runExpr(lfkdsk1, false, null);
    }

    @Test
    void testThreeExpr() {
        runExpr("lfkdsk ? 1111 : ddddd", false, null);
    }

    @Test
    void testParser() {
        for (int i = 0; i < 20; i++) {
            long start = System.currentTimeMillis();
            for (int j = 0; j < 100; j++) {
                runExpr("lfkdsk.lfkdsk(1111,2222,\"LFKDSK\") == true", false, null);
            }
            System.out.println(System.currentTimeMillis() - start);
        }
    }

    @Test
    void testEval() {
        JustContext context = new JustMapContext();
        context.put("i", 10e2);
        context.put("pi", 3.14f);

        String expr = "pi*i*i*pi";
        Logger.init("test parser");
        JustParser parser = new JustParserImpl();

        for (int i = 0; i < 20; i++) {
            long start = System.currentTimeMillis();
            for (int j = 0; j < 1000; j++) {

                Lexer lexer = new JustLexerImpl(new StringReader(expr));
                while (lexer.hasMore()) {
                    AstNode node = parser.parser(lexer);
                    node.eval(context);
//                    Logger.v(" => " + node.call(context).toString() + "  ");
                }
            }
            System.out.println(System.currentTimeMillis() - start);
        }
    }

    @Test
    void testOp() {
        Logger.init("test op");
        runExpr("1 + 1", true, null);
    }


    @Test
    void testAllLatticeTests() throws IOException {
        LineNumberReader reader = new LineNumberReader(new StringReader(allTests));
        String line;
        Lexer lexer = new JustLexerImpl();
        JustParser parser = new JustParserImpl();
        Logger.init();
        while ((line = reader.readLine()) != null) {
            lexer.reset(line);
            lexer.hasMore();
            parser.parser(lexer).printAst();
        }
    }

    @Test
    void testAllLatticeTestsEval() throws IOException {
        LineNumberReader reader = new LineNumberReader(new StringReader(allTests));
        String line;
        Lexer lexer = new JustLexerImpl();
        JustParser parser = new JustParserImpl();
        Logger.init();
        JustContext env = new JustMapContext();
        OrderLineSpec lineSpec = new OrderLineSpec();
        Map<String, Object> context1 = lineSpec.getStandardModel().buildContext();
        for (Map.Entry<String, Object> stringObjectEntry : context1.entrySet()) {
            if (stringObjectEntry.getKey().equals("standard"))
                env.put(stringObjectEntry.getKey(), stringObjectEntry.getValue());
        }


        while ((line = reader.readLine()) != null) {
            line = line.replace("item.", "standard.item.")
                       .replace("buyer.", "standard.buyer.")
                       .replace("category.", "standard.category.")
                       .replace("source.", "standard.source.")
                       .replace("seller.", "standard.seller.");

            lexer.reset(line);
            lexer.hasMore();
            AstNode node = parser.parser(lexer);
            node.printAst();
            node.eval(env);
        }
    }

    public static String runExpr(String expr, boolean eval, JustContext context) {
        Lexer lexer = new JustLexerImpl(new StringReader(expr));
        JustParser parser = new JustParserImpl();
        Logger.init("test parser");
        String returnString = "";
        while (lexer.hasMore()) {
            AstNode node = parser.parser(lexer);

            Logger.v(" => " + node.toString() + "  ");
            if (eval) {
                returnString = node.eval(context).toString();
                Logger.v(" => " + returnString + "  ");
            }
        }
        return returnString;
    }
}