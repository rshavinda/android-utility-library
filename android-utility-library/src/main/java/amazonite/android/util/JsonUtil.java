package amazonite.android.util;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class JsonUtil {
    private static final String TAG = JsonUtil.class.getSimpleName();

    /**
     * Read json file from asset and return value as string
     * @param context - application context
     * @param filePath - json file's path
     * @return file data as string format
     */
    public static String readJsonFromAsset(Context context, String filePath) {
        if (!filePath.contains(".json")) {
            filePath += ".json";
        }
        try {
            InputStream inputStream = context.getAssets().open(filePath);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            final int read = inputStream.read(buffer);
            inputStream.close();
            if (read > 0) {
                return new String(buffer, "UTF-8");
            }
        } catch (IOException ex) {
            Log.e(TAG, "loadJSONFromAsset: ", ex);
        }
        return null;
    }

    /**
     * Read json file from asset and return value as object
     * @param context - application context
     * @param filePath - json file's path
     * @param ObjType - return object type
     * @return converted object
     */
    public static <T> T getObjectFromAssetJson(Context context, String filePath, Class<T> ObjType) {
        try {
            String json = readJsonFromAsset(context, filePath);
            Object value = new Gson().fromJson(json, ObjType);
            return (T) value;
        } catch (Exception ex) {
            Log.e(TAG, "getObjectFromAssetJson: ", ex);
        }
        return null;
    }

    /**
     * Read json file from asset and return value as JSON object
     * @param context - application context
     * @param filePath - json file's path
     * @return json object
     */
    public static JSONObject loadJSONFromAsset(Context context, String filePath) {
        try {
            String json = readJsonFromAsset(context, filePath);
            if (json == null || json.isEmpty()) return null;

            return new JSONObject(json);
        } catch (JSONException ex) {
            Log.e(TAG, "loadJSONFromAsset: ", ex);
            return null;
        }
    }


    /**
     * Get String value of the JSON object's given field has
     * @param jsonObject - JSON object
     * @param fieldName - field name / key
     * @return value of the field
     */
    public static String getStringFromJson(JSONObject jsonObject, String fieldName) {
        if (jsonObject == null) return null;
        try {
            if (!jsonObject.has(fieldName)) {
                Log.e(TAG, "Field : \"" + fieldName + "\" not found in the json object");
                return null;
            }

            return jsonObject.getString(fieldName);
        } catch (JSONException ex) {
            Log.e(TAG, "getStringFromJson: ", ex);
            return null;
        }
    }

    /**
     * Get int value of the JSON object's given field has
     * @param jsonObject - JSON object
     * @param key - field name / key
     * @return value of the field
     */
    public static int getIntFromJson(JSONObject jsonObject, String key) {
        if (jsonObject == null) return -1;
        try {
            if (!jsonObject.has(key)) {
                Log.e(TAG, "Field : \"" + key + "\" not found in the json object");
                return -1;
            }

            return jsonObject.getInt(key);
        } catch (JSONException ex) {
            Log.e(TAG, "getIntFromJson: ", ex);
            return -1;
        }
    }

    /**
     * Get double value of the JSON object's given field has
     * @param jsonObject - JSON object
     * @param key - field name / key
     * @return value of the field
     */
    public static double getDoubleFromJson(JSONObject jsonObject, String key) {
        if (jsonObject == null) return -1d;
        try {
            if (!jsonObject.has(key)) {
                Log.e(TAG, "Field : \"" + key + "\" not found in the json object");
                return -1d;
            }

            return jsonObject.getDouble(key);
        } catch (JSONException ex) {
            Log.e(TAG, "getDoubleFromJson: ", ex);
            return -1d;
        }
    }

    /**
     * Get boolean value of the JSON object's given field has
     * @param jsonObject - JSON object
     * @param key - field name / key
     * @return value of the field
     */
    public static boolean getBooleanFromJson(JSONObject jsonObject, String key) {
        if (jsonObject == null) return false;
        try {
            if (!jsonObject.has(key)) {
                Log.e(TAG, "Field : \"" + key + "\" not found in the json object");
                return false;
            }

            return jsonObject.getBoolean(key);
        } catch (JSONException ex) {
            Log.e(TAG, "getDoubleFromJson: ", ex);
            return false;
        }
    }
}