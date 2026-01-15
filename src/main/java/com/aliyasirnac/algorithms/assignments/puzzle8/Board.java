package com.aliyasirnac.algorithms.assignments.puzzle8;

import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

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
        return Objects.equals(toString(), y.toString());
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return new Iterable<Board>() {
            @Override
            public Iterator<Board> iterator() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        throw new UnsupportedOperationException();
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
    }
}
