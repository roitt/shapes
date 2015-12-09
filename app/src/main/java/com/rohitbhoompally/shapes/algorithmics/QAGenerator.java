package com.rohitbhoompally.shapes.algorithmics;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;

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
    private static final String TAG = QAGenerator.class.getSimpleName();

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
     * Let us follow a singleton pattern, as we would always need only one instance of this class
     * to serve up shape maps
     *
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
     *
     * @param difficulty
     * @return
     */
    private ShapeType getRandomShapeType(int difficulty) {
        return POSSIBLE_SHAPES.get(RANDOMIZER.nextInt(TOTAL_SHAPES));
    }

    /**
     * Calculate the correct number of overlapping shapes.
     * We hardcode it to 1 as of now.
     *
     * @param shapeType
     * @param shapes
     * @return
     */
    private ShapeIntersectAnswer getTotalShapesAndIntersections(ShapeType shapeType, ArrayList<Shape> shapes) {
        switch (shapeType) {
            case LineSegment:
                return getTotalLineSegmentsAndIntersections((ArrayList<LineSegment>) (ArrayList<?>) shapes);
        }
        return null;
    }

    /**
     * Find intersections between all pairs of lines in the list
     * @param lineSegments
     * @return
     */
    private ShapeIntersectAnswer getTotalLineSegmentsAndIntersections(ArrayList<LineSegment> lineSegments) {
        ShapeIntersectAnswer finalAnswer = null;
        for (int i = 0; i < lineSegments.size() - 1; i++) {
            for (int j = i + 1; j < lineSegments.size(); j++) {
                ShapeIntersectAnswer answer = getLinesSegmentsAndIntersections(lineSegments.get(i), lineSegments.get(j));

                if (finalAnswer == null) {
                    finalAnswer = answer;
                } else {
                    finalAnswer.addAnswer(answer.getAnswer());
                    finalAnswer.appendIntersectingPoints(answer.getIntersectingPoints().get(0));
                }
            }
        }
        return finalAnswer;
    }

    /**
     * The logic for finding out the intersecting point between two line segments,
     * and also the total number of line segments as an answer.
     * @param first
     * @param second
     * @return
     */
    public ShapeIntersectAnswer getLinesSegmentsAndIntersections(LineSegment first, LineSegment second) {
        int l1 = 1, l2 = 1;

        PointF f11 = first.getStartingPoint();
        float f1x1 = f11.x;
        float f1y1 = f11.y;
        PointF f12 = first.getEndingPoint();
        float f1x2 = f12.x;
        float f1y2 = f12.y;
        PointF f21 = second.getStartingPoint();
        float f2x1 = f21.x;
        float f2y1 = f21.y;
        PointF f22 = second.getEndingPoint();
        float f2x2 = f22.x;
        float f2y2 = f22.y;

        /*
          For getting the line equation:
          A1 = (Y1-Y2)/(X1-X2) // Pay attention to not dividing by zero
          A2 = (Y3-Y4)/(X3-X4) // Pay attention to not dividing by zero
          b1 = Y1-A1*X1 = Y2-A1*X2
          b2 = Y3-A2*X3 = Y4-A2*X4
        */

        float a1, a2, b1, b2;
        if (f1x1 != f1x2) {
            a1 = (f1y1 - f1y2) / (f1x1 - f1x2);
        } else {
            a1 = 0;
        }

        if (f2x1 != f2x2) {
            a2 = (f2y1 - f2y2) / (f2x1 - f2x2);
        } else {
            a2 = 0;
        }

        // Checking for parallel lines
        if (a1 == a2) {
            return null;
        }

        b1 = f1y1 - (a1 * f1x1);
        b2 = f2y1 - (a2 * f2x1);

       /*
        To find the intersection point
        Ya = A1 * Xa + b1
        Ya = A2 * Xa + b2
        A1 * Xa + b1 = A2 * Xa + b2
        Xa = (b2 - b1) / (A1 - A2) */

        float xi = -1.0f;
        float yi = -1.0f;
        if (a1 != a2) {
            xi = (b2 - b1) / (a1 - a2);
            yi = (a1 * xi) + b1;

            if ((xi == f1x1 && yi == f1y1) || (xi == f1x2 && yi == f1y2))
                l1 = 0;

            if ((xi == f2x1 && yi == f2y1) || (xi == f2x2 && yi == f2y2))
                l2 = 0;
                
                
            /* Check to see if the intersection point falls on the line
             * if ( (Xa < MAX( MIN(X1,X2), MIN(X3,X4) )) ||
             (Xa > MIN( MAX(X1,X2), MAX(X3,X4) )) )
             return false; // intersection is out of bound
             else
             return true;
             */
            if (l1 != 0 && l2 != 0) {
                if (xi < Math.max((Math.min(f1x1, f1x2)), Math.min(f2x1, f2x2)) ||
                        xi > Math.min((Math.max(f1x1, f1x2)), Math.max(f2x1, f2x2))) {
                    return null;
                }
            }
        }

        if (l1 != 0)
            l1 = l1 + 1;
        if (l2 != 0)
            l2 = l2 + 1;

        // We have an answer for intersecting lines. +2 is for 2 lines.
        Log.d(TAG, Integer.toString(2 + l1 + l2));

        // TODO: Should this just be one point ?
        List<PointF> intersections = new ArrayList<>();
        intersections.add(new PointF(xi, yi));

        return new ShapeIntersectAnswer(l1 + l2 + 2, intersections);
    }
}
