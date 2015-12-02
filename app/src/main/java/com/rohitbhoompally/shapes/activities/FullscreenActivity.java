package com.rohitbhoompally.shapes.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rohitbhoompally.shapes.R;
import com.rohitbhoompally.shapes.fragments.QuestionFragment;
import com.rohitbhoompally.shapes.interfaces.AnswerListener;

public class FullscreenActivity extends AppCompatActivity implements AnswerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
    }

    @Override
    public void onOptionSelected(Choice selection) {
        QuestionFragment fragment = (QuestionFragment)
                getSupportFragmentManager().findFragmentById(R.id.question_fragment);
        fragment.onOptionSelected(selection);
    }
}
