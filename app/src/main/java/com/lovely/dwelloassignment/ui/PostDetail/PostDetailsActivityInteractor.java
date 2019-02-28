package com.lovely.dwelloassignment.ui.PostDetail;

import com.lovely.dwelloassignment.model.commentsResponse.CommentResponse;
import com.lovely.dwelloassignment.retrofit.APIError;
import com.lovely.dwelloassignment.retrofit.ApiResponse;

import java.util.List;

/**
 * The interface Post details activity interactor.
 */
public interface PostDetailsActivityInteractor {

    /**
     * Api hit to get comments.
     *
     * @param permalink the permalink
     * @param listener  the listener
     */
    void apiHitToGetComments(String permalink, GetCommentsListener listener);

    /**
     * The interface Get comments listener.
     */
    interface GetCommentsListener {

        /**
         * Gets comments api success.
         *
         * @param apiResponseList the api response
         */
        void getCommentsApiSuccess(List<CommentResponse> apiResponseList);

        /**
         * Gets comments api failure.
         *
         * @param error the error
         */
        void getCommentsApiFailure(APIError error);
    }
}
