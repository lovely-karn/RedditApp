package com.lovely.dwelloassignment.retrofit;

import android.net.Uri;
import android.util.Log;
import android.webkit.MimeTypeMap;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * The  Retrofit utils
 */
public final class RetrofitUtils {

    private RetrofitUtils() {
    }


    /**
     * Gets request body from string.
     *
     * @param value the value
     * @return the request body from string
     */
    static RequestBody getRequestBodyFromString(final String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }


    /**
     * Gets part body from file.
     *
     * @param key  the key
     * @param file the file
     * @return the part body from file
     */
    public static MultipartBody.Part getPartBodyFromFile(final String key, final File file) {

        // create RequestBody instance from file
        final RequestBody requestFile =
                RequestBody.create(MediaType.parse(getMimeType(file)), file);


        // MultipartBody.Part is used to send also the actual file name

        return MultipartBody.Part.createFormData(key, file.getName(), requestFile);
    }


    /**
     * Gets mime type.
     *
     * @param file the file
     * @return the mime type
     */
    static String getMimeType(final File file) {
        String mimeType = "image/png";
        try {
            final Uri selectedUri = Uri.fromFile(file);
            final String fileExtension
                    = MimeTypeMap.getFileExtensionFromUrl(selectedUri.toString());
            mimeType
                    = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension);
        } catch (final Exception e) {
            Log.e("Mime type exception ", e.toString());
        }
        return mimeType;
    }

}
