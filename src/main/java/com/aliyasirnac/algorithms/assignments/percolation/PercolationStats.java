package com.aliyasirnac.algorithms.assignments.percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int[] percolationStats;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        percolationStats = new int[n * n];
        for (int i = 0; i < trials; i++) {
            percolationStats[i] = 1;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(percolationStats);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(percolationStats);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (stddev() / Math.sqrt(percolationStats.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (stddev() / Math.sqrt(percolationStats.length));
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(5, 5);
        StdOut.println("mean = " + stats.mean());
        StdOut.println("stddev = " + stats.stddev());
        StdOut.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}
