package com.lovely.dwelloassignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChildData {

    @SerializedName("data")
    @Expose
    RedditDataModel redditPostList;

    public RedditDataModel getRedditPostList() {
        return redditPostList;
    }

    public void setRedditPostList(RedditDataModel redditPostList) {
        this.redditPostList = redditPostList;
    }
}
