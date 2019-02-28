package com.lovely.dwelloassignment.ui.MainDashboard.PostsList;

public interface PostsListFragmentPresenter {

    /**
     * Performs No Internet, tap to Retry
     * action
     */
    void onNoInternetClicked();

    /**
     * Called when swipe refersh onRefresh() is executed
     * perform on refresh action here
     */
    void onSwipeRefresh();

    /**
     * Called when recycler view onLoadMore() is executed
     * fetch next page data and notify recycler once done
     */
    void onLoadMore();


    void callInteractorToGetSubReddits();

}
