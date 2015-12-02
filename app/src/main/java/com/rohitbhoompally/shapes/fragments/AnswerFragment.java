package com.rohitbhoompally.shapes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rohitbhoompally.shapes.R;
import com.rohitbhoompally.shapes.interfaces.AnswerListener;

/**
 * Created by rbhoompally on 12/2/15.
 */
public class AnswerFragment extends Fragment implements View.OnClickListener {

    private AnswerListener answerListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_answer, null);

        setOnClickListeners(rootView, R.id.A, R.id.B, R.id.C, R.id.D);

        return rootView;
    }

    private void setOnClickListeners(View rootView, int ... layouts) {
        for (int layout : layouts) {
            Button button = (Button) rootView.findViewById(layout);
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            answerListener = (AnswerListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Parent activity must implement AnswerListener");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.A:
                answerListener.onOptionSelected(AnswerListener.Choice.A);
                break;

            case R.id.B:
                answerListener.onOptionSelected(AnswerListener.Choice.B);
                break;

            case R.id.C:
                answerListener.onOptionSelected(AnswerListener.Choice.C);
                break;

            case R.id.D:
                answerListener.onOptionSelected(AnswerListener.Choice.D);
                break;
        }
    }
}
