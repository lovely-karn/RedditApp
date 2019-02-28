package com.lovely.dwelloassignment.ui.MainDashboard.PostsList.adapter;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lovely.dwelloassignment.R;
import com.lovely.dwelloassignment.databinding.ItemSubredditPostBinding;
import com.lovely.dwelloassignment.model.ChildData;
import com.lovely.dwelloassignment.model.RedditDataModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubredditpostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_ITEM = 0;
    private static final int VIEW_PROG = 1;
    private static final int VIEW_RETRY = 2;
    private static final int VIEW_ERROR = 3;
    private static final int VIEW_NO_DATA = 4;

    private Activity activity;
    private OnViewHolderItemClick listener;
    private OnLoadMoreListener loadMoreListener;
    private List<ChildData> childrenList;
    private String errorMessage;
    private String noDataString;
    private boolean isMoreLoading = true;
    private OnActionListener mOnActionListener;

    public SubredditpostAdapter(final Activity activity,
                                final OnViewHolderItemClick listener,
                                final OnLoadMoreListener loadMoreListener) {
        this.activity = activity;
        this.listener = listener;
        this.loadMoreListener = loadMoreListener;
    }

    public void updateList(List<ChildData> childrenList) {

        this.childrenList = childrenList;
    }

    /**
     * Sets on action listener.
     *
     * @param onActionListener the on action listener
     */
    public void setOnActionListener(final OnActionListener onActionListener) {
        mOnActionListener = onActionListener;
    }

    @Override
    public int getItemViewType(final int position) {
        return childrenList.get(position).getRedditPostList().getItemType();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemSubredditPostBinding itemSubredditPostBinding = ItemSubredditPostBinding.inflate(layoutInflater, parent, false);
        return new PostViewHolder(itemSubredditPostBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        Log.e(" inside adapter ", "onBindViewHolder, childrenlist size " + childrenList.size());

        // specific viewHolder type Post type -->
        ((PostViewHolder) viewHolder).bindTo(childrenList.get(viewHolder.getAdapterPosition()).getRedditPostList());
    }

    /**
     * show progress loader at bottom while recycler view
     * is loading more items on scroll
     */
    public void showLoading() {
        if (isMoreLoading && childrenList != null && loadMoreListener != null) {
            isMoreLoading = false;
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    childrenList.add(new ChildData());
                    childrenList.get(childrenList.size() - 1).getRedditPostList().setItemType(VIEW_PROG);
                    notifyItemInserted(childrenList.size() - 1);
                    loadMoreListener.onLoadMore();
                }
            });
        }
    }

    /**
     * setter to set if loading more items is required
     */
    public void setMore() {
        this.isMoreLoading = true;
    }

    /**
     * dismiss progress loader shown at bottom
     * after the onLoadMore() method has performed its task
     * and new data is added to recycler view.
     * This function removes the progress loader item added at end of recycler view list
     */
    public void dismissLoading() {
        if (childrenList != null && childrenList.size() > 0
                && childrenList.get(childrenList.size() - 1).getRedditPostList().getItemType() == VIEW_PROG) {
            childrenList.remove(childrenList.size() - 1);
            notifyItemRemoved(childrenList.size());
        }
    }

    /**
     * add all items to children list, called
     * to initialize the list a fresh
     *
     * @param childrenPostsList children list
     */
    public void addAll(final List<ChildData> childrenPostsList) {
        if (childrenList != null) {
            childrenList.clear();
        } else {
            childrenList = new ArrayList<>();
        }

        childrenList.addAll(childrenPostsList);
        notifyDataSetChanged();
    }

    /**
     * add more items to children list on scroll
     *
     * @param childrenPostsList children list
     */
    public void addItemMore(final List<ChildData> childrenPostsList) {
        int sizeInit = childrenList.size();
        childrenList.addAll(childrenPostsList);
        notifyItemRangeChanged(sizeInit, childrenList.size());
    }

    @Override
    public int getItemCount() {
        return childrenList != null ? childrenList.size() : 0;
    }

    /**
     * In case of an error
     * clear the recycler view list and add a null value item
     * notify adapter that data set has changed
     * show error message to user
     *
     * @param message error message to be displayed
     */
    public void displayErrorMessage(final String message) {
        errorMessage = message;
        childrenList.clear();
        childrenList.add(new ChildData());
        childrenList.get(childrenList.size() - 1).getRedditPostList().setItemType(VIEW_ERROR);
        notifyDataSetChanged();
    }

    /**
     * In case the list of data received from server
     * is empty
     * show no data message to user
     *
     * @param message no data message to be displayed
     */
    public void displayNoDataString(final String message) {
        noDataString = message;
        childrenList.clear();
        childrenList.add(new ChildData());
        childrenList.get(childrenList.size() - 1).getRedditPostList().setItemType(VIEW_NO_DATA);
        notifyDataSetChanged();
    }

    /**
     * Display retry view.
     */
    public void displayRetryView() {
        if (childrenList.size() == 0
                || (childrenList != null
                && childrenList.size() > 0
                && childrenList.get(childrenList.size() - 1).getRedditPostList().getItemType() != VIEW_RETRY)) {
            childrenList.add(new ChildData());
            childrenList.get(childrenList.size() - 1).getRedditPostList().setItemType(VIEW_RETRY);
            notifyItemInserted(childrenList.size() - 1);
        }
    }

    /**
     * Hide retry view.
     */
    public void hideRetryView() {
        if (childrenList != null && childrenList.size() > 0
                && childrenList.get(childrenList.size() - 1).getRedditPostList().getItemType() == VIEW_RETRY) {
            childrenList.remove(childrenList.size() - 1);
            notifyItemRemoved(childrenList.size());
        }
    }

    public interface OnViewHolderItemClick {

        void onClickOfItem(String permalinkForComments);
    }

    /**
     * interface to listen to when recycler view is scrolled
     */
    public interface OnLoadMoreListener {

        /**
         * override onLoadMore() in listener
         * to specify the action required when recycler view is scrolled
         * to load more data
         */
        void onLoadMore();
    }


    /**
     * Call back implemented in HomeActivity to initiate API hit or perform
     * action on tap of Accept and Reject Buttons
     */
    public interface OnActionListener {

        /**
         * method to be executed to retry loading data
         */
        void onRetry();

    }


    // view holder
    public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemSubredditPostBinding binding;
        private String permalink;

        public PostViewHolder(ItemSubredditPostBinding itemActivityListBinding) {
            super(itemActivityListBinding.getRoot());

            this.binding = itemActivityListBinding;
        }

        public void bindTo(RedditDataModel redditDataModel) {

            // get the permalink
            permalink = redditDataModel.getPermalink();

            // displaying post title
            // TODO : not clear with the keyword..
            binding.tvPostTag.setText(String.format("r/%s", ""));

            // displaying user image
            Glide.with(activity)
                    .load(redditDataModel.getIcon_img())
                    .into(binding.cvUserPic);

            // display the post description
            binding.tvPostDescription.setText(redditDataModel.getTitle());

            // display post image if any
            Glide.with(activity)
                    .load(redditDataModel.getThumbnail())
                    .into(binding.cvUserPic);

            // display post the author
            binding.tvPostedBy.setText(String.format("r/%s . ", redditDataModel.getAuthor()));

            // display time_of_post
            Date date = new Date(redditDataModel.getCreated_utc() * 1000L);
            binding.tvTime.setReferenceTime(date.getTime());


            // display the no of upvotes , display no of comments
            binding.tvTotalVotes.setText(String.valueOf(redditDataModel.getUpVotes()));
            binding.tvNoOfComments.setText(String.valueOf(redditDataModel.getNumComments()));

            // on share button u can open up the share intent -> just opening it


            // adding click listener
            binding.llHolder.setOnClickListener(this);
        }

        @Override
        public void onClick(final View v) {

            switch (v.getId()) {
                case R.id.llHolder:
                    listener.onClickOfItem(this.permalink);
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * Bottom Progress bar view Holder
     */
    private final class ProgressViewHolder extends RecyclerView.ViewHolder {

        /**
         * @param progressView view
         */
        private ProgressViewHolder(final View progressView) {
            super(progressView);
        }
    }

    /**
     * view holder class
     */
    private final class RetryViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivRetry;

        /**
         * Instantiates a new view holder.
         *
         * @param itemView itemView
         */
        RetryViewHolder(final View itemView) {
            super(itemView);
        }
    }

    /**
     * view holder class
     */
    private final class ErrorViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNoInternet;

        /**
         * Instantiates a new view holder.
         *
         * @param itemView itemView
         */
        ErrorViewHolder(final View itemView) {
            super(itemView);
            tvNoInternet = itemView.findViewById(R.id.tvNoInternet);
        }
    }

    /**
     * view holder class
     */
    private final class NoDataViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNoPsts;

        /**
         * Instantiates a new view holder.
         *
         * @param itemView itemView
         */
        NoDataViewHolder(final View itemView) {
            super(itemView);
            tvNoPsts = itemView.findViewById(R.id.tvNoPsts);
        }
    }

}
