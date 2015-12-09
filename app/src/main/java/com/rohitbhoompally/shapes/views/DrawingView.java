package com.rohitbhoompally.shapes.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.rohitbhoompally.shapes.R;
import com.rohitbhoompally.shapes.shapemodels.LineSegment;
import com.rohitbhoompally.shapes.shapemodels.Shape;

import java.util.ArrayList;

/**
 * Created by rbhoompally on 12/4/15.
 */
public class DrawingView extends SquareView {
    private static final int DEFAULT_COLOR_RES = R.color.white_60;
    private static final int DEFAULT_CIRCLE_RES = R.color.blue_60;

    private static final float DEFAULT_STROKE_WIDTH = 12.5f;
    private static final float DEFAULT_CIRCLE_RADIUS = 14f;

    private Paint paint;
    private Paint circlePaint;

    private ArrayList<Shape> shapes;

    public DrawingView(Context context) {
        super(context);
        init();
    }

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), DEFAULT_COLOR_RES));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DEFAULT_STROKE_WIDTH);
        paint.setAntiAlias(true);

        circlePaint = new Paint();
        circlePaint.setColor(ContextCompat.getColor(getContext(), DEFAULT_CIRCLE_RES));
        circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        circlePaint.setAntiAlias(true);
    }

    public void drawShapesOnCanvas(ArrayList<Shape> shapes) {
        this.shapes = shapes;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw the lines
        for (Shape shape : shapes) {
            if (shape instanceof LineSegment) {
                drawLineSegment(canvas, (LineSegment) shape);
            }
        }

        // Draw intersection points
        canvas.drawCircle(50f, 50f, DEFAULT_CIRCLE_RADIUS, circlePaint);
    }

    private void drawLineSegment(Canvas canvas, LineSegment lineSegment) {
        canvas.drawLine(lineSegment.getStartingPoint().x,
                lineSegment.getStartingPoint().y,
                lineSegment.getEndingPoint().x,
                lineSegment.getEndingPoint().y,
                paint);
    }
}
