package com.lovely.dwelloassignment.ui.MainDashboard.PostsList;

import com.lovely.dwelloassignment.model.ChildData;
import com.lovely.dwelloassignment.model.Data;
import com.lovely.dwelloassignment.model.RedditDataModel;
import com.lovely.dwelloassignment.retrofit.APIError;
import com.lovely.dwelloassignment.retrofit.ApiResponse;

import java.util.ArrayList;
import java.util.List;

public class PostsListFragmentPresenterImpl
        implements PostsListFragmentPresenter,
        PostsListFragmentInteractor.RedditsPostsListener {


    private String after = null;
    private String before = null;
    private static final int LIMIT = 10;
    private List<ChildData> childrenList;

    private PostsListFragmentView postsListFragmentView;
    private PostsListFragmentInteractor postsListFragmentInteractor;

    public PostsListFragmentPresenterImpl(final PostsListFragmentView postsListFragmentView,
                                          final PostsListFragmentInteractor postsListFragmentInteractor) {
        this.postsListFragmentView = postsListFragmentView;
        this.postsListFragmentInteractor = postsListFragmentInteractor;

        // here if before is null means it is the
        // first time which is being loaded or say then after is null

    }


    @Override
    public void redditsPostsApiSuccess(final ApiResponse apiResponse) {

        // response has to be parsed in this model -> SubredditResponse
        if (before == null && childrenList != null) {
            childrenList.clear();
        }

        if (childrenList == null) {
            childrenList = new ArrayList<>();
        }

        Data responseData = apiResponse.toResponseModel(Data.class);

        List<ChildData> childrenDataList = responseData.getChildren();

        childrenList.addAll(childrenDataList);

        displayData();

        after = responseData.getAfter();
        before = responseData.getBefore();
    }

    private void displayData() {

        // handle the fetched list of children

        postsListFragmentView.showItems(childrenList);

    }

    @Override
    public void redditsPostsApiError(final APIError apiError) {

        // show error in the adapter itself.

    }

    @Override
    public void onNoInternetClicked() {

        // same get new subreddits
        callInteractorToGetSubReddits();
    }

    @Override
    public void onSwipeRefresh() {

        // is n/w connected is not being checked for now

        after = null;
        callInteractorToGetSubReddits();

    }

    @Override
    public void onLoadMore() {

        callInteractorToGetSubReddits();
    }

    @Override
    public void callInteractorToGetSubReddits() {

       /* if (totalCount != -1 && totalCount <= skip) {
            mNotificationView.hideRecyclerLoader();
            return;
        }*/ // iska equivalent
        // banega ki --
        // after & before check krna hoga ki null ot nhi hai  yahan total count ka concept nhi hoga


        // making api call
        postsListFragmentInteractor.getRedditsPosts(after, String.valueOf(LIMIT), this);
    }
}
