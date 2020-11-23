package amazonite.android.util;

import android.util.Log;
import android.widget.EditText;
import java.util.regex.Pattern;

public class Validator {
    private static final String TAG = Validator.class.getSimpleName();
    // \w = [a-zA-Z_0-9] || \d = [0-9] || \s = whitespace[\t\n\x0B\f\r] || \Q : Quote all characters up to \E
    private static final String FORMAT_USER_NAME = "[\\w_ -'.,@]+";
    private static final String FORMAT_PERSON_NAME = "^[a-zA-Z '.,]*$";

    public static final Pattern EMAIL_ADDRESS = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+");

    public static final Pattern PHONE = Pattern.compile(
            "(\\+[0-9]+[\\- \\.]*)?" + "(\\([0-9]+\\)[\\- \\.]*)?" + "([0-9][0-9\\- \\.]+[0-9])");

    /**
     * @param stringValue - string value
     * @return true - not empty | false - null or empty
     */
    public static boolean isEmptyString(String stringValue){
        return stringValue == null || stringValue.trim().isEmpty();
    }

    /**
     * @param stringValue - string value
     * @return string value which is not null
     */
    public static String getStringValue(String stringValue){
        return (stringValue == null || stringValue.trim().isEmpty())? "" : stringValue.trim();
    }

