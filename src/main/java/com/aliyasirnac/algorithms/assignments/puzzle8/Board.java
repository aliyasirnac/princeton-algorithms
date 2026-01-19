package com.aliyasirnac.algorithms.assignments.puzzle8;

import edu.princeton.cs.algs4.StdOut;

import java.util.*;

import static java.lang.System.*;

public class Board {
    private final int[][] board;
    private final int[][] goal;
    private final Map<Integer, int[]> goalMap;
    private final int n;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        if (tiles == null) {
            throw new IllegalArgumentException();
        }
        n = tiles.length;
        board = new int[n][n];
        for (int row = 0; row < tiles.length; row++) {
            arraycopy(tiles[row], 0, board[row], 0, tiles.length);
        }
        goal = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        goalMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                goalMap.put(goal[i][j], new int[]{i, j}); // goal value -> (row, col) point
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int j = 0;
            while (j < n) {
                int currentItem = board[i][j];
                s.append(currentItem).append(" ");
                if (i < j && currentItem % n == 0) {
                    s.append("\n");
                }
                j++;
            }
        }
        return dimension() + "\n" + s;
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int distance = 0;
        for (int i = 0; i < n; i++) {
            int j = 0;
            while (j < n) {
                int currentItem = board[i][j];
                if (currentItem != 0 && currentItem != goal[i][j]) {
                    distance++;
                }
                j++;
            }
        }
        return distance;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int distance = 0;
        for (int i = 0; i < n; i++) {
            int j = 0;
            while (j < n) {
                int currentItem = board[i][j];
                if (currentItem != 0) {
                    int[] goals = goalMap.get(currentItem);
                    int indexI = goals[0];
                    int indexJ = goals[1];
                    distance += Math.abs(i - indexI) + Math.abs(j - indexJ);
                }
                j++;
            }
        }
        return distance;
    }

    // is this board the goal board?
    // probably we will loop until isGoal == true
    public boolean isGoal() {
        return board == goal;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;

        if (y == null || y.getClass() != this.getClass()) return false;

        Board that = (Board) y;

        if (this.n != that.n) return false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.board[i][j] != that.board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();
        int row = 0, col = 0;

        // 1. Find the blank tile (0)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    row = i; col = j;
                }
            }
        }

        // 2. Try all 4 directions (check bounds first)
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] dir : directions) {
            int newR = row + dir[0];
            int newC = col + dir[1];

            if (newR >= 0 && newR < n && newC >= 0 && newC < n) {
                int[][] copy = copyBoard(board);
                exch(copy, row, col, newR, newC);
                neighbors.add(new Board(copy));
            }
        }
        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] copy = copyBoard(this.board);

        // Swap the first two tiles that are not zero
        // Usually, checking (0,0) and (0,1) works,
        // but if one is 0, use the second row.
        if (copy[0][0] != 0 && copy[0][1] != 0) {
            exch(copy, 0, 0, 0, 1);
        } else {
            exch(copy, 1, 0, 1, 1);
        }

        return new Board(copy);
    }

    // Swaps two specific coordinates in a 2D array
    private void exch(int[][] a, int r1, int c1, int r2, int c2) {
        int temp = a[r1][c1];
        a[r1][c1] = a[r2][c2];
        a[r2][c2] = temp;
    }

    // Deep copy of the board
    private int[][] copyBoard(int[][] original) {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            copy[i] = original[i].clone();
        }
        return copy;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] ints = new int[][]{
                {8, 1, 3},
                {4, 0, 2},
                {7, 6, 5} // 0 represents the blank
        };
        Board board = new Board(ints);
        StdOut.println("Dimension: " + board.dimension());
        StdOut.println("Board:\n" + board);
        StdOut.println(board.equals(new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0} // 0 represents the blank
        })));
        StdOut.println("hamming: " + board.hamming());
        StdOut.println("Manhattan: " + board.manhattan());
        StdOut.println(board.twin());
        for (Board b : board.neighbors()) {
            StdOut.println(b);
        }
    }
}
