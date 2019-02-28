package com.lovely.dwelloassignment.ui.MainDashboard;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.lovely.dwelloassignment.R;
import com.lovely.dwelloassignment.adapter.PagerAdapter;
import com.lovely.dwelloassignment.constant.AppConstant;
import com.lovely.dwelloassignment.ui.MainDashboard.PostsList.PostsListFragment;

import java.util.ArrayList;
import java.util.List;

import static com.lovely.dwelloassignment.constant.AppConstant.FILTER_STATUS;

public class MainActivity extends AppCompatActivity {

    private ViewPager vpPosts;
    private TabLayout tabLayout;
    private AppCompatImageView ivUser;
    private AppCompatEditText etSearch;
    private List<Fragment> fragmentsList;
    private PagerAdapter pagerAdapter;
    private List<String> fragmentsListName, tabName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialising view
        init();
    }

    private void init() {

        setTabName();
        fragmentsList = new ArrayList<>();
        fragmentsListName = new ArrayList<>();

        vpPosts = findViewById(R.id.vpPosts);
        tabLayout = findViewById(R.id.tabLayout);
        ivUser = findViewById(R.id.ivUser);
        etSearch = findViewById(R.id.etSearch);

        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tab_selected));

        addPagesTofragmentList();

        // attaching the viewPAger
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), fragmentsList, fragmentsListName);
        vpPosts.setAdapter(pagerAdapter);
        vpPosts.setOffscreenPageLimit(2);
        vpPosts.setCurrentItem(0);
        vpPosts.setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        tabLayout.setupWithViewPager(vpPosts);
    }

    private void addPagesTofragmentList() {

        for (int i = 0; i < 2; i++) {
            Fragment fragment = new PostsListFragment();
            Bundle bundle = new Bundle();
            bundle.putString(FILTER_STATUS, tabName.get(i));
            fragment.setArguments(bundle);
            fragmentsList.add(fragment);
            fragmentsListName.add(tabName.get(i));
        }
    }


    /**
     * set tab name
     */
    private void setTabName() {
        tabName = new ArrayList<>();
        tabName.add(AppConstant.HOME);
        tabName.add(AppConstant.POPULAR);
    }

}
