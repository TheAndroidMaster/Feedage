package me.jfenn.feedage.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import me.jfenn.feedage.fragments.BasePagerFragment;

public class SimplePagerAdapter extends FragmentStatePagerAdapter {

    private BasePagerFragment[] fragments;

    public SimplePagerAdapter(FragmentManager fm, BasePagerFragment... fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public BasePagerFragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position].getTitle();
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
