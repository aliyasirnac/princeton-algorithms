package com.aliyasirnac.algorithms.assignments.queues;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

// I wrote this class with double linked list; but in the assignment they want an array, maybe I'll write later.
public class RandomizedQueue<Item> implements Iterable<Item> {
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

    public RandomizedQueue() {
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldLast = last;
        last = new Node(null, oldLast, item);
        if (oldLast == null) first = last;
        else oldLast.next = last;
        size++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int randomIndex = StdRandom.uniformInt(size);
        Node nodeToRemove = get(randomIndex);

        Item item = nodeToRemove.item;

        if (size == 1) {
            first = null;
            last = null;
        } else if (nodeToRemove == first) {
            first = first.next;
            first.prev = null;
        } else if (nodeToRemove == last) {
            last = last.prev;
            last.next = null;
        } else {
            nodeToRemove.prev.next = nodeToRemove.next;
            nodeToRemove.next.prev = nodeToRemove.prev;
        }

        nodeToRemove.next = null;
        nodeToRemove.prev = null;
        nodeToRemove.item = null;

        size--;

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int randomIndex = StdRandom.uniformInt(size);
        Node result = get(randomIndex);
        return result.item;
    }

    private Node get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node current = first;

        if (index > size / 4) {
            current = last;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        } else {
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        }
        return current;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node i = first;

            @Override
            public boolean hasNext() {
                return i.next != null;
            }

            @Override
            public Item next() {
                if (hasNext()) {
                    Item item = i.item;
                    i = i.next;
                    return item;
                }
                throw new java.util.NoSuchElementException();
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
