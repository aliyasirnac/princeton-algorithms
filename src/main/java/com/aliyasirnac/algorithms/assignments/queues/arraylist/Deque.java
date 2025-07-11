package com.aliyasirnac.algorithms.assignments.queues.arraylist;

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private final List<Item> items;

    public Deque() {
        this.items = new ArrayList<>();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int size() {
        return items.size();
    }

    public Item get(int index) {
        return items.get(index);
    }

    public void addFirst(Item item) {
        items.add(0, item);
    }

    public void addLast(Item item) {
        items.add(item);
    }

    public Item removeFirst() {
        Item firstItem = items.get(0);
        items.remove(0);

        return firstItem;
    }

    public Item removeLast() {
        Item firstItem = items.get(items.size() - 1);
        items.remove(items.size() - 1);
        return firstItem;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < items.size();
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return items.get(index++);
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

        for (int item: items) {
            StdOut.println(item);
        }

        StdOut.println("------");
        StdOut.println(items.removeFirst());
        StdOut.println(items.removeLast());


    }
}
