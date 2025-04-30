package com.aliyasirnac.algorithms;

public class UF {
    private final int[] parent;

    public UF(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == rootP) {
                parent[i] = rootQ;
            }
        }
    }

    boolean connected(int p, int q) {
        return parent[p] == parent[q];
    }

    int find(int p) {
        return 0;
    }
}
