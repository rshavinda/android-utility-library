package amazonite.android.util;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


import static android.os.Build.BRAND;
import static android.os.Build.DEVICE;
import static android.os.Build.FINGERPRINT;
import static android.os.Build.HARDWARE;
import static android.os.Build.MANUFACTURER;
import static android.os.Build.MODEL;
import static android.os.Build.TAGS;

@SuppressWarnings("deprecation")
public class SystemUtil {
    private static final String TAG = SystemUtil.class.getSimpleName();

    /**
     * Make StatusBar transparent, StatusBar text light colour and full screen
     * @param activity - current activity
     */
    public static void transparencyStatusBar(Activity activity) {
        Window window = activity.getWindow();
        //change status bar text colour
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);

        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
    }

    /**
     * Change NavigationBar colour
     * @param activity - current activity
     * @param resId - colour resource id
     */
    public static void setNavigationBarColorByResource(Activity activity, int resId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setNavigationBarColor(activity.getResources().getColor(resId));
        }
    }

    /**
     * Change NavigationBar colour
     * @param activity - current activity
     * @param color - colour in int format (EX. Color.BLACK)
     */
    public static void setNavigationBarColor(Activity activity, int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setNavigationBarColor(color);
        }
    }

    /**
     * Make StatusBar transparent, StatusBar text light colour and full screen
     * and Change NavigationBar dark colour
     * @param activity - current activity
     */
    public static void setStyleDarkFullScreen(Activity activity) {
        transparencyStatusBar(activity);
        setNavigationBarColor(activity, Color.BLACK);
    }


    /**
     * Hide keyboard from the screen
     * @param activity - current activity
     */
    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            view.clearFocus();
        } else {
            activity.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }
    }


    /**
     * Show keyboard in the screen
     * @param context - app context
     * @param editText - EditText variable that want to focus
     */
    public static void showKeyboard(final Context context, final EditText editText) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if (context != null) {
                    InputMethodManager inputMethodManager = (InputMethodManager) context
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
                }
            }
        });
    }

    /**
     * Pause the current thread execution
     * @param milliseconds - time in milliseconds
     */
    public static void sleepFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * check sdcard is mounted or not
     * @return if sdcard is mounted , return true ; or return false
     */
    public static boolean isSDCardEnable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     *  BRAND
     * The consumer-visible brand with which the product/hardware will be associated, if any.
     */
    public static String getBrand() {
        return BRAND;
    }

    /**
     * MODEL
     *The end-user-visible name for the end product.
     */
    public static String getDeviceModel() {
        return MODEL;
    }

    /**
     * RELEASE
     * The user-visible version string.
     */
    public static String getOSVersion() {
        return "android " + Build.VERSION.RELEASE;
    }

    /**
     * VERSION_NAME
     * The app version name.
     * Ex: "1.0.6"
     */
    public static String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    /**
     * DEVICE
     * The name of the industrial design.
     */
    public static String getBuildDevice() {
        return DEVICE;
    }

    /**
     * MANUFACTURER
     * The manufacturer of the product/hardware.
     */
    public static String getBuildManufacturer() {
        return MANUFACTURER;
    }

    /**
     * HARDWARE
     * The name of the hardware (from the kernel command line or /proc).
     */
    public static String getHardware() {
        return HARDWARE;
    }

    /**
     * FINGERPRINT
     * A string that uniquely identifies this build.
     */
    public static String getFingerprint() {
        return FINGERPRINT;
    }

    /**
     * TAGS
     * Comma-separated tags describing the build, like "unsigned,debug".
     */
    public static String getTags() {
        return TAGS;
    }

    /**
     * SDK_INT
     * The SDK version of the software currently running on this hardware device.
     */
    public static int getOSVersionSDKINT() {
        return Build.VERSION.SDK_INT;
    }
}