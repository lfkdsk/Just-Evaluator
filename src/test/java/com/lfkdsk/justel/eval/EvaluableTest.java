package com.lfkdsk.justel.eval;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.parser.JustParserImpl;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.utils.FormatUtils.reformatAstPrint;

class EvaluableTest {

    @Test
    void evalTest() {
        Logger.init();
        AstNode node = new JustParserImpl().parser(new JustLexerImpl().scanner("\"lfkdsk\" + lfkdsk + 10000 + 100.0"));
        String reformat = reformatAstPrint(node.toString());

        Logger.v(reformat);
    }
}