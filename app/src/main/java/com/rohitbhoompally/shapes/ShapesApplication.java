package com.rohitbhoompally.shapes;

import android.app.Application;

import com.rohitbhoompally.shapes.algorithmics.ShapeListGenerator;

import de.greenrobot.event.EventBus;

/**
 * Created by rbhoompally on 12/2/15.
 */
public class ShapesApplication extends Application {
    public final static String SHARED_PREFS_NAME = "com.rohitbhoompally.shapes.prefs";

    /**
     * Let us follow a factory pattern, as we would always need an instance of this class
     * to serve up shape maps
     * @return
     */
    private static ShapeListGenerator shapeListGenerator;
    public static ShapeListGenerator getShapeListGenerator() {
        if (shapeListGenerator == null) {
            shapeListGenerator = new ShapeListGenerator();
        }
        return shapeListGenerator;
    }

    private EventBus eventBus;
    public EventBus getEventBus() {
        if (eventBus == null) {
            eventBus = EventBus.getDefault();
        }
        return eventBus;
    }
}
