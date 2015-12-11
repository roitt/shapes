package com.rohitbhoompally.shapes.activities;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rohitbhoompally.shapes.R;
import com.rohitbhoompally.shapes.algorithmics.QAGenerator;
import com.rohitbhoompally.shapes.fragments.QuestionFragment;
import com.rohitbhoompally.shapes.fragments.ShapesFragment;
import com.rohitbhoompally.shapes.interfaces.AnswerListener;
import com.rohitbhoompally.shapes.shapemodels.ShapeQAItem;

public class QAActivity extends AppCompatActivity implements AnswerListener {
    private QuestionFragment questionFragment;
    private ShapesFragment shapesFragment;

    private QAGenerator qaGenerator;
    public QAGenerator getQAGenerator(Context context) {
        if (qaGenerator == null) {
            qaGenerator = new QAGenerator(context);
        }
        return qaGenerator;
    }

    private enum ReplacementType {
        Add, Replace,
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        // Initial setup, add the shapes and question fragment
        if (savedInstanceState == null) {
            addOrReplaceFragments(ReplacementType.Add);
        }
    }

    @Override
    public void onOptionSelected(Choice selection, int answer) {

    }

    @Override
    public void onQACompleted() {
        addOrReplaceFragments(ReplacementType.Replace);
    }

    private void addOrReplaceFragments(ReplacementType type) {
        if (findViewById(R.id.questionContainer) != null &&
                findViewById(R.id.shapesContainer) != null) {

            // Get the next question
            ShapeQAItem nextItem = getNextQA(1);

            // Instantiate the fragments
            shapesFragment = ShapesFragment.newInstance(nextItem);

            questionFragment = QuestionFragment.newInstance(nextItem);

            // Add shapes fragment
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction shapesTransaction = manager.beginTransaction();
            FragmentTransaction questionTransaction = manager.beginTransaction();

            // Add transitions
            shapesTransaction.setCustomAnimations(R.anim.in_from_top, 0);
            questionTransaction.setCustomAnimations(R.anim.in_from_bottom, 0);

            if (type == ReplacementType.Add) {
                shapesTransaction.add(R.id.shapesContainer, shapesFragment);
                questionTransaction.add(R.id.questionContainer, questionFragment);
            } else {
                shapesTransaction.replace(R.id.shapesContainer, shapesFragment);
                questionTransaction.replace(R.id.questionContainer, questionFragment);
            }

            shapesTransaction.commit();
            questionTransaction.commit();
        }
    }

    private ShapeQAItem getNextQA(int difficulty) {
        return getQAGenerator(getApplicationContext()).getNextQA(difficulty);
    }
}
