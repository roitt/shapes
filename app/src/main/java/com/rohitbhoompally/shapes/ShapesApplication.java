package com.rohitbhoompally.shapes;

import android.app.Application;

import com.rohitbhoompally.shapes.algorithmics.ShapeListGenerator;

import de.greenrobot.event.EventBus;

/**
 * Created by rbhoompally on 12/2/15.
 */
public class ShapesApplication extends Application {
    public final static String SHARED_PREFS_NAME = "com.rohitbhoompally.shapes.prefs";

    private EventBus eventBus;
    public EventBus getEventBus() {
        if (eventBus == null) {
            eventBus = EventBus.getDefault();
        }
        return eventBus;
    }
}
