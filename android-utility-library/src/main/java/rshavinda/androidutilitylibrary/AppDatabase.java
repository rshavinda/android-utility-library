package rshavinda.androidutilitylibrary;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AppDatabase {
    private static final String TAG = AppDatabase.class.getSimpleName();
    private static final String PREFS_NAME = "app";
    private static final int MODE_PRIVATE = 0;
    private SharedPreferences mPreferences;

    public AppDatabase(Context context) {
        mPreferences = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
    }

    /**
     * Put int value into SharedPreferences with 'key' and save
     * @param key SharedPreferences key
     * @param intValue int value to be added
     */
    public boolean put(String key, int intValue) {
        checkForEmptyValues(key);
        return mPreferences.edit().putInt(key, intValue).commit();
    }

    /**
     * Put long value into SharedPreferences with 'key' and save
     * @param key SharedPreferences key
     * @param longValue long value to be added
     */
    public boolean put(String key, long longValue) {
        checkForEmptyValues(key);
        return mPreferences.edit().putLong(key, longValue).commit();
    }

    /**
     * Put float value into SharedPreferences with 'key' and save
     * @param key SharedPreferences key
     * @param floatValue float value to be added
     */
    public boolean put(String key, float floatValue) {
        checkForEmptyValues(key);
        return mPreferences.edit().putFloat(key, floatValue).commit();
    }

    /**
     * Put String value into SharedPreferences with 'key' and save
     * @param key SharedPreferences key
     * @param stringValue String value to be added
     */
    public boolean put(String key, String stringValue) {
        checkForEmptyValues(key);
        return mPreferences.edit().putString(key, stringValue).commit();
    }

    /**
     * Put boolean value into SharedPreferences with 'key' and save
     * @param key SharedPreferences key
     * @param booleanValue boolean value to be added
     */
    public boolean put(String key, boolean booleanValue) {
        checkForEmptyValues(key);
        return mPreferences.edit().putBoolean(key, booleanValue).commit();
    }



    /**
     * @param key SharedPreferences key
     * @return int value at 'key' or -1 if key not found
     */
    public int getInt(String key) {
        checkForEmptyValues(key);
        return mPreferences.getInt(key, -1);
    }

    /**
     * @param key SharedPreferences key
     * @return long value at 'key' or -1 if key not found
     */
    public long getLong(String key) {
        checkForEmptyValues(key);
        return mPreferences.getLong(key, -1);
    }

    /**
     * @param key SharedPreferences key
     * @return float value at 'key' or -1 if key not found
     */
    public Float getFloat(String key) {
        checkForEmptyValues(key);
        return mPreferences.getFloat(key, -1);
    }

    /**
     * @param key SharedPreferences key
     * @return String value at 'key' or null if key not found
     */
    public String getString(String key) {
        checkForEmptyValues(key);
        return mPreferences.getString(key, null);
    }

    /**
     * @param key SharedPreferences key
     * @return boolean value at 'key' or false if key not found
     */
    public Boolean getBoolean(String key) {
        checkForEmptyValues(key);
        return mPreferences.getBoolean(key, false);
    }


    /**
     * Get parsed Object from SharedPreferences at 'key'
     * @param key SharedPreferences key
     * @param classOfT Object type
     */
    public <T> T getObject(String key, Class<T> classOfT) {
        String json = getString(key);
        Object value = new Gson().fromJson(json, classOfT);
        return (T) value;
    }

    /**
     * Put any Object type into SharedPreferences with 'key' and save
     * @param key SharedPreferences key
     * @param obj Object to be added
     */
    public boolean putObject(String key, Object obj) {
        if(key == null || key.isEmpty()) return false;
        Gson gson = new Gson();
        put(key, gson.toJson(obj));
        return true;
    }



    /**
     * Get parsed ArrayList of String from SharedPreferences at 'key'
     * @param key SharedPreferences key
     * @return ArrayList of String
     */
    public ArrayList<String> getStringList(String key) {
        try {
            String json = getString(key);
            return new Gson().fromJson(json, new TypeToken<List<String>>(){}.getType());
        }
        catch (Exception ex){
            Log.e(TAG, "getStringList: ", ex);
            return new ArrayList<String>();
        }
    }

    /**
     * Put ArrayList of String into SharedPreferences with 'key' and save
     * @param key SharedPreferences key
     * @param stringList ArrayList of String to be added
     */
    public boolean putStringList(String key, List<String> stringList) {
        if(key == null || key.isEmpty()) return false;
        try {
            Gson gson = new Gson();
            put(key, gson.toJson(stringList));
            return true;
        }
        catch (Exception ex){
            Log.e(TAG, "getStringList: ", ex);
            return false;
        }
    }


    /**
     * Get parsed ArrayList of Objects from SharedPreferences at 'key'
     * @param key SharedPreferences key
     * @return ArrayList of Objects
     */
    public ArrayList<Object> getObjectList(String key, Class<?> mClass) {
        Gson gson = new Gson();

        ArrayList<String> objStrings = getStringList(key);
        ArrayList<Object> objects = new ArrayList<Object>();

        for (String jObjString : objStrings) {
            Object value = gson.fromJson(jObjString, mClass);
            objects.add(value);
        }
        return objects;
    }

    /**
     * Put ArrayList of Object into SharedPreferences with 'key' and save
     * @param key SharedPreferences key
     * @param objArray ArrayList of Object to be added
     */
    public void putListObject(String key, ArrayList<Object> objArray) {
        checkForEmptyValues(key);
        Gson gson = new Gson();
        ArrayList<String> objStrings = new ArrayList<String>();
        for (Object obj : objArray) {
            objStrings.add(gson.toJson(obj));
        }
        putStringList(key, objStrings);
    }


    /**
     * Remove SharedPreferences item with 'key'
     * @param key SharedPreferences key
     */
    public boolean removeValue(String key) {
        checkForEmptyValues(key);
        return mPreferences.edit().remove(key).commit();
    }


    /**
     * Clear SharedPreferences (remove everything)
     */
    public void clearAll() {
        mPreferences.edit().clear().apply();
    }

    /**
     * Retrieve all values from SharedPreferences. Do not modify collection return by method
     * @return a Map representing a list of key/value pairs from SharedPreferences
     */
    public Map<String, ?> getAll() {
        return mPreferences.getAll();
    }

    /**
     * null keys would corrupt the shared pref file and make them unreadable this is a preventive measure
     * @param value the pref value to check
     */
    private static void checkForEmptyValues(String value) {
        if (value == null || value.isEmpty()) {
            throw new NullPointerException();
        }
    }
}