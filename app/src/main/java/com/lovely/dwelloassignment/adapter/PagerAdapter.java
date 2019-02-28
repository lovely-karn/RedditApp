package com.lovely.dwelloassignment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;
    private List<String> fragmentsTitle;

    /**
     * Instantiates a new Pager adapter.
     *
     * @param fm             the fm
     * @param fragments      the fragments
     * @param fragmentsTitle title of fragment to be set during tabLayout
     */
    public PagerAdapter(final FragmentManager fm, final List<Fragment> fragments, final List<String> fragmentsTitle) {
        super(fm);
        this.fragments = fragments;
        this.fragmentsTitle = fragmentsTitle;
    }

    @Override
    public Fragment getItem(final int position) {

        return this.fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(final int position) {
        return fragmentsTitle.get(position);
    }

    @Override
    public int getCount() {
        if (fragments == null) {
            return 0;
        } else {
            return fragments.size();
        }
    }
}
