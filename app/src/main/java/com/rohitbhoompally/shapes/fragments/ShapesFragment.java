package com.rohitbhoompally.shapes.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rohitbhoompally.shapes.R;
import com.rohitbhoompally.shapes.shapemodels.Shape;
import com.rohitbhoompally.shapes.shapemodels.ShapeIntersectAnswer;
import com.rohitbhoompally.shapes.views.DrawingView;

import java.util.ArrayList;

/**
 * Created by rbhoompally on 12/2/15.
 */
public class ShapesFragment extends Fragment {

    private DrawingView drawingView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shapes, null);
        drawingView = (DrawingView) rootView.findViewById(R.id.drawing_canvas);
        return rootView;
    }

    public void drawShapes(ArrayList<Shape> shapeList, ShapeIntersectAnswer answer) {
        if (drawingView != null) {
            drawingView.drawShapesOnCanvas(shapeList, answer);
        }
    }
}
