package com.rohitbhoompally.shapes.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by rbhoompally on 12/4/15.
 */
public class SquareView extends View {

    public SquareView(Context context) {
        super(context);
    }

    public SquareView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int min = Math.min(width, height);
        super.onMeasure(
                MeasureSpec.makeMeasureSpec(min, MeasureSpec.getMode(widthMeasureSpec)),
                MeasureSpec.makeMeasureSpec(min, MeasureSpec.getMode(heightMeasureSpec))
        );
    }
}