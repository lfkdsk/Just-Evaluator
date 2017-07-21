package com.lfkdsk.justel.exception;

import com.lfkdsk.justel.ast.AstNode;

/**
 * Runtime com.lfkdsk.justel.eval in
 *
 * @author liufengkai Created by liufengkai on 17/7/18.
 */

public class EvalException extends RuntimeException {

  public EvalException(String msg) {
    super(msg);
  }

  public EvalException(String msg, AstNode tree) {
    super(msg + " " + tree.location());
  }
}
