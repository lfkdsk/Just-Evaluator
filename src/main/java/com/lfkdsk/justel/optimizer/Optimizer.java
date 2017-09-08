package com.lfkdsk.justel.optimizer;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.utils.collection.Tuple;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by liufengkai on 2017/9/8.
 */
public class Optimizer {

    private AstNode root;

    private Map<Class, Consumer<Tuple<AstNode, Tuple<AstNode, Integer>>>> optimizers = new HashMap<>();

    public Optimizer(AstNode root) {
        this.root = root;
    }

    public AstNode optimizer(AstNode parent) {
        for (int i = 0; i < root.childCount(); i++) {
            AstNode child = root.child(i);
            Consumer<Tuple<AstNode, Tuple<AstNode, Integer>>>
                    consumer = optimizers.get(child.getClass());

            if (consumer != null) {
                Tuple<AstNode, Tuple<AstNode, Integer>> tuple = new Tuple<>(child, new Tuple<>(parent, i));
                consumer.accept(tuple);
            }

            optimizer(child);
        }

        return root;
    }

    public void addOptimizer(Class<? extends AstNode> clazz,
                             Consumer<Tuple<AstNode, Tuple<AstNode, Integer>>> consumer) {
        optimizers.put(clazz, consumer);
    }
}
