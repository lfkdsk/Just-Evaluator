package com.lfkdsk.justel.utils.collection;

import java.util.*;

/**
 * Created by liufengkai on 2017/9/11.
 */
public class SkipList<K extends Comparable<K>, V> implements Map<K, V> {

    /**
     * Probability to flow one node up
     */
    private static final double PROBABILITY = 0.5;

    /**
     * head / tail
     */
    private SkipListNode<K, V> headNode, tailNode;

    /**
     * all node counts
     */
    private int nodeCounts;

    /**
     * all list level
     */
    private int listLevels;

    /**
     * random util
     */
    private Random random;

    /**
     * Key Set
     */
    private Set<K> keySet = new HashSet<>();

    /**
     * Value Set
     */
    private Set<V> valueSet = new HashSet<>();

    public SkipList() {
        this.random = new Random();
        this.clear();
    }

    @Override
    public int size() {
        return nodeCounts;
    }

    @Override
    public boolean isEmpty() {
        return nodeCounts == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        for (SkipListNode node = headNode; node != null; node = node.right) {
            if (node.value.equals(value)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public V get(Object key) {
        if (key == null) {
            throw new UnsupportedOperationException("key could not be null");
        }

        SkipListNode<K, V> node = search((K) key);

        return node == null ? null : node.value;
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new UnsupportedOperationException("key could not be null");
        }

        SkipListNode<K, V> p = findNodeByKey(key);
        // change value | equal key
        if (key.equals(p.key)) {
            p.value = value;
            return value;
        }

        SkipListNode<K, V> q = new SkipListNode<>(key, value);
        backLink(p, q);
        nodeCounts++;

        // current level
        int currentLevel = 0;
        while (random.nextDouble() < PROBABILITY) {
            if (currentLevel >= listLevels) {
                createNewLevel();
            }

            // find up level node to bind it
            while (p.up == null) {
                if (p.left == null) {
                    // p equal header node
                    createNewLevel();
                    break;
                }
                p = p.left;
            }

            // upper level node
            p = p.up;

            // save key
            SkipListNode<K, V> e = new SkipListNode<>(key, null);

            backLink(p, e);
            verticalLink(e, q);
            q = e;
            currentLevel++;
        }

        keySet.add(key);
        valueSet.add(value);

        return value;
    }

    private void createNewLevel() {
        listLevels++;

        SkipListNode<K, V> p1 = new SkipListNode<>(null, null);
        SkipListNode<K, V> p2 = new SkipListNode<>(null, null);

        horizontalLink(p1, p2);

        verticalLink(p1, headNode);
        verticalLink(p2, tailNode);

        headNode = p1;
        tailNode = p2;
    }

    @Override
    public V remove(Object key) {
        SkipListNode<K, V> node = findNodeByKey((K) key);

        if (node == null) {
            return null;
        } else if (node.key.equals(key)) {

            while (node != null) {
                SkipListNode<K, V> left = node.left;
                SkipListNode<K, V> right = node.right;

                if (left != null && right != null) {
                    horizontalLink(left, right);
                }

                node = node.up;
            }

            node = headNode;

            while (node != null && node.right.equals(tailNode)) {
                SkipListNode<K, V> oldHeadNode = headNode;
                SkipListNode<K, V> oldTailNode = tailNode;

                this.headNode = oldHeadNode.down;
                this.tailNode = oldTailNode.down;
                this.listLevels--;

                node = headNode;

                oldHeadNode = null;
                oldTailNode = null;
            }
        }

        return null;
    }


    @Override
    public boolean remove(Object key, Object value) {
        return remove(key) != null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        this.headNode = new SkipListNode<>(null, null);
        this.tailNode = new SkipListNode<>(null, null);
        this.nodeCounts = 0;
        this.listLevels = 0;

        // horizontal link head === tail nodes
        this.horizontalLink(headNode, tailNode);
    }

    @Override
    public Set<K> keySet() {
        return keySet;
    }

    @Override
    public Collection<V> values() {
        return valueSet;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<>();
        for (SkipListNode<K, V> node = headNode; node != null; node = node.right) {
            set.add(node);
        }

        return set;
    }

    /**
     * add front link after node
     *
     * @param front front-node
     * @param back  back-node
     */
    private void backLink(SkipListNode<K, V> front, SkipListNode<K, V> back) {
        back.left = front;
        back.right = front.right;
        front.right.left = back;
        front.right = back;
    }


    private SkipListNode<K, V> findNodeByKey(K key) {
//        System.out.println("Start Search: ");
        SkipListNode<K, V> head = headNode;

        while (true) {
            while (head.right.key != null && key.compareTo(head.right.key) >= 0) {
                head = head.right;
//                System.out.println(head);
            }

            if (head.down != null) {
                head = head.down;
//                System.out.println(head);
            } else {
                break;
            }

        }

        return head;
    }

    private SkipListNode<K, V> search(K key) {
        SkipListNode<K, V> p = findNodeByKey(key);

        if (key.equals(p.key)) {
            return p;
        } else {
            return null;
        }
    }

    /**
     * Horizontal Link
     *
     * @param left  left node
     * @param right right node
     */
    private void horizontalLink(SkipListNode<K, V> left, SkipListNode<K, V> right) {
        left.right = right;
        right.left = left;
    }

    /**
     * Vertical Link
     *
     * @param up   up node
     * @param down down node
     */
    private void verticalLink(SkipListNode<K, V> up, SkipListNode<K, V> down) {
        up.down = down;
        down.up = up;
    }

    String debugStructure() {
        List<List<SkipListNode<K, V>>> list = new LinkedList<>();
        int currentLevel = listLevels;
        SkipListNode<K, V> node = headNode;
        while (true) {
            if (currentLevel == -1) break;

            List<SkipListNode<K, V>> levelList = new LinkedList<>();

            while (node != null) {
                levelList.add(node);
                node = node.right;
            }

            currentLevel--;

            int times = listLevels - currentLevel;
            node = headNode;
            while (times != 0) {
                node = node.down;
                times--;
            }

            list.add(levelList);
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            List<SkipListNode<K, V>> level = list.get(i);

            builder.append("Level : ")
                    .append(String.valueOf(listLevels - i))
                    .append(" > \t");

            for (SkipListNode<K, V> kvSkipListNode : level) {

                builder.append(kvSkipListNode.key)
                        .append("\t");
            }

            builder.append("\n");

            if (i == list.size() - 1) {
                builder.append("            \t");
                for (SkipListNode<K, V> kvSkipListNode : level) {

                    builder.append(kvSkipListNode.value)
                            .append("\t");
                }
            }
        }

        return builder.toString();
    }

    /**
     * Node in Skip List
     *
     * @param <V> Type
     */
    static class SkipListNode<K, V> implements Entry<K, V> {

        /**
         * point to =>
         */
        SkipListNode<K, V> up, down, left, right;

        K key;
        V value;

        SkipListNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            return this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null) {
                return false;
            }

            if (!(o instanceof SkipListNode<?, ?>)) {
                return false;
            }

            SkipListNode<K, V> ent;
            try {
                ent = (SkipListNode<K, V>) o;
            } catch (ClassCastException ex) {
                return false;
            }

            return (ent.key == key) && (ent.value == value);
        }

        @Override
        public String toString() {
            return "SkipListNode{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }


}
