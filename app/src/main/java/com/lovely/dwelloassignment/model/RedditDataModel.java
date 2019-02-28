package com.lovely.dwelloassignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RedditDataModel {

    @SerializedName("title")
    @Expose
    String title;

    @SerializedName("display_name")
    @Expose
    String display_name;

    @SerializedName("icon_img")
    @Expose
    String icon_img;

    @SerializedName("display_name_prefixed")
    @Expose
    String display_name_prefixed;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("public_description")
    @Expose
    String public_description;

    @SerializedName("created")
    @Expose
    String created;

    @SerializedName("public_description_html")
    @Expose
    String public_description_html;

    @SerializedName("subreddit_type")
    @Expose
    String subreddit_type;

    @SerializedName("banner_img")
    @Expose
    String banner_img;

    @SerializedName("id")
    @Expose
    String id;

    @SerializedName("url")
    @Expose
    String url;

    @SerializedName("created_utc")
    @Expose
    Long created_utc;

    private int itemType = 0;

    @SerializedName("subreddit_name_prefixed")
    private String subredditNamePrefixed;

    @SerializedName("over_18")
    private boolean isNsfw;

    private String thumbnail;

    private String author;

    private String subreddit;

    @SerializedName("ups")
    private int upVotes;

    private int score;

    @SerializedName("num_comments")
    private int numComments;

    @SerializedName("body")
    private String body;

    private Preview preview;

    // for fetching the comments
    private String permalink;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getIcon_img() {
        return icon_img;
    }

    public void setIcon_img(String icon_img) {
        this.icon_img = icon_img;
    }

    public String getDisplay_name_prefixed() {
        return display_name_prefixed;
    }

    public void setDisplay_name_prefixed(String display_name_prefixed) {
        this.display_name_prefixed = display_name_prefixed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublic_description() {
        return public_description;
    }

    public void setPublic_description(String public_description) {
        this.public_description = public_description;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getPublic_description_html() {
        return public_description_html;
    }

    public void setPublic_description_html(String public_description_html) {
        this.public_description_html = public_description_html;
    }

    public String getSubreddit_type() {
        return subreddit_type;
    }

    public void setSubreddit_type(String subreddit_type) {
        this.subreddit_type = subreddit_type;
    }

    public String getBanner_img() {
        return banner_img;
    }

    public void setBanner_img(String banner_img) {
        this.banner_img = banner_img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCreated_utc() {
        return created_utc;
    }

    public void setCreated_utc(Long created_utc) {
        this.created_utc = created_utc;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(final int itemType) {
        this.itemType = itemType;
    }

    public String getSubredditNamePrefixed() {
        return subredditNamePrefixed;
    }

    public void setSubredditNamePrefixed(String subredditNamePrefixed) {
        this.subredditNamePrefixed = subredditNamePrefixed;
    }

    public boolean isNsfw() {
        return isNsfw;
    }

    public void setNsfw(boolean nsfw) {
        isNsfw = nsfw;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Preview getPreview() {
        return preview;
    }

    public void setPreview(Preview preview) {
        this.preview = preview;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

}
