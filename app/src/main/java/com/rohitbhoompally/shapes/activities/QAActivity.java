package com.rohitbhoompally.shapes.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rohitbhoompally.shapes.R;
import com.rohitbhoompally.shapes.algorithmics.QAGenerator;
import com.rohitbhoompally.shapes.fragments.QuestionFragment;
import com.rohitbhoompally.shapes.interfaces.AnswerListener;
import com.rohitbhoompally.shapes.shapemodels.ShapeQAItem;

public class QAActivity extends AppCompatActivity implements AnswerListener {
    private QAGenerator qaGenerator;

    public QAActivity() {
        qaGenerator = new QAGenerator();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        /* Serve up a question if there is not already one on the screen */
        serveUpNextQA();
    }

    @Override
    public void onOptionSelected(Choice selection) {
        QuestionFragment fragment = (QuestionFragment)
                getSupportFragmentManager().findFragmentById(R.id.question_fragment);
        fragment.onOptionSelected(selection);
    }

    private void serveUpNextQA() {
        ShapeQAItem nextItem = qaGenerator.getNextQA(1);

    }
}
