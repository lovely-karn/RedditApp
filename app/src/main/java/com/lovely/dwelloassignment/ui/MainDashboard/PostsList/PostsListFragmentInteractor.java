package com.lovely.dwelloassignment.ui.MainDashboard.PostsList;

import com.lovely.dwelloassignment.retrofit.APIError;
import com.lovely.dwelloassignment.retrofit.ApiResponse;

/**
 * The interface Main activity interactor.
 */
public interface PostsListFragmentInteractor {

    /**
     * Gets reddits posts.
     *
     * @param after                the after
     * @param limit                the limit
     * @param redditsPostsListener the reddits posts listener
     */
    void getRedditsPosts(final String after,
                         final String limit,
                         final RedditsPostsListener redditsPostsListener);

    /**
     * The interface Reddits posts listener.
     */
    interface RedditsPostsListener {

        /**
         * Reddits posts api success.
         *
         * @param apiResponse the api response
         */
        void redditsPostsApiSuccess(ApiResponse apiResponse);

        /**
         * Reddits posts api error.
         *
         * @param apiError the api error
         */
        void redditsPostsApiError(APIError apiError);
    }

}
