package com.skapina.frecycle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

//This class simply creates an adapter to add fragments to the application. Our fragments are used to create the tabbed interface.
public class TabAdapter extends FragmentStatePagerAdapter {
    //begin two arrays lists to store fragments and their names.
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    TabAdapter(FragmentManager fm) {
        super(fm);
    }

    //Gets a specified fragments position
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    //adds a fragment to the app
    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    //gets the name of the selected fragment
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    //counts the number of fragments loaded
    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}//End class