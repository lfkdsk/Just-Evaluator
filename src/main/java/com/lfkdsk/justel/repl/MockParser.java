package com.lfkdsk.justel.repl;

import com.lfkdsk.justel.parser.BnfCom;
import com.lfkdsk.justel.parser.JustParserImpl;

/**
 * insert operator for REPL
 *
 * @author liufengkai
 */
public class MockParser extends JustParserImpl {

    public MockParser() {
        this.insertOperators("=", 14, BnfCom.Operators.RIGHT, MockAssignOperator.class);
    }
}
