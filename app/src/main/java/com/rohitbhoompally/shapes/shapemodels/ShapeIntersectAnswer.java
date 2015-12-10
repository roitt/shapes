package com.rohitbhoompally.shapes.shapemodels;

import android.graphics.PointF;

import java.util.List;

/**
 * Created by rbhoompally on 12/8/15.
 * This class encompasses the final integer answer for total number of shapes,
 * and it also gives a list of all intersecting points
 */
public class ShapeIntersectAnswer {
    private int intersectAnswer;
    private boolean isIntersecting;
    private List<PointF> intersectingPoints;

    public ShapeIntersectAnswer(int intersectAnswer, List<PointF> intersectingPoints, boolean isIntersecting) {
        this.intersectAnswer = intersectAnswer;
        this .intersectingPoints = intersectingPoints;
        this.isIntersecting = isIntersecting;
    }

    public int getIntersectAnswer() {
        return intersectAnswer;
    }

    public List<PointF> getIntersectingPoints() {
        return intersectingPoints;
    }

    public void addAnswer(int intersectAnswer) {
        this.intersectAnswer += intersectAnswer;
    }

    public void appendIntersectingPoints(PointF intersectingPoint) {
        this.intersectingPoints.add(intersectingPoint);
    }

    public boolean isIntersecting() {
        return isIntersecting;
    }
}
