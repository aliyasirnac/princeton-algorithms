package com.aliyasirnac.algorithms.assignments.puzzle8;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

import static java.lang.System.*;

public class Board {
    private final int[][] board;
    private final int n;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        if (tiles == null) {
            throw new IllegalArgumentException();
        }
        n = tiles.length;
        board = new int[n][n];
        for (int col = 0; col < tiles.length; col++) {
            arraycopy(tiles[col], 0, board[col], 0, tiles.length);
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
        return 0;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return 0;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return false;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return new Iterable<Board>() {
            @Override
            public Iterator<Board> iterator() {
                return null;
            }
        };
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        return new Board(board);
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] ints = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0} // 0 represents the blank
        };
        Board board = new Board(ints);
        StdOut.println("Dimension: " + board.dimension());
        StdOut.println("Board:\n" + board);
    }
}
