package com.rohitbhoompally.shapes.algorithmics;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;

import com.rohitbhoompally.shapes.R;
import com.rohitbhoompally.shapes.shapemodels.LineSegment;
import com.rohitbhoompally.shapes.shapemodels.Shape;
import com.rohitbhoompally.shapes.utils.DeviceUtil;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

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
    private static final int UPPER_BOUND = 320;

    private static final Random RANDOMIZER = new Random();

    private int maxShapeLength = UPPER_BOUND;

    public ShapeListGenerator(Context context) {
        init(context);
    }

    private void init(Context context) {
        int deviceWidth = DeviceUtil.getDisplayWidth(context);
        int shapeViewWidth  = deviceWidth - (2 * context.getResources().getDimensionPixelSize(R.dimen.standard_margin));
        maxShapeLength = shapeViewWidth;
    }

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
        ArrayList<Shape> shapes = new ArrayList<>();
        for (int i = 0; i < difficulty + 1; i++) {
            shapes.add(getRandomLine());
        }
        return shapes;
    }

    private LineSegment getRandomLine() {
        PointF from = new PointF(RANDOMIZER.nextInt(maxShapeLength), RANDOMIZER.nextInt(maxShapeLength));
        PointF to = new PointF(RANDOMIZER.nextInt(maxShapeLength), RANDOMIZER.nextInt(maxShapeLength));
        return new LineSegment(from, to);
    }
}
