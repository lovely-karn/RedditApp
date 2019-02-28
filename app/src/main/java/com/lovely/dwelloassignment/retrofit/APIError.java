package com.lovely.dwelloassignment.retrofit;

import com.google.gson.annotations.SerializedName;

/**
 * APIError
 */
public class APIError {

    @SerializedName("statusCode")
    private final int statusCode;

    @SerializedName("message")
    private final String message;

    /**
     * Constructor
     *
     * @param statusCode status code of api error response
     * @param message    message of api error response
     */
    APIError(final int statusCode, final String message) {
        this.message = message;
        this.statusCode = statusCode;
    }


    /**
     * Returns the status code of the response
     *
     * @return status code of api error response
     */
    public int getStatusCode() {

        if (statusCode == 0) {
            return ErrorUtils.DEFAULT_STATUS_CODE;
        }
        return statusCode;
    }


    /**
     * Returns the message of the response
     *
     * @return the error message
     */
    public String getMessage() {

        if (message == null) {
            return "";
        } else {
            return message;
        }
    }

}

