package edu.umsl.hester.sqlproject.app;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Austin on 3/18/2017.
 *
 */

public class ModelHolder {

    public static final String FRIEND_MODEL = "FriendModel";
    private Map<String, WeakReference<Object>> modelData = new HashMap<>();

    private static final ModelHolder holder = new ModelHolder();

    public static ModelHolder getInstance() {
        return holder;
    }

    public void saveModel(String key, Object model) {
        modelData.put(key, new WeakReference<>(model));
    }

    public Object getModel(String modelKey) {
        WeakReference<Object> weakObject = modelData.get(modelKey);
        if (weakObject != null) {
            return weakObject.get();
        }
        return null;
    }

}
