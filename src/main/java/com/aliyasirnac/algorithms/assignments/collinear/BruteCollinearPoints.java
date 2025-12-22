package com.aliyasirnac.algorithms.assignments.collinear;

public class BruteCollinearPoints {
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        for (Point p : points) {
            for (Point q : points) {
                for (Point r : points) {
                    for (Point s : points) {
                        // code here
                    }
                }
            }
        }
    }   // finds all line segments containing 4 points

    public int numberOfSegments() {
        return 0;
    }     // the number of line segments

    public LineSegment[] segments() {
        return null;
    }

}
