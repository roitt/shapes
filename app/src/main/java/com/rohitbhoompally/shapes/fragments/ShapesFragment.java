package com.rohitbhoompally.shapes.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rohitbhoompally.shapes.R;
import com.rohitbhoompally.shapes.interfaces.AnswerListener;
import com.rohitbhoompally.shapes.shapemodels.Shape;

import java.util.ArrayList;

/**
 * Created by rbhoompally on 12/2/15.
 */
public class ShapesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shapes, null);
    }

    public void onOptionSelected(AnswerListener.Choice selection) {
        Toast.makeText(getActivity(), selection.name(), Toast.LENGTH_SHORT).show();
    }

    public void drawShapes(ArrayList<Shape> shapeList) {
        // TODO: Draw shapes on a canvas.
    }
}
