package com.rohitbhoompally.shapes.shapemodels;

import com.rohitbhoompally.shapes.algorithmics.QAGenerator;

import java.util.ArrayList;

/**
 * Created by rbhoompally on 12/2/15.
 */
public class ShapeQAItem {
    private QAGenerator.ShapeType shapeType;
    private ShapeIntersectAnswer answer;
    private ArrayList<Shape> overlappingShapes;

    public ShapeQAItem(QAGenerator.ShapeType shapeType, ShapeIntersectAnswer answer, ArrayList<Shape> overlappingShapes) {
        this.shapeType = shapeType;
        this.answer = answer;
        this.overlappingShapes = overlappingShapes;
    }

    public QAGenerator.ShapeType getShapeType() {
        return shapeType;
    }

    public ShapeIntersectAnswer getAnswer() {
        return answer;
    }

    public ArrayList<Shape> getOverlappingShapes() {
        return overlappingShapes;
    }
}
