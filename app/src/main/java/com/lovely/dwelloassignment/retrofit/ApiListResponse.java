package com.lovely.dwelloassignment.retrofit;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lovely.dwelloassignment.model.commentsResponse.CommentResponse;

import java.util.List;

public class ApiListResponse {
    @SerializedName("statusCode")
    @Expose
    private String statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<CommentResponse> data;

    /**
     * Gets data.
     *
     * @return the data
     */
    public List<CommentResponse> getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(final List<CommentResponse> data) {
        this.data = data;
    }

    /**
     * Get message from api response
     *
     * @return message message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Get status code of api response
     *
     * @return statusCode status code
     */
    public String getStatusCode() {
        return statusCode;
    }

    @Override
    public String toString() {
        return statusCode + " " + message + "\n" + data;
    }

    /**
     * Get data model.
     *
     * @param <T>      the type parameter
     * @param classRef the class ref
     * @return t t
     */
    public <T> T toResponseModel(final Class<T> classRef) {
        return new Gson().fromJson(new Gson().toJson(this.data), classRef);
    }
}
