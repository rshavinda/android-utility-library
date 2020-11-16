package amazonite.android.util;

import android.util.Log;
import android.widget.EditText;

import java.util.Locale;

public class TextUtil {
    private static final String TAG = TextUtil.class.getSimpleName();

    /**
     * @param stringValue - string value
     * @return true - not empty | false - null or empty
     */
    public static boolean isEmptyString(String stringValue) {
        return stringValue == null || stringValue.trim().isEmpty();
    }

    /**
     * Capitalize First Letter of the sentence
     *
     * @param text - string value
     * @return formatted text
     */
    public static String capitalizeFirstLetter(String text) {
        try {
            if (text == null || text.trim().isEmpty()) return "";
            text = text.trim();
            return (text.length() > 1) ? (text.substring(0, 1).toUpperCase(Locale.US)) + (text.substring(1).toLowerCase(Locale.US)) : text.toUpperCase();
        } catch (Exception e) {
            return text;
        }
    }

    /**
     * Capitalize First Letter of every word in the text
     *
     * @param text - string value
     * @return formatted text
     */
    public static String toTitleCaseEveryWord(String text) {
        try {
            if (text == null || text.trim().isEmpty()) return "";

            String[] words = text.trim().split(" ");
            StringBuilder result = new StringBuilder();
            for (String word : words) {
                result.append((word.length() > 1) ? (word.substring(0, 1).toUpperCase(Locale.US)) + (word.substring(1).toLowerCase(Locale.US)) : word.toUpperCase()).append(" ");
            }
            return result.toString().trim();
        } catch (Exception e) {
            return text;
        }
    }

    /**
     * @param stringValue - string value
     * @return string value which is not null
     */
    public static String getStringValue(String stringValue) {
        return (stringValue == null || stringValue.trim().isEmpty()) ? "" : stringValue.trim();
    }

    /**
     * Get int from string variable
     *
     * @param string - string value
     * @return int - int value
     */
    public static int getIntValue(String string) {
        if (getStringValue(string).isEmpty()) return 0;

        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            Log.e(TAG, "getIntValue: ", e);
            return 0;
        }
    }

    /**
     * Get float from string variable
     *
     * @param string - string value
     * @return float - float value
     */
    public static float getFloatValue(String string) {
        if (getStringValue(string).isEmpty()) return 0;

        try {
            return Float.parseFloat(string);
        } catch (NumberFormatException e) {
            Log.e(TAG, "getFloatValue: ", e);
            return 0;
        }
    }

    /**
     * Get double from string variable
     *
     * @param string - string value
     * @return double - double value
     */
    public static double getDoubleValue(String string) {
        if (getStringValue(string).isEmpty()) return 0;

        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {
            Log.e(TAG, "getDoubleValue: ", e);
            return 0;
        }
    }

    /**
     * Get String from EditText variable
     *
     * @param editText - EditText variable
     * @return String value
     */
    public static String getStringValue(EditText editText) {
        return (editText == null ? "" : getStringValue(editText.getText().toString()));
    }

    /**
     * Get int from EditText variable
     *
     * @param editText - EditText variable
     * @return int value
     */
    public static int getIntValue(EditText editText) {
        return (editText == null ? 0 : getIntValue(editText.getText().toString()));
    }

    /**
     * Get float from EditText variable
     *
     * @param editText - EditText variable
     * @return float value
     */
    public static float getFloatValue(EditText editText) {
        return (editText == null ? 0 : getFloatValue(editText.getText().toString()));
    }

    /**
     * Get double from EditText variable
     *
     * @param editText - EditText variable
     * @return double value
     */
    public static double getDoubleValue(EditText editText) {
        return (editText == null ? 0 : getDoubleValue(editText.getText().toString()));
    }
}