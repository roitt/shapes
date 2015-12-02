package com.rohitbhoompally.shapes.shapes;

import android.graphics.Point;

/**
 * Created by rbhoompally on 12/1/15.
 *
 * A line segment has a starting point and and ending point, and a line connecting those points.
 */
public class LineSegment {
    private Point startingPoint;
    private Point endingPoint;

    public LineSegment(Point startingPoint, Point endingPoint) {
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
    }

    public Point getStartingPoint() {
        return startingPoint;
    }

    public Point getEndingPoint() {
        return endingPoint;
    }
}
