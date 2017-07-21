package com.lfkdsk.justel.ast;

import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.eval.Evalable;

import java.util.Iterator;

/**
 * AST TREE NODE 抽象语法树的通用接口
 * Created by liufengkai on 16/7/11.
 */
public abstract class AstNode implements Iterable<AstNode>,
    Evalable {

  private final int tag;

  public AstNode(int tag) {
    this.tag = tag;
  }

  /**
   * 获取指定子节点
   *
   * @param index 索引
   * @return 子节点
   */
  public abstract AstNode child(int index);

  /**
   * 返回子节点迭代器
   *
   * @return 迭代器
   */
  public abstract Iterator<AstNode> children();

  /**
   * 子节点数目
   *
   * @return count
   */
  public abstract int childCount();

  /**
   * 位置描述
   *
   * @return location
   */
  public abstract String location();

  public Iterator<AstNode> iterator() {
    return children();
  }

  public Object eval(JustContext context) {
    return this;
  }
}
