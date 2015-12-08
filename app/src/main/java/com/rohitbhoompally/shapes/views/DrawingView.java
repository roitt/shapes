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
    private static final float DEFAULT_STROKE_WIDTH = 12.5f;

    private Paint paint;

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
    }

    public void drawShapesOnCanvas(ArrayList<Shape> shapes) {
        this.shapes = shapes;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Shape shape : shapes) {
            if (shape instanceof LineSegment) {
                drawLineSegment(canvas, (LineSegment) shape);
            }
        }
    }

    private void drawLineSegment(Canvas canvas, LineSegment lineSegment) {
        canvas.drawLine(lineSegment.getStartingPoint().x,
                lineSegment.getStartingPoint().y,
                lineSegment.getEndingPoint().x,
                lineSegment.getEndingPoint().y,
                paint);
    }
}
