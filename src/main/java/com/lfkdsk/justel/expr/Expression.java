package com.lfkdsk.justel.expr;

import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.eval.Evaluable;
import com.lfkdsk.justel.exception.EvalException;

/**
 * Created by liufengkai on 2017/7/20.
 */
public interface Expression extends Evaluable {

  @Override
  default Object eval(JustContext context) {
    throw new EvalException("Use default eval in expression");
  }
}
