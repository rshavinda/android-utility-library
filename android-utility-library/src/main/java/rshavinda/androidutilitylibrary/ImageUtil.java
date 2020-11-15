package rshavinda.androidutilitylibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.ByteArrayOutputStream;


public class ImageUtil {
    private static final String TAG = ImageUtil.class.getSimpleName();

    /**
     * convert Bitmap to byte array
     *
     * @param bitmap - image bitmap file
     * @return bitmap as byte array
     */
    public static byte[] bitmapToByte(Bitmap bitmap) {
        if (bitmap == null) return null;

        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            return stream.toByteArray();
        } catch (Exception ex) {
            Log.e(TAG, "byteToBitmap: ", ex);
            return null;
        }
    }

    /**
     * convert byte array to Bitmap
     *
     * @param byteArray - image as byte array
     * @return - image bitmap file
     */
    public static Bitmap byteToBitmap(byte[] byteArray) {
        if (byteArray == null || byteArray.length == 0) return null;

        try {
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        } catch (Exception ex) {
            Log.e(TAG, "byteToBitmap: ", ex);
            return null;
        }
    }

    /**
     * convert Drawable to Bitmap
     *
     * @param drawable - android drawable file
     * @return bitmap file
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        return drawable == null ? null : ((BitmapDrawable) drawable).getBitmap();
    }

    /**
     * convert Bitmap to Drawable
     * @param bitmap - image bitmap file
     * @return - android drawable file
     */
    public static Drawable bitmapToDrawable(Context context, Bitmap bitmap) {
        if (bitmap == null) return null;
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    /**
     * convert Drawable to byte array
     * @param drawable - android drawable file
     * @return drawable as byte array
     */
    public static byte[] drawableToByte(Drawable drawable) {
        return bitmapToByte(drawableToBitmap(drawable));
    }

    /**
     * convert byte array to Drawable
     * @param byteArray - image as byte array
     * @return Drawable file
     */
    public static Drawable byteToDrawable(Context context, byte[] byteArray) {
        return bitmapToDrawable(context, byteToBitmap(byteArray));
    }

    /**
     * Scale image
     * @param bitmap      - image bitmap file
     * @param scaleWidth  scale of width
     * @param scaleHeight scale of height
     * @return - scaled bitmap image
     */
    public static Bitmap scaleImage(Bitmap bitmap, float scaleWidth, float scaleHeight) {
        if (bitmap == null) return null;
        try {
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (Exception ex) {
            Log.e(TAG, "scaleImage: ", ex);
            return null;
        }
    }
}
