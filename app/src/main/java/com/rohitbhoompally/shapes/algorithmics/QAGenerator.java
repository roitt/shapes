package com.rohitbhoompally.shapes.algorithmics;

import android.content.res.Resources;

import com.rohitbhoompally.shapes.ShapesApplication;
import com.rohitbhoompally.shapes.shapemodels.Shape;
import com.rohitbhoompally.shapes.shapemodels.ShapeQAItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by rbhoompally on 12/2/15.
 */
public class QAGenerator {
    private static final List<ShapeType> POSSIBLE_SHAPES =
            Collections.unmodifiableList(Arrays.asList(ShapeType.values()));
    private static final int TOTAL_SHAPES = POSSIBLE_SHAPES.size();
    private static final Random RANDOMIZER = new Random();

    private Resources resources;

    /**
     * Have all possible shapes ever here
     */
    public enum ShapeType {
        LineSegment,
    }

    /**
     * Create and return a QA based on difficulty.
     *
     * @param difficulty
     * @return
     */
    public ShapeQAItem getNextQA(int difficulty) {
        /* First pick a shape type. For now we just have line segments.
         * Later on we will have multiple shapes, and we will randomize the selection and even better,
         * based on difficulty
         */
        ShapeType currentShapeType = getRandomShapeType(difficulty);

        /* Get the list of overlapping shapes */
        ShapeListGenerator shapeListGenerator = ShapesApplication.getShapeListGenerator();
        ArrayList<Shape> shapeList = shapeListGenerator.getOverlappingShapes(difficulty, currentShapeType);

        /* Calculate the answer for the overlapping shapes */
        int answer = getNumberOfOverlappingShapesInShapeList(currentShapeType, shapeList);

        return new ShapeQAItem(currentShapeType, answer, shapeList);
    }

    /**
     * Return a randomly picked shapeType each time.
     * A future enhancement can be to give weightage to difficulty in the randomizer.
     * @param difficulty
     * @return
     */
    private ShapeType getRandomShapeType(int difficulty) {
        return POSSIBLE_SHAPES.get(RANDOMIZER.nextInt(TOTAL_SHAPES));
    }

    /**
     * Calculate the correct number of overlapping shapes.
     * We hardcode it to 1 as of now.
     * @param shapeType
     * @param shapes
     * @return
     */
    private int getNumberOfOverlappingShapesInShapeList(ShapeType shapeType, ArrayList<Shape> shapes) {
        return 1;
    }
}
