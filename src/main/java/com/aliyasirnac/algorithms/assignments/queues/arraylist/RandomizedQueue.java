package com.aliyasirnac.algorithms.assignments.queues.arraylist;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private final List<Item> items;

    public RandomizedQueue() {
        this.items = new ArrayList<>();
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // return the number of items on the randomized queue
    public int size() {
        return items.size();
    }

    // add the item
    public void enqueue(Item item) {
        items.add(item);
    }

    // remove and return a random item
    public Item dequeue() {
        return items.remove(StdRandom.uniformInt(items.size() - 1));
    }

    // return a random item (but do not remove it)
    public Item sample() {
        return items.get(StdRandom.uniformInt(items.size() - 1));
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
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        StdOut.println("Size: " + queue.size());
        StdOut.println("Sampled item: " + queue.sample());

        for (int item : queue) {
            StdOut.println("Iterated item: " + item);
        }

        StdOut.println("Dequeued item: " + queue.dequeue());
        StdOut.println("Size after dequeue: " + queue.size());
    }
}
