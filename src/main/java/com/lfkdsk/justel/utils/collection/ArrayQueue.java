package com.lfkdsk.justel.utils.collection;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

/**
 * ArrayQueue => use Array to build Queue.
 *
 * @author liufengkai
 * Created by liufengkai on 2017/7/24.
 */
public class ArrayQueue<E> implements Deque<E> {

    @Getter
    private ArrayList<E> arrayList = new ArrayList<>();

    @Override
    public void addFirst(E e) {
        arrayList.add(0, e);
    }

    @Override
    public void addLast(E e) {
        arrayList.add(e);
    }

    @Override
    public boolean offerFirst(E e) {
        arrayList.add(0, e);
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        return arrayList.add(e);
    }

    @Override
    public E removeFirst() {
        return arrayList.remove(0);
    }

    @Override
    public E removeLast() {
        return arrayList.remove(arrayList.size() - 1);
    }

    @Override
    public E pollFirst() {
        return arrayList.remove(0);
    }

    @Override
    public E pollLast() {
        return arrayList.remove(arrayList.size() - 1);
    }

    @Override
    public E getFirst() {
        return arrayList.get(0);
    }

    @Override
    public E getLast() {
        return arrayList.get(arrayList.size() - 1);
    }

    @Override
    public E peekFirst() {
        return arrayList.get(0);
    }

    @Override
    public E peekLast() {
        return arrayList.get(arrayList.size() - 1);
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        throw new UnsupportedOperationException("Unsupported removeFirstOccurrence");
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        throw new UnsupportedOperationException("Unsupported removeLastOccurrence");
    }

    public boolean add(E e) {
        return arrayList.add(e);
    }

    @Override
    public boolean offer(E e) {
        return arrayList.add(e);
    }

    public E remove() {
        return arrayList.remove(arrayList.size() - 1);
    }

    public E poll() {
        return arrayList.remove(0);
    }

    @Override
    public E element() {
        return getFirst();
    }

    public E peek() {
        return arrayList.get(0);
    }

    @Override
    public void push(E e) {
        add(e);
    }

    @Override
    public E pop() {
        return removeLast();
    }

    @Override
    public boolean remove(Object o) {
        return arrayList.remove(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return arrayList.containsAll(c);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends E> c) {
        return arrayList.addAll(c);
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return arrayList.removeAll(c);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return arrayList.retainAll(c);
    }

    @Override
    public boolean contains(Object o) {
        return arrayList.contains(o);
    }

    public E get(int index) {
        return arrayList.get(index);
    }

    public int size() {
        return arrayList.size();
    }

    @Override
    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return arrayList.iterator();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return arrayList.toArray(a);
    }

    @NotNull
    @Override
    public Iterator<E> descendingIterator() {
        throw new UnsupportedOperationException("Unsupported descendingIterator");
    }

    public void clear() {
        arrayList.clear();
    }

    @Override
    public String toString() {
        return arrayList.toString();
    }
}
