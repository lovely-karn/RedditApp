package com.lovely.dwelloassignment.retrofit;

import com.lovely.dwelloassignment.model.commentsResponse.CommentResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Api Interface
 */
public interface ApiInterface {

    /**
     * Gets posts.
     *
     * @return the posts
     */
    @GET("/top.json")
    Call<ApiResponse> getsubbreditPostsForHome(@Query("after") String after,
                                               @Query("limit") String limit);


    /**
     * Gets posts.
     *
     * @return the posts
     */
    @GET("/users/popular.json")
    Call<ApiResponse> getsubbreditPostsForPopular(@Query("after") String after,
                                                  @Query("limit") String limit);


    @GET("{permalink}.json")
    Call<List<CommentResponse>> getComments(@Path(value = "permalink", encoded = true) String permalink);

}