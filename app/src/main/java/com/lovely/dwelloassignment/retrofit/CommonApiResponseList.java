package com.lovely.dwelloassignment.retrofit;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Common Api Response List
 */
public class CommonApiResponseList implements Parcelable {

    public static final Creator<CommonApiResponseList> CREATOR = new Creator<CommonApiResponseList>() {
        @Override
        public CommonApiResponseList createFromParcel(final Parcel source) {
            return new CommonApiResponseList(source);
        }

        @Override
        public CommonApiResponseList[] newArray(final int size) {
            return new CommonApiResponseList[size];
        }
    };

    @SerializedName("statusCode")
    @Expose
    private String statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<String> data;

    /**
     * constructor with param
     *
     * @param in in
     */
    protected CommonApiResponseList(final Parcel in) {
        this.statusCode = in.readString();
        this.message = in.readString();
        this.data = new ArrayList<String>();
        in.readList(this.data, Object.class.getClassLoader());
    }

    /**
     * Parameterized constructor
     *
     * @param tutorData list of tutorData
     */
    public CommonApiResponseList(final List<String> tutorData) {
        this.data = tutorData;
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

    /**
     * array
     *
     * @return data
     */
    public List<String> getData() {
        return data;
    }

    /**
     * set data
     *
     * @param data data
     */
    public void setData(final List<String> data) {
        this.data = data;
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
        return new Gson().fromJson(new Gson().toJson(data), classRef);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(this.statusCode);
        dest.writeString(this.message);
        dest.writeList(this.data);
    }
}