    /**
     * @param email input string
     * @return boolean email format validation
     */
    public static boolean isValidEmail(String email) {
        return EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * @param name input string
     * @return boolean - format validation
     */
    public static boolean isValidPersonName(String name) {
        return name.matches(FORMAT_PERSON_NAME);
    }

    /**
     * @param userName input string
     * @return boolean - format validation
     */
    public static boolean isValidUserName(String userName) {
        return userName.matches(FORMAT_USER_NAME);
    }

    /**
     * Validate using COMMON PHONE NUMBER FORMAT
     *
     * @param number input string
     * @return boolean - format validation
     */
    public static boolean isValidPhoneNumber(String number) {
        return PHONE.matcher(number).matches();
    }


    /**
     * @param editText - EditText variable
     * @return boolean -> true: valid user input field | false: invalid user input field
     */
    public static boolean isFieldValid(EditText editText) {

        if(isEmptyString(getStringValue(editText.getText().toString()))){
            editText.setError("⚠ This field is required!");
            return false;
        }
        editText.setError(null);
        return true;
    }

    /**
     * check if number input field is valid
     * @param editText - EditText variable
     * @param isPositiveNumbersOnly - boolean -> true: set error for negative input | false : all numbers are valid
     * @return boolean -> true: valid user input field | false: invalid user input field
     */
    public static boolean isFieldValidNumber(EditText editText, boolean isPositiveNumbersOnly) {
        String value = getStringValue(editText.getText().toString());

        if (isEmptyString(value)) {
            editText.setError("⚠ This field is required!");
            return false;
        }

        try {
            long no = Long.parseLong(value);
            if (isPositiveNumbersOnly && no < 0) {
                editText.setError("⚠ Invalid input!");
                return false;
            }
        } catch (NumberFormatException e) {
            Log.e(TAG, "isValidNumberField: ", e);
            editText.setError("⚠ Invalid input!");
        }
        editText.setError(null);
        return true;
    }


    /**
     * check if floating-point number input field is valid
     * @param editText - EditText variable
     * @param isPositiveNumbersOnly - boolean -> true: set error for negative input | false : all numbers are valid
     * @return boolean -> true: valid user input field | false: invalid user input field
     */
    public static boolean isFieldValidFloatNumber(EditText editText, boolean isPositiveNumbersOnly) {
        String value = getStringValue(editText.getText().toString());

        if (isEmptyString(value)) {
            editText.setError("⚠ This field is required!");
            return false;
        }

        try {
            double no = Double.parseDouble(value);
            if (isPositiveNumbersOnly && no < 0) {
                editText.setError("⚠ Invalid input!");
                return false;
            }
        } catch (NumberFormatException e) {
            Log.e(TAG, "isValidFloatNumberField: ", e);
            editText.setError("⚠ Invalid input!");
        }
        editText.setError(null);
        return true;
    }

    /**
     * Validate user email field
     *
     * @param editText : EditText variable of email
     * @return Valid Format = true | Invalid format = false
     */
    public static boolean isFieldEmailValid(EditText editText) {
        String value = getStringValue(editText.getText().toString());

        if(isEmptyString(value)){
            editText.setError("⚠ Email address field is empty!");
            return false;
        }

        if(!isValidEmail(value)){
            editText.setError("⚠ Please enter a valid email address!");
            return false;
        }
        else {
            editText.setError(null);
            return true;
        }
    }

    /**
     * Validate user name field
     *
     * @param editText : EditText variable of user name
     * @return Valid Format = true | Invalid format = false
     */
    public static boolean isFieldUserNameValid(EditText editText) {
        String value = getStringValue(editText.getText().toString());

        if (isEmptyString(value)) {
            editText.setError("⚠ User Name field is empty!");
            return false;
        }
        if (!isValidUserName(value)) {
            editText.setError("⚠ Invalid user name!");
            return false;
        }
        else {
            editText.setError(null);
            return true;
        }
    }

    /**
     * Validate person name field
     *
     * @param editText : EditText variable of person name
     * @return Valid Format = true | Invalid format = false
     */
    public static boolean isFieldPersonNameValid(EditText editText) {
        String value = getStringValue(editText.getText().toString());

        if (isEmptyString(value)) {
            editText.setError("⚠ Name field is empty!");
            return false;
        }
        if (!isValidPersonName(value)) {
            editText.setError("⚠ Invalid name!");
            return false;
        }
        else {
            editText.setError(null);
            return true;
        }
    }

    /**
     * Validate password field
     *
     * @param editText : EditText variable of password
     * @return Valid Format = true | Invalid format = false
     */
    public static boolean isFieldPasswordValid(EditText editText)  {
        String value = getStringValue(editText.getText().toString());

        if (isEmptyString(value)) {
            editText.setError("⚠ Please enter a valid password!");
            return false;
        }

        if (value.length() < 8) {
            editText.setError("⚠ Password must contain at least 8 characters");
            return false;
        }

        if (value.length() > 30) {
            editText.setError( "⚠ Password should not be greater than 30 characters");
            return false;
        }
        else {
            editText.setError(null);
            return true;
        }
    }

    /**
     * Validate phone number filed
     *
     * @param editText : EditText variable of Phone
     * @return Valid Format = true | Invalid format = false
     */
    public static boolean isFieldPhoneNumberValid(EditText editText) {
        String value = getStringValue(editText.getText().toString());

        if (isEmptyString(value)) {
            editText.setError("⚠ Please enter a valid phone number!");
            return false;
        }

        if (value.length() > 15) {
            editText.setError("⚠ Please enter a valid phone number!");
            return false;
        }

        if (!isValidPhoneNumber(value)) {
            editText.setError("⚠ Please enter a valid phone number!");
            return false;
        }
        else {
            editText.setError(null);
            return true;
        }
    }

    /**
     * Validate both email and password fields
     *
     * @param email : EditText variable of email
     * @param password : EditText variable of password
     * @return both fields are valid = true | one or both fields are invalid = false
     */
    public static boolean isFieldsEmailPasswordValid(EditText email, EditText password) {
        if(!isFieldEmailValid(email)) return false;
        return isFieldPasswordValid(password);
    }

    /**
     * Validate both password and confirm Password fields
     *
     * @param password : EditText variable of email
     * @param confirmPassword : EditText variable of confirm Password
     * @return both fields are valid = true | one or both fields are invalid = false
     */
    public static boolean isFieldBothPasswordsValid(EditText password, EditText confirmPassword) {
        if(!isFieldPasswordValid(password)) return false;
        if(!isFieldPasswordValid(confirmPassword)) return false;


        if (!getStringValue(password.getText().toString()).equals(getStringValue(confirmPassword.getText().toString()))) {
            confirmPassword.setError( "⚠ Entered password is mismatched!");
            return false;
        }
        else {
            confirmPassword.setError(null);
            return true;
        }
    }
}