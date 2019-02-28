package com.lovely.dwelloassignment.ui.PostDetail;

import com.lovely.dwelloassignment.model.commentsResponse.CommentResponse;
import com.lovely.dwelloassignment.retrofit.APIError;
import com.lovely.dwelloassignment.retrofit.ApiResponse;
import com.lovely.dwelloassignment.retrofit.ResponseResolver;
import com.lovely.dwelloassignment.retrofit.RestClient;

import java.util.List;

public class PostDetailsActivityInteractorImpl implements PostDetailsActivityInteractor {
    @Override
    public void apiHitToGetComments(final String permalink, final GetCommentsListener listener) {

        RestClient.getApiInterface().getComments(permalink).enqueue(new ResponseResolver<List<CommentResponse>>() {
            @Override
            public void success(final List<CommentResponse> apiResponseList) {
                listener.getCommentsApiSuccess(apiResponseList);
            }

            @Override
            public void failure(final APIError error) {
                listener.getCommentsApiFailure(error);
            }
        });
    }
}
