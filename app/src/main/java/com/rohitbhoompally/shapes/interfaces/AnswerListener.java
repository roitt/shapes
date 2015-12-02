package com.rohitbhoompally.shapes.interfaces;

/**
 * Created by rbhoompally on 12/2/15.
 */
public interface AnswerListener {
    enum Choice {A, B, C, D};

    void onOptionSelected(Choice selection);
}
