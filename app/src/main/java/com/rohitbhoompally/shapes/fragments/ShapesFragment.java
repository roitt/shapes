package com.rohitbhoompally.shapes.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rohitbhoompally.shapes.R;
import com.rohitbhoompally.shapes.shapemodels.ShapeQAItem;
import com.rohitbhoompally.shapes.views.DrawingView;

/**
 * Created by rbhoompally on 12/2/15.
 */
public class ShapesFragment extends Fragment {

    private DrawingView drawingView;
    private static ShapeQAItem currentQAItem;

    public static ShapesFragment newInstance(ShapeQAItem nextItem) {
        ShapesFragment fragment = new ShapesFragment();
        currentQAItem = nextItem;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shapes, null);
        drawingView = (DrawingView) rootView.findViewById(R.id.drawing_canvas);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (drawingView != null && currentQAItem != null) {
            drawingView.drawShapesOnCanvas(currentQAItem.getOverlappingShapes(),
                    currentQAItem.getAnswer());
        }
    }
}
