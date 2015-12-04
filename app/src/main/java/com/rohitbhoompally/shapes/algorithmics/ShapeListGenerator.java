package com.rohitbhoompally.shapes.algorithmics;

import com.rohitbhoompally.shapes.shapemodels.LineSegment;
import com.rohitbhoompally.shapes.shapemodels.Shape;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by rbhoompally on 12/1/15.
 *
 * This class is responsible for handling shape generation.
 * The task is to take the the difficulty level as input and generate a map contains shapes.
 * The generated map should have all shapes of similar kind, and can overlap.
 *
 * Let us assume we have a difficulty level of 0 to 10, 0 being the easiest and 10 being the hardest.
 */
public class ShapeListGenerator {

    private static final int LOWER_BOUND = 0;
    private static final int UPPER_BOUND = 20;

    public ArrayList<Shape> getOverlappingShapes(int difficulty, QAGenerator.ShapeType shapeType) {
        ArrayList<Shape> overlappingShapes = null;

        switch (shapeType) {
            case LineSegment:
                overlappingShapes = getOverlappingLinesSegments(difficulty);
                break;
        }

        return  overlappingShapes;
    }

    private ArrayList<Shape> getOverlappingLinesSegments(int difficulty) {

    }
}
