package com.lfkdsk.justel.parser.bnf;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.exception.ParseException;
import com.lfkdsk.justel.lexer.Lexer;

import java.util.ArrayList;
import java.util.List;

import static com.lfkdsk.justel.parser.bnf.EBNF.Element.create;

/**
 * Created by liufengkai on 2017/9/13.
 */
public final class EBNF {

    @FunctionalInterface
    private interface Parse {
        /**
         * Grammar Analyze
         *
         * @param lexer Lexer
         * @param nodes Ast-List
         * @throws ParseException
         */
        void parse(Lexer lexer, List<AstNode> nodes) throws ParseException;
    }

    @FunctionalInterface
    private interface Match {

        /**
         * Match Elements
         *
         * @param lexer Lexer
         * @return tof?
         * @throws ParseException
         */
        boolean match(Lexer lexer) throws ParseException;
    }

    protected static class Element {
        final Parse parse;
        final Match match;

        private Element(Parse parse, Match match) {
            this.parse = parse;
            this.match = match;
        }

        public static Element create(Parse parse, Match match) {
            return new Element(parse, match);
        }
    }

    List<Element> elements = new ArrayList<>();

    public EBNF ast(final EBNF ebnf) {
        elements.add(create((lexer, nodes) -> nodes.add(ebnf.parse(lexer)), ebnf::match));

        return this;
    }

    public AstNode parse(Lexer lexer) {
        return null;
    }

    public boolean match(Lexer lexer) {
        return true;
    }
}
