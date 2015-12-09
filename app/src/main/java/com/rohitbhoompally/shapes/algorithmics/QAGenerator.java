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
     * Let us follow a singleton pattern, as we would always need only one instance of this class
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

        LineSegment first = lineSegments.get(0);
        LineSegment sec = lineSegments.get(1);

        int l1 = 1, l2 = 1;

        android.graphics.PointF f11 = first.getStartingPoint();
        float f1x1 = f11.x;
        float f1y1 = f11.y;
        android.graphics.PointF f12 = first.getEndingPoint();
        float f1x2 = f12.x;
        float f1y2 = f12.y;
        android.graphics.PointF f21 = sec.getStartingPoint();
        float f2x1 = f11.x;
        float f2y1 = f11.y;
        android.graphics.PointF f22 = sec.getEndingPoint();
        float f2x2 = f12.x;
        float f2y2 = f12.y;

        /*
          For getting the line equation:
          A1 = (Y1-Y2)/(X1-X2) // Pay attention to not dividing by zero
          A2 = (Y3-Y4)/(X3-X4) // Pay attention to not dividing by zero
          b1 = Y1-A1*X1 = Y2-A1*X2
          b2 = Y3-A2*X3 = Y4-A2*X4
        */

        float a1, a2, b1, b2;
        if(f1x1 != f1x2)
            a1 = (f1y1 - f1y2)/(f1x1 - f1x2);
        else a1 = 0;

        if(f2x1 != f2x2)
            a2 = (f2y1 - f2y2)/(f2x1 - f2x2);
        else a2 = 0;

        // parellel lines
        if(a1 == a2)
            l1 = l2 = 0;

        b1 = f1y1 - (a1 * f1x1);
        b2 = f2y1 - (a2 * f2x1);

       /*
        To find the intersection point
        Ya = A1 * Xa + b1
        Ya = A2 * Xa + b2
        A1 * Xa + b1 = A2 * Xa + b2
        Xa = (b2 - b1) / (A1 - A2) */

        if(a1 != a2) {
            float xi = (b2 - b1) / (a1- a2);
            float yi = (a1 * xi) + b1;

            if((xi == f1x1 && yi == f1y1) || (xi == f1x2 && yi == f1y2))
                l1 = 0;

            if((xi == f2x1 && yi == f2y1) || (xi == f2x2 && yi == f2y2))
                l2 = 0;
        }

        if(l1 != 0)
            l1 = l1+1;
        if(l2 != 0)
            l2 = l2+1;


        System.out.println(2 + l1 + l2);

        return answer;
    }
    
}
