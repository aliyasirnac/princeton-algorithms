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
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            Point[] copyPoints = points.clone();
            Arrays.sort(copyPoints, p.slopeOrder());

            // buradan sonra ne yapacağımı bilmiyorum
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
