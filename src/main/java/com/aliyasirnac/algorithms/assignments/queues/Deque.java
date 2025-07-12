package com.aliyasirnac.algorithms.assignments.queues;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int size;

    private class Node {
        Item item;
        Node next, prev;

        Node(Node next, Node prev, Item item) {
            this.next = next;
            this.prev = prev;
            this.item = item;
        }
    }

    public Deque() {
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldFirst = first;
        first = new Node(oldFirst, first, item);

        if (last == null) last = first;
        else oldFirst.prev = first;
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldLast = last;
        last = new Node(null, oldLast, item);
        if (first == null) first = last;
        else oldLast.next = last;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node oldFirst = first;
        first = first.next;
        if (first == null) last = null;
        size--;
        return oldFirst.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = last.item;
        Node oldLast = last;
        last = oldLast.prev;

        if (last == null) first = null;
        else last.next = null;

        // for garbage collector
        oldLast.prev = null;
        oldLast.next = null;
        oldLast.item = null;

        size--;

        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node i = first;

            @Override
            public boolean hasNext() {
                return i != null;
            }

            @Override
            public Item next() {
                if (hasNext()) {
                    Item item = i.item;
                    i = i.next;
                    return item;
                }
                throw new NoSuchElementException();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        Deque<Integer> items = new Deque<>();
        items.addFirst(1);
        items.addFirst(2);
        items.addFirst(3);
        items.addFirst(4);
        items.addLast(34);

        for (int item : items) {
            StdOut.println(item);
        }

        StdOut.println("------");
        StdOut.println(items.removeFirst());
        StdOut.println(items.removeLast());

        StdOut.println("------");

        for (int item : items) {
            StdOut.println(item);
        }
        StdOut.println("------");

    }
}
