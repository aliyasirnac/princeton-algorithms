package com.aliyasirnac.algorithms.assignments.collinear;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private final LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<LineSegment> segments = new ArrayList<>();
        int n = points.length;
        for (Point p : points) {
            Point[] copyPoints = points.clone();
            Arrays.sort(copyPoints, p.slopeOrder());

            int j = 1, count = 0;
            while (j < n) {
                double currentSlope = p.slopeTo(copyPoints[j]);
                Point maxPoint = copyPoints[j];
                boolean pIsSmall = true;
                if (p.compareTo(copyPoints[j]) > 0)
                    pIsSmall = false;
                j++;

                while (j < n && Double.compare(p.slopeTo(copyPoints[j]), currentSlope) == 0) {
                    Point q = copyPoints[j];
                    count++;
                    if (q.compareTo(maxPoint) > 0) {
                        maxPoint = q;
                    }
                    if (p.compareTo(q) > 0)
                        pIsSmall = false;
                    j++;
                }

                if (count >= 3) {
                    if (pIsSmall) {
                        segments.add(new LineSegment(p, maxPoint));
                    }
                }

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
