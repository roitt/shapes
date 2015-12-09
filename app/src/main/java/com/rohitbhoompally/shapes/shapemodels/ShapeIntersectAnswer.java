package com.rohitbhoompally.shapes.shapemodels;

import android.graphics.PointF;

import java.util.List;

/**
 * Created by rbhoompally on 12/8/15.
 * This class encompasses the final integer answer for total number of shapes,
 * and it also gives a list of all intersecting points
 */
public class ShapeIntersectAnswer {
    private int answer;
    private List<PointF> intersectingPoints;

    public ShapeIntersectAnswer(int answer, List<PointF> intersectingPoints) {
        this.answer = answer;
        this .intersectingPoints = intersectingPoints;
    }

    public int getAnswer() {
        return answer;
    }

    public List<PointF> getIntersectingPoints() {
        return intersectingPoints;
    }
}
