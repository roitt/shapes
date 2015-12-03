package com.rohitbhoompally.shapes.utils;

import com.rohitbhoompally.shapes.R;
import com.rohitbhoompally.shapes.algorithmics.QAGenerator;

/**
 * Created by rbhoompally on 12/2/15.
 */
public class QAUtil {

    public static int getResFromShapeType(QAGenerator.ShapeType type) {
        switch (type) {
            case LineSegment:
                return R.string.line_segments;
            default:
                return 0;
        }
    }
}
