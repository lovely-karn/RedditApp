package com.lovely.dwelloassignment.ui.MainDashboard.PostsList;

import com.lovely.dwelloassignment.retrofit.APIError;
import com.lovely.dwelloassignment.retrofit.ApiResponse;
import com.lovely.dwelloassignment.retrofit.ResponseResolver;
import com.lovely.dwelloassignment.retrofit.RestClient;

public class PostsListFragmentInteractorImpl
        implements PostsListFragmentInteractor {

    @Override
    public void getRedditsPosts(final String after,
                                final String limit,
                                final RedditsPostsListener redditsPostsListener) {

        RestClient.getApiInterface().getsubbreditPostsForHome(after, limit).enqueue(new ResponseResolver<ApiResponse>() {
            @Override
            public void success(final ApiResponse apiResponse) {

                redditsPostsListener.redditsPostsApiSuccess(apiResponse);
            }

            @Override
            public void failure(final APIError error) {

                redditsPostsListener.redditsPostsApiError(error);
            }
        });

    }
}
