package com.rohitbhoompally.shapes.algorithmics;

import android.content.Context;

import com.rohitbhoompally.shapes.shapemodels.LineSegment;
import com.rohitbhoompally.shapes.shapemodels.Shape;
import com.rohitbhoompally.shapes.shapemodels.ShapeIntersectAnswer;
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

    private Context context;

    public QAGenerator(Context context) {
        this.context = context;
    }

    /**
     * Have all possible shapes ever here
     */
    public enum ShapeType {
        LineSegment,
    }

    /**
     * Let us follow a factory pattern, as we would always need an instance of this class
     * to serve up shape maps
     * @return
     */
    private ShapeListGenerator shapeListGenerator;
    public ShapeListGenerator getShapeListGenerator(Context context) {
        if (shapeListGenerator == null) {
            shapeListGenerator = new ShapeListGenerator(context);
        }
        return shapeListGenerator;
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
        ShapeListGenerator shapeListGenerator = getShapeListGenerator(context);
        ArrayList<Shape> shapeList = shapeListGenerator.getOverlappingShapes(difficulty, currentShapeType);

        /* Calculate the answer for the overlapping shapes */
        ShapeIntersectAnswer answer = getTotalShapesAndIntersections(currentShapeType, shapeList);

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
    private ShapeIntersectAnswer getTotalShapesAndIntersections(ShapeType shapeType, ArrayList<Shape> shapes) {
        switch (shapeType) {
            case LineSegment:
                return getTotalLinesSegmentsAndIntersections((ArrayList<LineSegment>) (ArrayList<?>) shapes);
        }
        return null;
    }

    /**
     * The logic for finding out all intersecting points between all the line segments,
     * and also the total number of line segments as an answer
     * @param lineSegments
     * @return
     */
    private ShapeIntersectAnswer getTotalLinesSegmentsAndIntersections(ArrayList<LineSegment> lineSegments) {
        ShapeIntersectAnswer answer = null;

        return answer;
    }
}
