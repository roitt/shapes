package com.rohitbhoompally.shapes.activities;

import android.content.Context;
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

    private ShapeQAItem currentQuestion;

    private QAGenerator qaGenerator;
    public QAGenerator getQAGenerator(Context context) {
        if (qaGenerator == null) {
            qaGenerator = new QAGenerator(context);
        }
        return qaGenerator;
    }

    private QuestionFragment getQuestionFragment() {
        if (questionFragment == null) {
            questionFragment = (QuestionFragment)
                    getSupportFragmentManager().findFragmentById(R.id.question_fragment);
        }
        return questionFragment;
    }

    private ShapesFragment getShapesFragment() {
        if (shapesFragment == null) {
            shapesFragment = (ShapesFragment)
                    getSupportFragmentManager().findFragmentById(R.id.shapes_fragment);
        }
        return shapesFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        /* Serve up a question if there is not already one on the screen */
        serveUpNextQA();
    }

    @Override
    public void onOptionSelected(Choice selection, int answer) {

    }

    private void serveUpNextQA() {
        ShapeQAItem nextItem = getQAGenerator(getApplicationContext()).getNextQA(1);

        // Pass the question string to question fragment
        getQuestionFragment().setQuestion(nextItem.getShapeType());
        getQuestionFragment().setOptions(nextItem.getAnswer().getIntersectAnswer());

        // Pass the shape list to shapes fragment
        getShapesFragment().drawShapes(nextItem.getOverlappingShapes(), nextItem.getAnswer());

        currentQuestion = nextItem;
    }
}
