package com.rohitbhoompally.shapes.shapemodels;

import com.rohitbhoompally.shapes.algorithmics.QAGenerator;

import java.util.ArrayList;

/**
 * Created by rbhoompally on 12/2/15.
 */
public class ShapeQAItem {
    private QAGenerator.ShapeType shapeType;
    private int answer;
    private ArrayList<Shape> overlappingShapes;

    public ShapeQAItem(QAGenerator.ShapeType shapeType, int answer, ArrayList<Shape> overlappingShapes) {
        this.shapeType = shapeType;
        this.answer = answer;
        this.overlappingShapes = overlappingShapes;
    }

    public QAGenerator.ShapeType getShapeType() {
        return shapeType;
    }

    public int getAnswer() {
        return answer;
    }

    public ArrayList<Shape> getOverlappingShapes() {
        return overlappingShapes;
    }
}
