package com.lfkdsk.justel.grammar;

import com.lfkdsk.justel.literal.StringLiteral;
import com.lfkdsk.justel.parser.BnfCom;

/**
 * Created by liufengkai on 2017/7/18.
 */
public class Grammar {
  public static final BnfCom string = BnfCom.rule().string(StringLiteral.class);

  public static final BnfCom expr = BnfCom.rule();
}
