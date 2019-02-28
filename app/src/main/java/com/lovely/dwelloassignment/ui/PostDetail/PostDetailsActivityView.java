package com.lovely.dwelloassignment.ui.PostDetail;

import com.lovely.dwelloassignment.model.commentsResponse.CommentResponse;
import com.lovely.dwelloassignment.model.commentsResponse.Data;

import java.util.List;

public interface PostDetailsActivityView {

    void showComments(List<CommentResponse> commentDataList);

    void showError(String error);
}
