package com.rohitbhoompally.shapes;

import android.graphics.PointF;
import android.test.AndroidTestCase;

import com.rohitbhoompally.shapes.algorithmics.QAGenerator;
import com.rohitbhoompally.shapes.shapemodels.LineSegment;
import com.rohitbhoompally.shapes.shapemodels.ShapeIntersectAnswer;


/**
 * Created by rbhoompally on 12/9/15.
 */
public class QAGeneratorTestCase extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testTwoIntersectingLineSegments() {
        QAGenerator qaGenerator = new QAGenerator(getContext());

        // Parallel line segments
        LineSegment one = new LineSegment(new PointF(1f, 1f), new PointF(10f, 1f));
        LineSegment two = new LineSegment(new PointF(1f, 2f), new PointF(10f, 2f));

        ShapeIntersectAnswer answer = qaGenerator.getLinesSegmentsAndIntersections(one, two);
        assertNull(answer);

        // Intersecting lines in between
        one = new LineSegment(new PointF(1f, 5f), new PointF(10f, 5f));
        two = new LineSegment(new PointF(6f, 1f), new PointF(7f, 10f));

        answer = qaGenerator.getLinesSegmentsAndIntersections(one, two);
        assertNotNull(answer);
        assertEquals(answer.getAnswer(), 6);
    }
}
