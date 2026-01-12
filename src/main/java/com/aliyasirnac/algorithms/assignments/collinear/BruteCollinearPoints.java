package com.aliyasirnac.algorithms.assignments.collinear;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private final LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        Point[] points1 = points.clone();
        Arrays.sort(points1);
        for (int i = 0; i < points1.length - 1; i++) {
            if (points1[i].compareTo(points1[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
        ArrayList<LineSegment> segments = new ArrayList<>();
        for (int i = 0; i < points1.length; i++) {
            for (int j = i + 1; j < points1.length; j++) {
                for (int k = j + 1; k < points1.length; k++) {
                    for (int l = k + 1; l < points1.length; l++) {
                        Point p = points1[i];
                        Point q = points1[j];
                        Point r = points1[k];
                        Point s = points1[l];

                        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(r) == p.slopeTo(s)) {
                            Point[] foundPoints = {p, q, r, s};
                            Arrays.sort(foundPoints);
                            LineSegment segment = new LineSegment(foundPoints[0], foundPoints[3]);
                            segments.add(segment);
                        }
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
