package com.aliyasirnac.algorithms.assignments.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final boolean[][] grid;
    private final int n;
    private final WeightedQuickUnionUF uf;
    private int openSites;
    private final int virtualTop;
    private final int virtualBottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        grid = new boolean[n][n];
        this.n = n;
        this.uf = new WeightedQuickUnionUF(n * n + 2);
        virtualTop = n * n;
        virtualBottom = n * n + 1;
    }

    private int check(int row, int col) {
        return (row - 1) * n + (col - 1);
    }

    private void validate(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("Invalid row or col");
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) return;
        grid[row - 1][col - 1] = true;
        openSites++;

        int current = check(row, col);
        if (row == 1) uf.union(current, virtualTop);
        if (row == n) uf.union(current, virtualBottom);

        if (row > 1 && isOpen(row - 1, col)) uf.union(current, check(row - 1, col));
        if (row < n && isOpen(row + 1, col)) uf.union(current, check(row + 1, col));
        if (col > 1 && isOpen(row, col - 1)) uf.union(current, check(row, col - 1));
        if (col < n && isOpen(row, col + 1)) uf.union(current, check(row, col + 1));
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf.find(check(row, col)) == uf.find(virtualTop);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(virtualTop) == uf.find(virtualBottom);
    }
}
