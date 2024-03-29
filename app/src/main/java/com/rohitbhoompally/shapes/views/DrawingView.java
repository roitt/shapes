package com.rohitbhoompally.shapes.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.rohitbhoompally.shapes.R;
import com.rohitbhoompally.shapes.shapemodels.LineSegment;
import com.rohitbhoompally.shapes.shapemodels.Shape;
import com.rohitbhoompally.shapes.shapemodels.ShapeIntersectAnswer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbhoompally on 12/4/15.
 */
public class DrawingView extends SquareView {
    private static final int DEFAULT_COLOR_RES = R.color.white_60;
    private static final int DEFAULT_CIRCLE_RES = R.color.blue_75;

    private static final float DEFAULT_STROKE_WIDTH = 12.5f;
    private static final float DEFAULT_CIRCLE_RADIUS = 14f;

    private Paint paint;
    private Paint circlePaint;

    private ArrayList<Shape> shapes;
    private ShapeIntersectAnswer answer;

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
        paint.setDither(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);

        circlePaint = new Paint();
        circlePaint.setColor(ContextCompat.getColor(getContext(), DEFAULT_CIRCLE_RES));
        circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setDither(true);
        circlePaint.setAntiAlias(true);
    }

    public void drawShapesOnCanvas(ArrayList<Shape> shapes, ShapeIntersectAnswer answer) {
        this.shapes = shapes;
        this.answer = answer;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (shapes == null) {
            return;
        }

        // Draw the lines
        for (Shape shape : shapes) {
            if (shape instanceof LineSegment) {
                drawLineSegment(canvas, (LineSegment) shape);
            }
        }

        // Draw intersection points
        if (answer != null && answer.isIntersecting()) {
            List<PointF> intersections = answer.getIntersectingPoints();

            for (PointF pointF : intersections) {
                canvas.drawCircle(pointF.x, pointF.y, DEFAULT_CIRCLE_RADIUS, circlePaint);
            }
        }
    }

    private void drawLineSegment(Canvas canvas, LineSegment lineSegment) {
        canvas.drawLine(lineSegment.getStartingPoint().x,
                lineSegment.getStartingPoint().y,
                lineSegment.getEndingPoint().x,
                lineSegment.getEndingPoint().y,
                paint);

        // Draw starting and ending points for the lines
        canvas.drawCircle(lineSegment.getStartingPoint().x, lineSegment.getStartingPoint().y,
                DEFAULT_CIRCLE_RADIUS, circlePaint);
        canvas.drawCircle(lineSegment.getEndingPoint().x, lineSegment.getEndingPoint().y,
                DEFAULT_CIRCLE_RADIUS, circlePaint);
    }
}
