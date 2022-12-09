package ru.ivanmurzin.falloutdungeon.tools;

import androidx.annotation.NonNull;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;

public class GameList<T> extends AbstractList<T> {
    private Node<T> head;
    private Node<T> tail;

    private int size = 0;

    @Override
    public boolean add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
            size = 1;
            return true;
        }
        tail.next = newNode;
        newNode.previous = tail;
        tail = newNode;
        ++size;
        return true;
    }

    @Override
    public T get(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("size: " + size + " index: " + index);
        Node<T> tmp = head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp.data;
    }

    private void removeNode(Node<T> node) {
        if (node.previous == null) {
            head = node.next;
            if (node.next != null) node.next.previous = null;
            else tail = null;
        } else {
            node.previous.next = node.next;
            if (node.next != null) node.next.previous = node.previous;
            else tail = node.previous;
        }
    }

    @Override
    public boolean remove(Object o) {
        Node<T> tmp = head;
        while (tmp != null) {
            if (tmp.data.equals(o)) {
                removeNode(tmp);
                --size;
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return new GameListIterator();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends T> collection) {
        for (T item : collection) {
            if (!add(item)) return false;
        }
        return true;
    }

    private static class Node<T> {
        Node<T> previous;
        Node<T> next;
        T data;

        public Node(T data) {
            this.data = data;
        }

        public Node(Node<T> next) {
            this.next = next;
        }
    }

    private class GameListIterator implements Iterator<T> {
        Node<T> cursor = new Node<>(head);

        @Override
        public boolean hasNext() {
            return cursor.next != null;
        }

        @Override
        public T next() {
            cursor = cursor.next;
            return cursor.data;
        }

        @Override
        public void remove() {
            removeNode(cursor);
            --size;
        }
    }
}
