package com.lovely.dwelloassignment.ui.MainDashboard.PostsList;

import com.lovely.dwelloassignment.model.ChildData;
import com.lovely.dwelloassignment.model.RedditDataModel;

import java.util.List;

public interface PostsListFragmentView {

    /**
     * Show No Internet Tap to Retry View
     * in case of No Internet Connection
     *
     * @param errorMessage the error message to be displayed on view
     */
    void showErrorView(final String errorMessage);

    /**
     * Show No Internet Tap to Retry View
     * in case of No Internet Connection
     *
     * @param errorMessageId the error message string id to be displayed on view
     */
    void showErrorView(final int errorMessageId);

    /**
     * Show No items available
     * when the list returned is empty
     */
    void showNoItemsView();

    /**
     * Show items in recycler view
     * when list with size greater than 0 is returned
     *
     * @param childrenList list of subreddit posts fetched from server,  returned by PostsListFragmentInteractor on success
     */
    void showItems(List<ChildData> childrenList);

    /**
     * show next page items
     * when OnLoadMore is executed for adapter,
     * basically showing next page items after page 1
     *
     * @param childrenList list of subreddit posts fetched from server,   returned by PostsListFragmentInteractor on success
     */
    void showMoreItems(List<ChildData> childrenList);

    /**
     * Show Retry View
     * in case of failure of loading next page data
     * that is page other than first page
     */
    void showRetry();

    /**
     * remove the bottom loader
     * from recycler view
     * once the data is loaded
     */
    void hideRecyclerLoader();

    /**
     * hideMoreLoaderIfLoading
     */
    void hideMoreLoaderIfLoading();
}
