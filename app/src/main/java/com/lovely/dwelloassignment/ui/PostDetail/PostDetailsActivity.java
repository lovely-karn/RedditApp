package com.lovely.dwelloassignment.ui.PostDetail;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.lovely.dwelloassignment.R;
import com.lovely.dwelloassignment.model.commentsResponse.Child;
import com.lovely.dwelloassignment.model.commentsResponse.ChildData;
import com.lovely.dwelloassignment.model.commentsResponse.CommentResponse;
import com.lovely.dwelloassignment.model.commentsResponse.Data;

import java.util.Date;
import java.util.List;

import static com.lovely.dwelloassignment.constant.AppConstant.EXTRA_PERMALINK;

/**
 * The type Post details activity.
 */
public class PostDetailsActivity extends AppCompatActivity
        implements PostDetailsActivityView, View.OnClickListener {

    private Toolbar idToolbar;
    private AppCompatImageView ivClose;
    private LinearLayoutCompat llContainer;
    private PostDetailsActivityPresenter postDetailsActivityPresenter;
    private String permalink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        init();

        setOnClickListener();

        // instantiating presenter
        postDetailsActivityPresenter = new PostDetailsActivityPresenterImpl(this, new PostDetailsActivityInteractorImpl());

        getIntentData();

        getComments();
    }

    private void setOnClickListener() {

        // setting clicklistener
        ivClose.setOnClickListener(this);
    }

    private void getIntentData() {

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_PERMALINK)) {
            permalink = intent.getStringExtra(EXTRA_PERMALINK);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void getComments() {

        // get the permalink
        postDetailsActivityPresenter.callInteractorToGetComments(permalink);

    }

    private void init() {

        idToolbar = findViewById(R.id.idToolbar);
        ivClose = findViewById(R.id.ivClose);
        llContainer = findViewById(R.id.llContainer);
    }


    @Override
    public void showComments(final List<CommentResponse> commentDataList) {

        addAllComments(commentDataList);
    }

    private void addAllComments(final List<CommentResponse> commentData) {

        final List<Child> children = commentData.get(1).getData().getChildren();

        for (Child childItem : children) {

            // now have to attach the comments to the layout
            Log.e("Child data ", "" + childItem);

            addLayoutAtRunTime(childItem.getData());
        }

    }

    private void addLayoutAtRunTime(final ChildData childData) {

        // attaching items at runtime

        // the Layout inflater
        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // the educational degree view that has to be added
        final View v = vi.inflate(R.layout.item_reply, null);

        // author.date. description has to be shown
        TextView tvPostedBy = v.findViewById(R.id.tvPostedBy);
        TextView tvPostDescription = v.findViewById(R.id.tvPostDescription);
        TextView tvTotalVotes = v.findViewById(R.id.tvTotalVotes);
        RelativeTimeTextView tvTime = v.findViewById(R.id.tvTime);
        LinearLayoutCompat llActions = v.findViewById(R.id.llActions);
        //individual card data has to  be fetched

        // setData
        // set Author
        Log.e("author", "" + childData.getAuthor());
        tvPostedBy.setText(String.format("%s . ", childData.getAuthor()));

        // display time_of_post
        if (childData.getCreated() != null) {
            Log.e("date", "" + childData.getCreated());
            Date date = new Date(childData.getCreated() * 1000L);
            tvTime.setReferenceTime(date.getTime());
        }

        // description
        Log.e("body", "" + childData.getBody());
        if (childData.getBodyHtml() != null) {
            tvPostDescription.setText(childData.getBody());
        }

        // total up votes
        Log.e("up of votes ", "" + childData.getUps());
        tvTotalVotes.setText(String.valueOf(childData.getUps()));


        // if author,date,body is null then may be itemm has been deleted
        if (childData.getAuthor() == null && childData.getBody() == null) {
            tvPostedBy.setText("deleted");
            tvPostDescription.setVisibility(View.GONE);
            tvTime.setVisibility(View.GONE);
            llActions.setVisibility(View.GONE);
        }


        // for more nested comments from - here replies can be accessed and addLayoutAtRunTime can be called again

        // insert into main view
        ViewGroup insertPoint = (ViewGroup) findViewById(R.id.llContainer);

        // save the instance of latest view added recently
        insertPoint.addView(v, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

    }

    @Override
    public void showError(final String error) {

        // snack bar can be used to show the error
        showSnackBarForError(error);
    }

    private void showSnackBarForError(String error) {

        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "" + error, Snackbar.LENGTH_LONG);
        snackbar.show();

    }

    @Override
    public void onClick(final View v) {

        switch (v.getId()) {
            case R.id.ivClose:
                onBackPressed();
                break;
            default:
                break;
        }
    }
}
