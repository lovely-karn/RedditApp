package com.lovely.dwelloassignment.ui.MainDashboard.PostsList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lovely.dwelloassignment.R;
import com.lovely.dwelloassignment.model.ChildData;
import com.lovely.dwelloassignment.model.RedditDataModel;
import com.lovely.dwelloassignment.ui.MainDashboard.PostsList.adapter.SubredditpostAdapter;
import com.lovely.dwelloassignment.ui.PostDetail.PostDetailsActivity;

import java.util.List;

import static com.lovely.dwelloassignment.constant.AppConstant.EXTRA_PERMALINK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link PostsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostsListFragment extends Fragment
        implements PostsListFragmentView,
        SubredditpostAdapter.OnViewHolderItemClick,
        SubredditpostAdapter.OnLoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener {

    private Activity activity;
    private RecyclerView rvPosts;
    private SwipeRefreshLayout swipeRefresh;
    private SubredditpostAdapter subredditpostAdapter;
    private PostsListFragmentPresenter postsListFragmentPresenter;

    public PostsListFragment() {
        // Required empty public constructor
    }

    public static PostsListFragment newInstance(String param1, String param2) {
        PostsListFragment fragment = new PostsListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_posts_list, container, false);
        init(view);

        // attach presenter too & make api hit
        postsListFragmentPresenter = new PostsListFragmentPresenterImpl(this, new PostsListFragmentInteractorImpl());

        // call presenter for the api call
        postsListFragmentPresenter.callInteractorToGetSubReddits();

        return view;
    }

    private void init(View view) {

        swipeRefresh = view.findViewById(R.id.swipeRefresh);

        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.azure));
        swipeRefresh.setOnRefreshListener(this);

        // init recycler view
        initRecyclerView(view);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void initRecyclerView(View view) {


        rvPosts = view.findViewById(R.id.rvPosts);

        // finding adapter
        subredditpostAdapter = new SubredditpostAdapter(activity, this, this);

        rvPosts.setAdapter(subredditpostAdapter);
        rvPosts.setLayoutManager(new LinearLayoutManager(activity));
    }

    @Override
    public void onRefresh() {
        postsListFragmentPresenter.onSwipeRefresh();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Override
    public void onClickOfItem(final String permalinkForComments) {

        // navigate to post details page ->

        Intent intentToCommentsActivity = new Intent(activity, PostDetailsActivity.class);
        intentToCommentsActivity.putExtra(EXTRA_PERMALINK, permalinkForComments);
        startActivity(intentToCommentsActivity);
    }

    @Override
    public void showErrorView(final String errorMessage) {

        subredditpostAdapter.displayErrorMessage(errorMessage.concat(getString(R.string.tap_to_retry)));
        swipeRefresh.setRefreshing(false);
        swipeRefresh.setEnabled(false);
    }

    @Override
    public void showErrorView(final int errorMessageId) {

        showErrorView(getString(errorMessageId));
    }

    @Override
    public void showNoItemsView() {

        subredditpostAdapter.displayNoDataString(getString(R.string.no_posts));
        swipeRefresh.setEnabled(true);
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showItems(final List<ChildData> childrenList) {

        Log.e(" showing items ", "inside post list frag " + childrenList.size());
        subredditpostAdapter.addAll(childrenList);
        swipeRefresh.setRefreshing(false);
        swipeRefresh.setEnabled(true);
        //  subredditpostAdapter.setMore();
    }

    @Override
    public void showMoreItems(final List<ChildData> childrenList) {
        hideRecyclerLoader();
        subredditpostAdapter.hideRetryView();
        subredditpostAdapter.addItemMore(childrenList);
        subredditpostAdapter.setMore();
        swipeRefresh.setEnabled(true);
    }

    @Override
    public void showRetry() {

        hideRecyclerLoader();
        subredditpostAdapter.displayRetryView();
        swipeRefresh.setRefreshing(false);
        swipeRefresh.setEnabled(true);

    }

    @Override
    public void hideRecyclerLoader() {

        subredditpostAdapter.dismissLoading();
    }

    @Override
    public void hideMoreLoaderIfLoading() {

    }

    @Override
    public void onLoadMore() {
        swipeRefresh.setRefreshing(false);
        postsListFragmentPresenter.onLoadMore();
    }
}
