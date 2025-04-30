package com.aliyasirnac.algorithms;

public class QuickUnionUF {
    private final int[] parent;
    private final int[] size;
    private final int[] maximum;
    private int count;

    public QuickUnionUF(int n) {
        parent = new int[n];
        size = new int[n];
        maximum = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            maximum[i] = i;
            size[i] = 1;
        }
    }

    public int getCount() {
        return count;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    private int root(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
        if (rootP == rootQ) {
            return;
        }
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
            maximum[rootQ] = Math.max(maximum[rootP], maximum[rootQ]);
            return;
        }
        parent[rootQ] = rootP;
        size[rootP] += size[rootQ];
        maximum[rootP] = Math.max(maximum[rootP], maximum[rootQ]);
        count--;
    }

    private int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    public int findCanoncial(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IndexOutOfBoundsException("Index " + p + " is not between 0 and " + (parent.length - 1));
        }

        int rootP = root(p);
        return maximum[rootP];
    }
}
