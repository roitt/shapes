package com.rohitbhoompally.shapes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rohitbhoompally.shapes.R;
import com.rohitbhoompally.shapes.algorithmics.QAGenerator;
import com.rohitbhoompally.shapes.interfaces.AnswerListener;
import com.rohitbhoompally.shapes.utils.QAUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by rbhoompally on 12/2/15.
 */
public class QuestionFragment extends Fragment implements View.OnClickListener {

    private AnswerListener answerListener;
    private TextView questionView;

    /* Options */
    private Button A;
    private Button B;
    private Button C;
    private Button D;

    private Random randomizer = new Random();
    private List<Integer> options;
    private int answer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_question, null);

        A = (Button) rootView.findViewById(R.id.A);
        B = (Button) rootView.findViewById(R.id.B);
        C = (Button) rootView.findViewById(R.id.C);
        D = (Button) rootView.findViewById(R.id.D);

        setOnClickListeners(A, B, C, D);
        questionView = (TextView) rootView.findViewById(R.id.question);

        return rootView;
    }

    private void setOnClickListeners(Button... buttons) {
        for (Button button : buttons) {
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
        int givenAnswer = 0;
        switch (v.getId()) {
            case R.id.A:
                onAnswerChosen(AnswerListener.Choice.A, options.get(0));
                givenAnswer = options.get(0);
                answerListener.onOptionSelected(AnswerListener.Choice.A, givenAnswer);
                verifyAnswer(givenAnswer);
                break;

            case R.id.B:
                onAnswerChosen(AnswerListener.Choice.B, options.get(1));
                break;

            case R.id.C:
                onAnswerChosen(AnswerListener.Choice.C, options.get(2));
                break;

            case R.id.D:
                onAnswerChosen(AnswerListener.Choice.D, options.get(3));
                break;
        }
    }

    private void onAnswerChosen(AnswerListener.Choice choice, int value) {
        answerListener.onOptionSelected(choice, value);
        verifyAnswer(value);
    }

    private void verifyAnswer(int givenAnswer) {
        if (answer == givenAnswer) {
            Toast.makeText(getContext(), "Right", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public void setQuestion(QAGenerator.ShapeType shapeType) {
        if (questionView != null) {
            String question = getString(R.string.how_many_items_do_you_see,
                    getString(QAUtil.getResFromShapeType(shapeType)));
            questionView.setText(question);
        }
    }

    public void setOptions(int answer) {
        this.answer = answer;

        options = new ArrayList<>();
        options.add(answer);

        int min = answer - 5;
        if (min < 2) {
            min = 2;
        }

        int max = answer + 5;

        // Generate 3 other options close to answer. +/- 5
        while (options.size() != 4) {
            int newOption = randInt(min, max);
            if (!options.contains(newOption) && newOption != answer) {
                options.add(newOption);
            }
        }

        // We have four options of which one is the correct answer, and is at 0th position
        // Shuffle them up
        Collections.shuffle(options);
        setValuesToButtons(options);
    }

    private void setValuesToButtons(List<Integer> options) {
        A.setText(Integer.toString(options.get(0)));
        B.setText(Integer.toString(options.get(1)));
        C.setText(Integer.toString(options.get(2)));
        D.setText(Integer.toString(options.get(3)));
    }

    private int randInt(int min, int max) {
        return randomizer.nextInt((max - min) + 1) + min;
    }
}
