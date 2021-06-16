package com.example.kitchenstories.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.kitchenstories.View.Fragment.fm_profile_tab1;
import com.example.kitchenstories.View.Fragment.fm_profile_tab2;
import com.example.kitchenstories.View.Fragment.fm_profile_tab3;

public class PagerAdapter_Profile extends FragmentPagerAdapter {

    private int nuberoftabs;
    public PagerAdapter_Profile(FragmentManager fm, int numOftabs) {
        super(fm);

        this.nuberoftabs= numOftabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new fm_profile_tab1();
            case 1:
                return new fm_profile_tab2();
            case 2:
                return new fm_profile_tab3();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return nuberoftabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
