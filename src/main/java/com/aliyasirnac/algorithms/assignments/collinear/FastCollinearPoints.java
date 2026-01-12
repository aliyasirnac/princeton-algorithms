package com.aliyasirnac.algorithms.assignments.collinear;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private final LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
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
        int n = points1.length;
        for (int p = 0; p < n; p++) {
            Point origin = points1[p];
            Point[] copyPoints = points1.clone();
            Arrays.sort(copyPoints, origin.slopeOrder());

            int i = 1;
            while (i < n) {
                double currentSlope = origin.slopeTo(copyPoints[i]);
                int j = i + 1;

                while (j < n && origin.slopeTo(copyPoints[j]) == currentSlope)
                    j++;

                if (j - i >= 3) {
                    Point maxPoint = origin;
                    Point minPoint = origin;

                    for (int k = i; k < j; k++) {
                        if (copyPoints[k].compareTo(minPoint) < 0) minPoint = copyPoints[k];
                        if (copyPoints[k].compareTo(maxPoint) > 0) maxPoint = copyPoints[k];
                    }

                    if (origin == minPoint) {
                        segments.add(new LineSegment(minPoint, maxPoint));
                    }
                }

                i = j;
            }
        }
        lineSegments = segments.toArray(new LineSegment[0]);

    } // finds all line segments containing 4 or more points

    public int numberOfSegments() {
        return lineSegments.length;
    }    // the number of line segments

    public LineSegment[] segments() {
        return lineSegments.clone();
    }      // the line segments

}
