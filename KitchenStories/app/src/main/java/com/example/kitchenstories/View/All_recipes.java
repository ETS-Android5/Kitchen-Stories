package com.example.kitchenstories.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.kitchenstories.R;
import com.example.kitchenstories.View.Fragment.FragmentCall;
import com.example.kitchenstories.View.Fragment.FragmentContact;
import com.example.kitchenstories.View.Fragment.FragmentFav;
import com.example.kitchenstories.ViewModel.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class All_recipes extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_recipes);

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.view_pager);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // add Fragment here
        viewPagerAdapter.AddFragment(new FragmentCall(), "Call");
        viewPagerAdapter.AddFragment(new FragmentContact(), "Contact");
        viewPagerAdapter.AddFragment(new FragmentFav(), "Fav");

        // View
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);


    }
}