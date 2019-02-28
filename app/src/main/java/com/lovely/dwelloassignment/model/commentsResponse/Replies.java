
package com.lovely.dwelloassignment.model.commentsResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Replies {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("data")
    @Expose
    private Data__ data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Data__ getData() {
        return data;
    }

    public void setData(Data__ data) {
        this.data = data;
    }

}
