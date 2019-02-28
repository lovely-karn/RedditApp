package com.lovely.dwelloassignment.ui.PostDetail;

import com.lovely.dwelloassignment.model.commentsResponse.CommentResponse;
import com.lovely.dwelloassignment.retrofit.APIError;
import com.lovely.dwelloassignment.retrofit.ApiResponse;

import java.util.List;

public class PostDetailsActivityPresenterImpl implements PostDetailsActivityPresenter,
        PostDetailsActivityInteractor.GetCommentsListener {


    private PostDetailsActivityView postDetailsActivityView;
    private PostDetailsActivityInteractor postDetailsActivityInteractor;

    PostDetailsActivityPresenterImpl(PostDetailsActivityView postDetailsActivityView,
                                     PostDetailsActivityInteractor postDetailsActivityInteractor) {

        this.postDetailsActivityView = postDetailsActivityView;
        this.postDetailsActivityInteractor = postDetailsActivityInteractor;
    }


    @Override
    public void callInteractorToGetComments(final String permalink) {

        if (permalink != null) {
            postDetailsActivityInteractor.apiHitToGetComments(permalink, this);
        }
    }

    @Override
    public void getCommentsApiSuccess(final List<CommentResponse> apiResponseList) {

        // call view to show comment responses
        postDetailsActivityView.showComments(apiResponseList);

    }

    @Override
    public void getCommentsApiFailure(final APIError error) {

        // show failure this time --
        postDetailsActivityView.showError(error.getMessage());
    }
}
