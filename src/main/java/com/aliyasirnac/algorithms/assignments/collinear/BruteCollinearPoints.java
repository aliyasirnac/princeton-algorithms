package com.aliyasirnac.algorithms.assignments.collinear;

import java.util.ArrayList;

public class BruteCollinearPoints {
    private final LineSegment[] lineSegments;
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<LineSegment> segments = new ArrayList<>();
        for (Point p : points) {
            for (Point q : points) {
                for (Point r : points) {
                    for (Point s : points) {
                        // code here
                    }
                }
            }
        }
        lineSegments = segments.toArray(new LineSegment[0]);
    }   // finds all line segments containing 4 points

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments.clone();
    }

}
