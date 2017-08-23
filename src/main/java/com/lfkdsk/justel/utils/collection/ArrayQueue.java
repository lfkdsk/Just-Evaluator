package com.lfkdsk.justel.utils.collection;

import java.util.ArrayList;

/**
 * ArrayQueue => use Array to build Queue.
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/24.
 */
public class ArrayQueue<E> {

    private ArrayList<E> arrayList = new ArrayList<>();

    public boolean add(E e) {
        return arrayList.add(e);
    }

    public E remove() {
        return arrayList.remove(arrayList.size() - 1);
    }

    public E poll() {
        return arrayList.remove(0);
    }

    public E peek() {
        return arrayList.get(0);
    }

    public E get(int index) {
        return arrayList.get(index);
    }

    public int size() {
        return arrayList.size();
    }

    public void clear() {
        arrayList.clear();
    }
}
