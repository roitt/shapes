package com.rohitbhoompally.shapes.shapemodels;

import android.graphics.PointF;

/**
 * Created by rbhoompally on 12/1/15.
 *
 * A line segment has a starting point and and ending point, and a line connecting those points.
 */
public class LineSegment extends Shape {
    private PointF startingPoint;
    private PointF endingPoint;

    public LineSegment(PointF startingPoint, PointF endingPoint) {
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
    }

    public PointF getStartingPoint() {
        return startingPoint;
    }

    public PointF getEndingPoint() {
        return endingPoint;
    }
}
